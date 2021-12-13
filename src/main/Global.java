package main;

import util.Time;
import world.tile.TileBush;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Global {
    public enum StateType {
        MENU, GAME
    }

    public static StateType currentStateType;
    public static GameState game;
    public static MainMenuState menu;
    public static State currentState;

    public static int tileSize = 48;

    public static int maxTileX = 25; //24 + 1 for player in middle
    public static int maxTileY = 13; //12 + 1 for player in middle

    public static void setState(StateType state, State gameState) {
        Global.currentStateType = state;

        //clear renderer?

        switch (state) {
            case GAME -> {
                Global.game = (GameState) gameState;
                //Global.game.init();
                Global.menu = null;
                Global.currentState = game;
            }
            case MENU -> {
                Global.game = null;
                Global.menu = new MainMenuState();
                Global.currentState = menu;
            }
        }
    }

    public static void createNewGame(int worldSize, long worldSeed) {
        if (worldSeed == 0) {
            worldSeed = Time.now();
        }
        setState(StateType.GAME, new GameState(worldSize, worldSeed));
    }

    public static void loadGame(String filename) {
        if (new File(filename).exists()) {
            setState(StateType.GAME, new GameState(filename));
        } else {
            JOptionPane.showMessageDialog(Window.frame,"World File Not Found","File Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }

}
