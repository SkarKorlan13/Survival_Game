package main;

public class Global {
    public enum StateType {
        MENU, GAME
    }

    public static StateType currentStateType;
    public static GameState game;
    public static MainMenuState menu;
    public static State currentState;

    public static int tileSize = 48;

    public static int worldSize = 256;

    public static int maxTileX = 25; //24 + 1 for player in middle
    public static int maxTileY = 13; //12 + 1 for player in middle

    public static void setState(StateType state) {
        Global.currentStateType = state;

        //clear renderer?

        switch (state) {
            case GAME -> {
                Global.game = new GameState();
                Global.game.init();
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

}
