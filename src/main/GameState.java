package main;

import world.World;
import world.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState implements State{

    //public GUIHandler guiHandler;

    public World world;

    public int worldSize;  //Size of world in tiles, last tile is worldSize - 1 b/c starts at 0

    public Rectangle worldBounds;

    public Point camera = new Point();  //Location of camera - usually centered on player
    public Rectangle cameraBounds;  //Area in which the camera can be without "rendering" areas outside the defined world size

    //Create new game
    public GameState(int worldSize, long worldSeed) {
        this.worldSize = worldSize;

        init();

        world = new World(worldSize, worldSeed);
        world.addEntity(new Player(worldSize/2, worldSize/2), worldSize/2, worldSize/2);
        updateCameraPos(world.playerPos);
    }

    public GameState(String filename) {
        File gameFile = new File(filename);

        try {
            FileInputStream fileInputStream = new FileInputStream(gameFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            world = (World) objectInputStream.readObject();
            worldSize = world.size;

            init();

            updateCameraPos(world.playerPos);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        //Rectangle.contains checks if point is < bounds instead of <= so add one to width/height to make it work
        cameraBounds = new Rectangle(Global.maxTileX/2, Global.maxTileY/2,
                worldSize - Global.maxTileX + 1, worldSize - Global.maxTileY + 1);

        worldBounds = new Rectangle(0, 0, worldSize, worldSize);
    }

    public void save() {
        while (world.saveName == null) {
            String name = JOptionPane.showInputDialog(Window.frame,"Save Name: ");
            try {
                if (!new File("saves/" + name + ".txt").createNewFile()) {
                    JOptionPane.showMessageDialog(Window.frame,"Save name already in use","Duplicate Name", JOptionPane.WARNING_MESSAGE);
                } else {
                    world.saveName = name;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("saves/" + world.saveName + ".txt", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(world);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void render(Graphics2D g2) {
        world.render(g2);
    }

    public void updateCameraPos(Point cameraPos) {
        if (cameraPos.x < cameraBounds.x) {
            cameraPos.x = cameraBounds.x;
        }
        if (cameraPos.y < cameraBounds.y) {
            cameraPos.y = cameraBounds.y;
        }
        //-1's are because of Rectangle.contains issue stated in <init>
        if (cameraPos.x > cameraBounds.getMaxX() - 1) {
            cameraPos.x = (int) cameraBounds.getMaxX() -1;
        }
        if (cameraPos.y > cameraBounds.getMaxY() - 1) {
            cameraPos.y = (int) cameraBounds.getMaxY() - 1;
        }
        if (cameraBounds.contains(cameraPos)) {
            camera.setLocation(cameraPos);
            //return true;
        } else {
            System.out.println("Error Positioning Camera: " + cameraPos);
            //return false;
        }
    }
}