package main;

import entity.Player;
import util.Keyboard;
import util.Time;

import java.awt.*;

public class Main implements Runnable{

    public static Window window;

    public static Player player;

    private static int frames, ticks, fps, tps;
    private static long lastSecond, lastFrame, frameTime, tickTimeRemaining;

    public static void main(String[] args) {

        player = new Player();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window = new Window("2D Survival Game", screen.width, screen.height);

        new Thread(new Main()).start();
    }

    @Override
    public void run() {

        lastFrame = Time.now() - Time.NS_PER_TICK;

        while (!window.close) {
            long now = Time.now();

            if (now - lastSecond >= Time.NS_PER_SECOND) {
                lastSecond = now;
                fps = frames;
                tps = ticks;
                frames = 0;
                ticks = 0;
                System.out.println(fps + " | " + tps);
            }

            frameTime = now - lastFrame;
            lastFrame = now;

            long tickTime = frameTime + tickTimeRemaining;
            while (tickTime >= Time.NS_PER_TICK) {
                tick();
                tickTime -= Time.NS_PER_TICK;
                ticks++;
            }
            tickTimeRemaining = tickTime;

            update();

            window.gp.repaint();
            frames++;

            try {
                long sleepMs = ((16 * Time.NS_PER_MS) - (Time.now() - now)) / Time.NS_PER_MS;
                Thread.sleep(sleepMs < 0 ? 0 : sleepMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    private void tick() {
        Keyboard.tick();
        player.tick();
    }

    private void update() {
        Keyboard.update();
    }
}
