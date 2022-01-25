package main;

import gui.menu.Main;
import gui.menu.MenuGUI;

import java.awt.*;

public class MainMenuState implements State {

    public enum MenuType { //currently obsolete, may be useful later, who knows
        MAIN,
        NEW_GAME,
        LOAD_GAME,
        SETTINGS
    }

    private MenuGUI[] currentMenus;

    private int index=0;

    public MainMenuState() {
        currentMenus = new MenuGUI[MenuType.values().length];
        currentMenus[0] = new Main();    //Default to main menu
        updateDim();
    }

    public void nextMenu(MenuGUI menu) {
        index++;
        currentMenus[index] = menu;
    }

    public void previousMenu() {
        if (index == 0) {
            System.out.println("Can't go back further");
            return; //Can't go back from main menu
        }

        currentMenus[index] = null;
        index--;
    }

    @Override
    public void tick() {
        //System.out.println(index);
        currentMenus[index].tick();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2) {
        currentMenus[index].render(g2);
    }

    @Override
    public void updateDim() {
        for (MenuGUI menu : currentMenus) {
            if (menu != null) {
                menu.updateDim();
            }
        }
    }
}
