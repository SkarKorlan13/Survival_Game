package main;

import gui.game.GameGUIHandler;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GameState implements State {

    public GameGUIHandler guiHandler = new GameGUIHandler();

    public World world;

    public int worldSize;  //Size of world in tiles, last tile is worldSize - 1 b/c starts at 0

    public Rectangle worldBounds;

    public Point camera = new Point();  //Location of camera - usually centered on player
    public Rectangle cameraBounds;  //Area in which the camera can be without "rendering" areas outside the defined world size

    public boolean gamePaused = false;

    //Create new game
    public GameState(int worldSize, long worldSeed) {
        this.worldSize = worldSize;

        init();

        world = new World(worldSize, worldSeed);
        updateCameraPos(world.getPlayerPos());
        updateDim();
    }

    public GameState(String filename) {
        File gameFile = new File(filename);

        try {
            FileInputStream fileInputStream = new FileInputStream(gameFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            world = (World) objectInputStream.readObject();
            worldSize = world.getSize();

            init();

            updateCameraPos(world.getPlayerPos());

            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateDim();
    }

    public void init() {
        //Rectangle.contains checks if point is < bounds instead of <= so add one to width/height to make it work
        cameraBounds = new Rectangle(Global.maxTileX/2, Global.maxTileY/2,
                worldSize - Global.maxTileX + 1, worldSize - Global.maxTileY + 1);

        worldBounds = new Rectangle(0, 0, worldSize, worldSize);
    }

    public void save() {
        while (world.getSaveName() == null) {
            String name = JOptionPane.showInputDialog(Window.frame,"Save Name: ");
            try {
                if (!new File("saves/" + name + ".txt").createNewFile()) {
                    JOptionPane.showMessageDialog(Window.frame,"Save name already in use","Duplicate Name", JOptionPane.WARNING_MESSAGE);
                } else {
                    world.setSaveName(name);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("saves/" + world.getSaveName() + ".txt", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(world);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        if (!gamePaused) {
            world.tick();
        }
        guiHandler.tick();
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void render(Graphics2D g2) {
        world.render(g2);
        guiHandler.render(g2);
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

    @Override
    public void updateDim() { guiHandler.updateDim(); }

    @Override
    public void previousMenu() {

        //guiHandler.previousGUI();
    }
}
