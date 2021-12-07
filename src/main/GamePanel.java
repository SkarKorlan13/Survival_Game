package main;

import entity.Player;
import util.Keyboard;
import util.Time;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public static int tileSize = 48;

    private static int frames, ticks, fps, tps;
    private static long lastSecond, lastFrame, frameTime, tickTimeRemaining;

    Window window;

    Player player;

    public GamePanel(Window window, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
        this.setBackground(Color.BLACK);
        this.addKeyListener(Keyboard.getListener());
        this.setFocusable(true);
        this.window = window;
        this.player = new Player();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        player.render(g2);
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
