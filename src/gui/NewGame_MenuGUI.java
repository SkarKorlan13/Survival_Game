package gui;

import main.Global;
import main.MainMenuState;
import main.Window;
import util.ControlHandler;
import util.MathUtils;

import javax.swing.*;

public class NewGame_MenuGUI extends MenuGUI implements GUI {

    int minWorldSize = Math.max(Global.maxTileX, Global.maxTileY);
    int worldSize = minWorldSize;
    long worldSeed;

    public NewGame_MenuGUI() {
        updateLines();
    }

    public void updateLines() {
        lines = new String[] {
                "World Size: " + worldSize,
                "World Seed: " + worldSeed,
                "Create New World",
                "Back"
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
                            updateLines();
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
                        updateLines();
                    } else {
                        JOptionPane.showMessageDialog(Window.frame,"Please enter a number between 0 and " + Long.MAX_VALUE,"Incorrect Input", JOptionPane.WARNING_MESSAGE);
                    }
                }
                case 2 -> Global.createNewGame(worldSize, worldSeed);
                case 3 -> Global.menu.previousMenu();
            }
        }

        if (ControlHandler.BACK.pressedTick()) {
            Global.menu.previousMenu();
        }
    }
}
