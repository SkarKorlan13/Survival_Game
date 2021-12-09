package main;

import java.awt.*;

public class Main{

    public static void main(String[] args) {

        //Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();   //returns wrong size

        /*
        DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        Dimension screen = new Dimension(displayMode.getWidth(), displayMode.getHeight());

        screen.height = (int) (screen.width * Window.aspectHeight);

        screen.height -= screen.height % GamePanel.maxTileY;
        screen.width -= screen.width % GamePanel.maxTileX;

        Window.maxSize = screen;
        System.out.println(Window.maxSize);
        Window.minSize = new Dimension(screen.width/2, screen.height/2);
        System.out.println(Window.minSize);

        //Window.aspectHeight = ((double) screen.height/screen.width);
         */

        Window.maxSize = new Dimension(GamePanel.tileSize*GamePanel.maxTileX, GamePanel.tileSize*GamePanel.maxTileY);

        Window.init("2D Survival Game");
    }
}
