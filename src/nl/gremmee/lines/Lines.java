package nl.gremmee.lines;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public class Lines extends Canvas implements Runnable {
    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int NUM_STARS = 10;

    public static int speed = 1;

    int mxOldPos = 0;

    private boolean running = false;
    private Handler handler;
    private int frames = 0;
    private int myOldPos = 0;

    private Thread thread;

    public Lines() {
        handler = new Handler();

        new Window(WIDTH, HEIGHT, "Lines", this);
    }

    public static void main(String[] aArgs) {
        new Lines();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                int stars = handler.getStars();
                System.out.println("W x H : " + WIDTH + " x " + HEIGHT + " FPS: " + frames + " : Stars " + stars);
                frames = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0, 0, 0, 15));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.translate((WIDTH / 2), (HEIGHT / 2));
        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        float startangle = Utils.getRandomFloat(0, (float) Math.PI * 2);
        int mxPos = Window.getMouseX();
        int myPos = Window.getMouseY();
        if ((mxPos != mxOldPos) || (myPos != myOldPos)) {
            myOldPos = myPos;
            mxOldPos = mxPos;
            handler.clear();
            mxPos = (int) Utils.map(mxPos, 0, WIDTH, 0, 45);
            myPos = (int) Utils.map(myPos, 0, HEIGHT, 0, HEIGHT / 2);
            for (int i = 0; i < mxPos; i++) {
                float radius = myPos;
                float angle = (float) (((Math.PI * 2) / mxPos) * i) + startangle;
                handler.addObject(new ARGLine(angle, radius));
            }
        }

        handler.update();
    }
}
