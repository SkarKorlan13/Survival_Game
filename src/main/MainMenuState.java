package main;

import gui.*;

import java.awt.*;

public class MainMenuState implements State {

    public enum MenuType {
        MAIN,
        NEW_GAME,
        LOAD_GAME,
        SETTINGS
    }

    public MenuGUI currentMenu;

    public MainMenuState() {
        currentMenu = new Main_MenuGUI();    //Default to main menu
    }

    public void setCurrentMenu(MenuType menu) {
        switch (menu) {
            case MAIN:
                currentMenu = new Main_MenuGUI();
                break;
            case NEW_GAME:
                currentMenu = new NewGame_MenuGUI();
                break;
            case LOAD_GAME:
                currentMenu = new LoadGame_MenuGUI();
                break;
            case SETTINGS:
                //currentMenu = new Settings_MenuGUI();
                break;
        }
    }

    @Override
    public void tick() {
        currentMenu.tick();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        currentMenu.render(g2);
    }
}
