package main;

import world.ImageHandler;

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

        //Global.setState(Global.StateType.GAME);

        ImageHandler.initializeImages();

        Global.setState(Global.StateType.MENU, null);

        Dimension screen = new Dimension(Global.tileSize*Global.maxTileX, Global.tileSize*Global.maxTileY);

        Window.init("2D Survival Game", screen);

        new Thread(new Window()).start();
    }
}








