package main;

import util.ControlHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    public static JFrame frame;

    public static GamePanel gp;

    public static boolean close = false;

    public static int width;
    public static int height;

    public static double aspectWidth;
    public static double aspectHeight;

                                                        //weird numbers (14 and 37)
    public static Dimension maxSize = new Dimension(1540+14, 770+37);
    public static Dimension lastSize;

    public static boolean fullscreen=false;

    public static void init(String title, Dimension dimension) {

        dimension.width *= 0.5;
        dimension.height *= 0.5;

        lastSize = dimension;

        width = dimension.width;
        height = dimension.height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setPreferredSize(dimension);
        frame.setSize(dimension);
        frame.setResizable(false);

        //frame.setState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                close = true;
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                width = gp.getWidth();
                height = gp.getHeight();
                GamePanel.tileSize = width/24;
                System.out.println(width + " # " + height);
            }
        });

        gp = new GamePanel(dimension);

        frame.add(gp);
        frame.pack();
        frame.setLocation(-10, 0);

        frame.setVisible(true);
        gp.setVisible(true);

        Thread thread = new Thread(gp);
        thread.start();
    }

    public static void tick() {

        //RESIZE
        if (ControlHandler.TEST1.pressedTick()) {
            frame.setSize((int)(frame.getWidth() + 10*aspectWidth), (int)(frame.getHeight() + 10*aspectHeight));
        } else if (ControlHandler.TEST2.pressedTick()) {
            frame.setSize((int)(frame.getWidth() - 10*aspectWidth), (int)(frame.getHeight() - 10*aspectHeight));
        } else if (ControlHandler.TEST3.pressedTick()) {
            if (fullscreen) {
                frame.setSize(lastSize);
            } else {
                lastSize = frame.getSize();
                frame.setSize(maxSize);
            }

            fullscreen = !fullscreen;
        }
    }
}