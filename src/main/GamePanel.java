/*
package main;

import gui.GUIHandler;
import world.World;
import world.entity.Player;
import util.Keyboard;
import util.Time;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static int tileSize = 48;

    public static boolean gamePaused = false;

    public static int maxTileX = 25; //24 + 1 for player in middle
    public static int maxTileY = 13; //12 + 1 for player in middle

    private static int frames, ticks, fps, tps;
    private static long lastSecond, lastFrame, frameTime, tickTimeRemaining;

    public World world;

    public GUIHandler guiHandler;

    public GamePanel(Dimension dimension) {
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(Color.BLACK);
        this.addKeyListener(Keyboard.getListener());
        this.setFocusable(true);

        Player player = new Player(128, 128);
        world = new World(256, 137117711, player);
        guiHandler = new GUIHandler();
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
        guiHandler.tick();
        if (!gamePaused) {
            world.tick();
        } else {

        }
    }

    private void update() {
        Keyboard.update();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        world.render(g2);

        guiHandler.render(g2);
    }
}
 */