package gui.menu;

import gui.GUI;
import main.Global;
import main.Window;
import util.ControlHandler;

public class Main extends MenuGUI {

    public Main() {
        lines = new String[] {
                "New Game",
                "Load Game",
                /*"Settings",*/
                "Exit"
        };
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                case 0 -> Global.menu.nextMenu(new NewGame());
                case 1 -> Global.menu.nextMenu(new LoadGame());
                //case 2 -> Global.menu.addCurrentMenu(MainMenuState.MenuType.SETTINGS);
                //case 3 -> Window.close();

                case 2 -> Window.close(); //Settings menu currently non-existent
            }
        }
    }
}
