package gui;

import main.Global;
import main.MainMenuState;
import util.ControlHandler;

import java.io.File;

public class LoadGame_MenuGUI extends MenuGUI {

    public LoadGame_MenuGUI() {
        File saveFolder = new File("saves");
        File[] listOfSaves = saveFolder.listFiles();

        if (listOfSaves != null) {
            lines = new String[listOfSaves.length+1];
            int i = 0;
            for (File file : listOfSaves) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
                lines[i] = file.getPath();
                i++;
            }
            lines[lines.length-1] = "Back";
        }
        if (lines.length == 0) {
            lines = new String[] {
                    "No Saves Found",
                    "Back"
            };
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.SELECT.pressedTick()) {
            if (currentLine == lines.length-1) {
                Global.menu.setCurrentMenu(MainMenuState.MenuType.MAIN);
            } else {
                Global.loadGame(lines[currentLine]);
            }
        }
    }
}

/*
package gui;

import main.Global;
import main.Window;
import util.ControlHandler;
import util.MathUtils;

import javax.swing.*;

public class NewGame_MenuGUI extends MenuGUI {

    int minWorldSize = Math.max(Global.maxTileX, Global.maxTileY);
    int worldSize = minWorldSize;
    long worldSeed;

    public NewGame_MenuGUI() {
        update();
    }

    public void update() {
        lines = new String[] {
                "World Size: " + worldSize,
                "World Seed: " + worldSeed,
                "Create New World",
        };
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                case 0 -> {
                    String size = JOptionPane.showInputDialog(Window.frame, "World Size: ");
                    if (MathUtils.isNumber(size, Integer.MAX_VALUE)) {
                        if (Integer.parseInt(size) >= minWorldSize) {
                            worldSize = Integer.parseInt(size);
                            update();
                        } else {
                            JOptionPane.showMessageDialog(Window.frame,"Please enter a number between " + minWorldSize + " and " + Integer.MAX_VALUE,"Incorrect Input", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(Window.frame,"Please enter a number between " + minWorldSize + " and " + Integer.MAX_VALUE,"Incorrect Input", JOptionPane.WARNING_MESSAGE);
                    }
                }
                case 1 -> {
                    String seed = JOptionPane.showInputDialog(Window.frame, "World Seed: ");
                    if (MathUtils.isNumber(seed, Long.MAX_VALUE)) {
                        worldSeed = Long.parseLong(seed);
                        update();
                    } else {
                        JOptionPane.showMessageDialog(Window.frame,"Please enter a number between 0 and " + Long.MAX_VALUE,"Incorrect Input", JOptionPane.WARNING_MESSAGE);
                    }
                }
                case 2 -> Global.createNewGame(worldSize, worldSeed);
            }
        }
    }
}

 */