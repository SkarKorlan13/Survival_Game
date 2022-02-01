package gui.menu;

import main.Global;
import main.Window;
import util.ControlHandler;

public class MainMenu extends MenuGUI {

    public MainMenu() {
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
                case 0 -> Global.menu.nextMenu(new NewGameMenu());
                case 1 -> Global.menu.nextMenu(new LoadGameMenu());
                //case 2 -> Global.menu.addCurrentMenu(MainMenuState.MenuType.SETTINGS);
                //case 3 -> Window.close();

                case 2 -> Window.close(); //Settings menu currently non-existent
            }
        }
    }
}
