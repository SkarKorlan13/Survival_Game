package main;

import util.Keyboard;
import util.Time;
import world.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements Runnable{

    public static JFrame frame;

    public static JPanel gamePanel;

    public static boolean close = false;

    public static int width;
    public static int height;

    private static int frames, fps;
    private static long lastSecond, lastFrame, frameTime;

    public static Dimension maxSize;

    public static void init(String title, Dimension dim) {

        maxSize = new Dimension(dim);

        width = maxSize.width;
        height = maxSize.height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);
        frame.setIconImage(ImageHandler.getImage("Player"));

        //frame.setState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                /*if (Global.game != null) {
                    Global.game.save();
                }*/
                super.windowClosing(e);
                close = true;
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                width = gamePanel.getWidth();
                height = gamePanel.getHeight();
                System.out.println(width + " # " + height);

                Global.currentState.updateDim();
            }
        });

        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                super.paintComponent(g);

                Global.currentState.render(g2);
            }
        };

        gamePanel.setPreferredSize(maxSize);
        gamePanel.setSize(maxSize);
        gamePanel.setBackground(Color.BLACK);
        gamePanel.addKeyListener(Keyboard.getListener());
        gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getPoint() + " | " + MouseInfo.getPointerInfo().getLocation());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        /*
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        System.out.println(pointerInfo.getLocation());
         */

        gamePanel.setFocusable(true);

        frame.add(gamePanel);
        frame.pack();
        frame.setLocation(-10, 0);

        frame.setVisible(true);
        gamePanel.setVisible(true);
    }

    public static void resize(Dimension dimension) {
        frame.setSize(dimension.width, dimension.height+34);
    }

    @Override
    public void run() {
        while (!Window.close) {
            long now = Time.now();

            if (now - lastSecond >= Time.NS_PER_SECOND) {
                lastSecond = now;
                fps = frames;
                frames = 0;
                System.out.println("FPS: " + fps);
            }

            frameTime = now - lastFrame;
            lastFrame = now;

            update();

            render();
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

    private void update() {
        Keyboard.update();
        Global.currentState.update();
    }

    private void render() {
        gamePanel.repaint();
    }

    public static void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}