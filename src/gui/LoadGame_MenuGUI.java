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
                Global.menu.previousMenu();
            } else {
                Global.loadGame("saves\\" + lines[currentLine] + ".txt");
            }
        }

        if (ControlHandler.BACK.pressedTick()) {
            Global.menu.previousMenu();
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