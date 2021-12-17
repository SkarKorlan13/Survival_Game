package gui;

import main.Global;
import util.ControlHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameGUIHandler {

    public static List<GUI> active = new ArrayList<>();

    /*
    Pause Menu
    HUD
    Inventory
    ...
     */

    public static PauseGUI pauseGUI = new PauseGUI();

    public GameGUIHandler() {

    }

    public void render(Graphics2D g2) {
        for (int i = 0; i < active.size(); i++) {
            active.get(i).render(g2);
        }
    }

    public void tick() {
        if (ControlHandler.ESC.pressedTick()) {
            if (!Global.game.gamePaused) {
                //Will be different once inventories etc. added
                Global.game.gamePaused = true;
            }
        }

        if (Global.game.gamePaused && !active.contains(pauseGUI)) {
            active.add(pauseGUI);
        } else if (!Global.game.gamePaused) {
            active.remove(pauseGUI);
        }

        for (int i = 0; i < active.size(); i++) {
            active.get(i).tick();
        }
    }
}
