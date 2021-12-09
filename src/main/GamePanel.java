package main;

import world.World;
import world.entity.Player;
import util.Keyboard;
import util.Time;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static int tileSize = 48;

    public static int maxTileX = 24;
    public static int maxTileY = 12;

    private static int frames, ticks, fps, tps;
    private static long lastSecond, lastFrame, frameTime, tickTimeRemaining;

    public static Player player;

    public static World world;

    public GamePanel(Dimension dimension) {
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(Color.BLACK);
        this.addKeyListener(Keyboard.getListener());
        this.setFocusable(true);

        player = new Player();
        world = new World();
    }

    @Override
    public void run() {

        lastFrame = Time.now() - Time.NS_PER_TICK;

        while (!Window.close) {
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

            repaint();
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
        Window.tick();
    }

    private void update() {
        Keyboard.update();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        world.render(g2);

        player.render(g2);
    }
}
