package gui;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class GameGUIHandler {

    public static List<GameGUI> active = new ArrayList<>();
    public static List<GameGUI> all = new ArrayList<>();

    /*
    Pause Menu
    HUD
    Inventory
    ...
     */

    public GameGUIHandler() {

    }

    public void render(Graphics2D g2) {
        for (GameGUI gui : active) {
            gui.render(g2);
        }
    }

    public void tick() {
        for (GameGUI gui : active) {
            gui.tick();
        }
    }
}
