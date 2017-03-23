package nl.gremmee.lines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class ARGLine {

    float a;

    float pz;
    float r;

    float x;
    float y;
    float z;

    private ID id;
    private int color;

    private int speed;

    private Random random = new Random();

    public ARGLine(float aA, float aR) {
        id = ID.ARGLine;
        r = aR;
        a = aA;
        x = (r * (float) Math.sin(a));
        y = (r * (float) Math.cos(a));
    }

    public void doRender(Graphics aGraphics) {
        color = Window.getMouseY();
        float c = Utils.map(color, 0, Lines.HEIGHT, 64, 255);
        aGraphics.setColor(new Color((int) c, (int) c, (int) c));

        Graphics2D g2 = (Graphics2D) aGraphics;
        g2.setStroke(new BasicStroke(3));
        g2.drawLine((int) x, (int) y, (int) x, (int) y);
    }

    public void doUpdate() {
        // speed = Window.getMouseX();
        // float s = Utils.map(speed, 0, Lines.WIDTH, 3, 50);
        // z = z - s;
        // if (z < 1)
        // {
        // z = Lines.WIDTH;
        // x = Utils.getRandomFloat(-Lines.WIDTH, Lines.WIDTH);
        // y = Utils.getRandomFloat(-Lines.HEIGHT, Lines.HEIGHT);
        //
        // pz = z;
        // }
    }

    public ID getID() {
        return this.id;
    }

    public Random getRandom() {
        return this.random;
    }

    public void render(Graphics aGraphics) {
        doRender(aGraphics);
    }

    public void setId(ID aId) {
        this.id = aId;
    }

    public void update() {
        doUpdate();
    }
}
