package main;

import java.awt.*;

public class GameUIHandler {

    public static GameUI[] active;
    public static GameUI[] inactive;

    public static GameUI MainMenu = new GameUI();
    public static GameUI PauseMenu = new GameUI();

    public static GameUI inGameInfo = new GameUI(); //Equipped item, menus, controls

    public static void render(Graphics2D g2) {

    }
}
