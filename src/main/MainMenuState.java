package main;

import gui.LoadGame_MenuGUI;
import gui.Main_MenuGUI;
import gui.MenuGUI;
import gui.NewGame_MenuGUI;

import java.awt.*;

public class MainMenuState implements State {

    public enum MenuType {
        MAIN,
        NEW_GAME,
        LOAD_GAME,
        SETTINGS
    }

    private MenuType[] currentMenus;

    private int index=0;

    private MenuGUI currentMenu;

    public MainMenuState() {
        currentMenus = new MenuType[MenuType.values().length];
        setCurrentMenu(MenuType.MAIN);    //Default to main menu
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

    public void addCurrentMenu(MenuType menu) {
        setCurrentMenu(menu);
        currentMenus[index] = menu;
        index++;
    }

    public void previousMenu() {
        if (index == 0) {
            return; //Can't go back from main menu
        }

        currentMenus[index] = null;
        setCurrentMenu(currentMenus[index-1]);
        index--;
    }

    @Override
    public void tick() {
        currentMenu.tick();
        System.out.println(index);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        currentMenu.render(g2);
    }
}
