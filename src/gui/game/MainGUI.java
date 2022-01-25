package gui.game;

import gui.GUI;

import java.awt.*;

public class MainGUI implements GUI {

    private GameGUIType[] openGUIs;

    public enum GameGUIType {
        Inventory,
        Crafting,

    }

    public MainGUI() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g2) {

    }
}
