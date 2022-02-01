package gui.game;

import gui.menu.MenuGUI;
import util.ControlHandler;

public class MainGUI extends MenuGUI {

    public MainGUI() {
        lines = new String[] {
                "Inventory",
                "Crafting",
        };
    }

    @Override
    public String[] getRenderText() {
        String[] renderLines = lines.clone();
        renderLines[currentLine] += "<";
        return renderLines;
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                case 0 -> GameGUIHandler.mainGUI.nextGUI(new InventoryGUI());
                case 1 -> GameGUIHandler.mainGUI.nextGUI(new CraftingGUI());
            }
        }
    }
}
