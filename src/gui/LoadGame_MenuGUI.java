package gui;

import main.Global;
import main.MainMenuState;
import main.Window;
import util.ControlHandler;

import javax.swing.*;
import java.io.File;

public class LoadGame_MenuGUI extends MenuGUI implements GUI {

    public LoadGame_MenuGUI() {
        updateLines();
    }

    public void updateLines() {
        File saveFolder = new File("saves");
        File[] listOfSaves = saveFolder.listFiles();

        if (listOfSaves != null) {
            lines = new String[listOfSaves.length+1];
            int i = 0;
            for (File file : listOfSaves) {
                lines[i] = file.getPath().replace(".txt", "").replace("saves\\", "");
                i++;
            }
            lines[lines.length-1] = "Back";
        } else {
            lines = new String[] {
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
                Global.loadGame("saves\\" + lines[currentLine] + ".txt");
            }
        }

        if (ControlHandler.BACK.pressedTick()) {
            Global.menu.setCurrentMenu(MainMenuState.MenuType.MAIN);
        }

        if (ControlHandler.DELETE.pressedTick()) {
            int i = JOptionPane.showConfirmDialog(Window.frame,"Are you sure you want to delete \"" + lines[currentLine] + "\"?", "Delete World Save", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                if (new File("saves\\" + lines[currentLine] + ".txt").delete()) {
                    JOptionPane.showMessageDialog(Window.frame, "Save successfully deleted", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);
                    updateLines();
                } else {
                    JOptionPane.showMessageDialog(Window.frame, "Error: Save could not be deleted", "Delete Unsuccessful", JOptionPane.ERROR_MESSAGE);
                }
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