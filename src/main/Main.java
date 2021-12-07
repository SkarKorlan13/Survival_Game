package main;

import java.awt.*;

public class Main{

    public static Window window;

    public static void main(String[] args) {

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window = new Window("2D Survival Game", screen.width, screen.height);

    }
}
