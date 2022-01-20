package gui;

import main.Global;
import main.MainMenuState;
import main.Window;
import util.ControlHandler;

public class Main_MenuGUI extends MenuGUI implements GUI {

    public Main_MenuGUI() {
        lines = new String[] {
                "New Game",
                "Load Game",
                /*"Settings",*/
                "Exit"
        };
        currentLine = 0;    //New Game by default
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                case 0 -> Global.menu.addCurrentMenu(MainMenuState.MenuType.NEW_GAME);
                case 1 -> Global.menu.addCurrentMenu(MainMenuState.MenuType.LOAD_GAME);
                //case 2 -> Global.menu.addCurrentMenu(MainMenuState.MenuType.SETTINGS);
                //case 3 -> Window.close();

                case 2 -> Window.close(); //Settings menu currently non-existent
            }
        }
    }
}
