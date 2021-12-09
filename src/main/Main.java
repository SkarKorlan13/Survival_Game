package main;

import java.awt.*;

public class Main{

    public static void main(String[] args) {

        //Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        Dimension screen = new Dimension(displayMode.getWidth(), displayMode.getHeight());

        Window.aspectWidth = 1;
        Window.aspectHeight = 0.5;
        //Window.aspectHeight = ((double) screen.height/screen.width);

        Window.init("2D Survival Game", screen);

    }
}
