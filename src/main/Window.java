package main;

import world.ImageHandler;

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

    public static double aspectWidth = 1;
    public static double aspectHeight = 0.5;

                                                    //weird numbers
    //public static Dimension maxSize = new Dimension(1540, 770);   //home
    public static Dimension maxSize;// = new Dimension(1924+16, 962);  //school
    //public static Dimension minSize;// = new Dimension();


    public static boolean fullscreen=false;

    public static void init(String title) {

        width = maxSize.width;
        height = maxSize.height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //frame.setPreferredSize(minSize);
        //frame.setSize(minSize);
        frame.setResizable(true);
        frame.setIconImage(ImageHandler.PLAYER);

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
                //GamePanel.tileSize = width/24;
                //System.out.println("Tilesize: " + GamePanel.tileSize);
                System.out.println(width + " # " + height);
            }
        });

        gp = new GamePanel(maxSize);

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

        /*
        if (ControlHandler.TEST1.pressedTick()) {
            if (fullscreen) {
                System.out.println(minSize);
                resize(minSize);
                fullscreen = false;
            } else {
                System.out.println(maxSize);
                resize(maxSize);
                fullscreen = true;
            }
        }
         */

        /*
        if (ControlHandler.TEST1.pressedTick()) {
            frame.setSize((int)(frame.getWidth() + 10*aspectWidth), (int)(frame.getHeight() + 10*aspectHeight));
        } else if (ControlHandler.TEST2.pressedTick()) {
            frame.setSize((int)(frame.getWidth() - 10*aspectWidth), (int)(frame.getHeight() - 10*aspectHeight));
        } else if (ControlHandler.TEST3.pressedTick()) {
            if (fullscreen) {
                resize(minSize);
            } else {
                resize(maxSize);
            }

            fullscreen = !fullscreen;
        }
         */
    }

    public static void resize(Dimension dimension) {
        frame.setSize(dimension.width, dimension.height+34);
    }
}