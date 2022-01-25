package gui.game;

import main.Global;
import util.ControlHandler;

import java.awt.*;

public class GameGUIHandler {

    /*
    Pause Menu
    Main GUI:
        Inventory
        Info on focused tile
        Crafting
        etc.
    Maybe more, idk
    ...
     */

    private static PauseGUI pauseGUI = new PauseGUI();

    private static MainGUIHandler mainGUIHandler = new MainGUIHandler();

    public GameGUIHandler() {

    }

    public void render(Graphics2D g2) {
        mainGUIHandler.render(g2);

        //Pause menu renders on top
        if (pauseGUI.active) {
            pauseGUI.render(g2);
        }
    }

    public void tick() {
        if (ControlHandler.ESC.pressedTick()) {
            //Will be different once inventories etc. added
            Global.game.gamePaused = !Global.game.gamePaused;
        }

        if (Global.game.gamePaused && !pauseGUI.active) {
            pauseGUI.active = true;
        } else if (!Global.game.gamePaused) {
            pauseGUI.active = false;
        }

        if (pauseGUI.active) {
            pauseGUI.tick();
        } else {
            mainGUIHandler.tick();
        }
    }

    public void updateDim() {
        pauseGUI.updateDim();
        mainGUIHandler.updateDim();
    }
}
