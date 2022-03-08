package gui.game;

import gui.menu.MenuGUI;

public class InventoryGUI extends MenuGUI {
    public InventoryGUI() {
        updateLines();
    }

    public void updateLines() {
        lines = Global.game.world.getPlayer().getInventory();
    }

    @Override
    public String[] getRenderText() {
        String[] renderLines = lines.clone();

        renderLines[currentLine] += "<";

        return renderLines;
    }
}
