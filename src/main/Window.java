package main;

import util.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    public static int tileSize = 48;

    public JFrame frame;

    public GamePanel gp;

    public boolean close;

    public Window(String title, int width, int height) {

        width /= 1.5;
        height /= 1.5;

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.setResizable(false);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                close = true;
            }
        });

        gp = new GamePanel(this, width, height);

        frame.add(gp);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        gp.setVisible(true);
    }
}
