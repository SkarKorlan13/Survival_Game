package gui.game;

import gui.menu.MenuGUI;
import main.Global;
import main.Window;
import util.Renderer;

import java.awt.*;

public class MainGameGUI extends MenuGUI {

    public MainGameGUI() {
        lines = new String[] {
                "Inventory",
                "Crafting",
        };
    }

    @Override
    public void render(Graphics2D g2) {
        String[] renderLines = lines.clone();
        renderLines[currentLine] = ">" + renderLines[currentLine] + "<";

        Renderer.renderText(Window.gamePanel.getBounds(), Global.getFont(), true, g2, Color.WHITE, renderLines);
    }

    @Override
    public String[] getRenderText() {
        String[] renderLines = lines.clone();
        renderLines[currentLine] += "<";
        return renderLines;
    }

}
