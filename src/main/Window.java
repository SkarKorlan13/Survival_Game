package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    public JFrame frame;

    public GamePanel gp;

    public boolean close;

    public int width;
    public int height;

    public Window(String title, Dimension dimension) {

        width = dimension.width;
        height = dimension.height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setPreferredSize(dimension);
        frame.setSize(dimension);
        //frame.setResizable(false);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
                System.out.println(width + " # " + height);
            }
        });

        gp = new GamePanel(this, dimension);

        frame.add(gp);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        gp.setVisible(true);

        Thread thread = new Thread(gp);
        thread.start();
    }
}
