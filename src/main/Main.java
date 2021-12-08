package main;

import java.awt.*;

public class Main{

    public static Window window;

    public static void main(String[] args) {

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        screen.height /= 2;
        screen.width /= 2;

        window = new Window("2D Survival Game", screen);

    }
}
