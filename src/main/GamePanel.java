package main;

import util.Keyboard;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Window window;

    public GamePanel(Window window, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setSize(width, height);
        this.setBackground(Color.BLACK);
        this.addKeyListener(Keyboard.getListener());
        this.setFocusable(true);
        this.window = window;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        Main.player.render(g2);
    }
}
