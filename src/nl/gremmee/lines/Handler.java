package nl.gremmee.lines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {
    LinkedList<ARGLine> object = new LinkedList<>();

    public void addObject(ARGLine aObject) {
        this.object.add(aObject);
    }

    public void clear() {
        this.object.clear();
    }

    public ARGLine getGameObject(ARGLine aObject) {
        int index = this.object.indexOf(aObject);
        if (index != -1) {
            return this.object.get(index);
        }
        return null;
    }

    public int getStars() {
        int result = 0;
        for (ARGLine fireObject : object) {
            if (fireObject instanceof ARGLine) {
                result++;
            }
        }
        return result;
    }

    public void removeObject(ARGLine aObject) {
        this.object.remove(aObject);
    }

    public void render(Graphics g) {
        float color = Window.getMouseY();
        float c = Utils.map(color, 0, Lines.HEIGHT, 64, 255);

        for (int i = 0; i < object.size(); i++) {
            ARGLine tempObject = object.get(i);

            for (int j = i + 1; j < object.size(); j++) {
                ARGLine tempObject1 = object.get(j);

                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1));
                g2.setColor(new Color((int) c, (int) c, (int) c));

                g2.drawLine((int) tempObject.x, (int) tempObject.y, (int) tempObject1.x, (int) tempObject1.y);
                tempObject.render(g);
            }
        }
    }

    public void update() {
        for (int i = 0; i < object.size(); i++) {
            ARGLine tempObject = object.get(i);
            tempObject.update();
        }
    }
}
