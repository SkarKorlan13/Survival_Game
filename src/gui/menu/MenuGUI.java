package gui.menu;

import gui.GUI;
import main.Global;
import util.ControlHandler;
import util.Renderer;

import java.awt.*;

public class MenuGUI implements GUI {

    //RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    protected String[] lines;

    protected int currentLine=0;

    @Override
    public void tick() {
        if (ControlHandler.UP.pressedTick()) {
            currentLine--;
            if (currentLine < 0) {
                currentLine = 0;
            }
        }

        if (ControlHandler.DOWN.pressedTick()) {
            currentLine++;
            if (currentLine >= lines.length) {
                currentLine = lines.length-1;
            }
        }

        if (ControlHandler.BACK.pressedTick()) {
            Global.currentState.previousMenu();
        }
    }

    @Override
    public void render(Graphics2D g2) {
        /*
        g2.setFont(Global.getFont());
        g2.setColor(Color.WHITE);
        //g2.setRenderingHints(rh);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (i == currentLine) {
                line = ">" + line + "<";
            }

            g2.drawString(line,
                    (int) (((Global.maxTileX * Global.tileSize) / 2) - Global.getFont().getStringBounds(line, g2.getFontRenderContext()).getCenterX()),
                    Global.getFont().getSize() * i + Global.getFont().getSize());
        }
         */

        String[] renderLines = lines.clone();
        renderLines[currentLine] = ">" + renderLines[currentLine] + "<";

        Renderer.renderText(Global.getFont(), true, g2, Color.WHITE, renderLines);
    }

    @Override
    public void updateDim() {
        //nothing because window size not used, only maxTile X/Y and tilesize
    }

    public String[] getRenderText() { //Should be overridden by subclass
        return lines;
    }
}
