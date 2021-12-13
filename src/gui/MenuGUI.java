package gui;

import main.Global;
import util.ControlHandler;

import java.awt.*;

public class MenuGUI {

    public Font font = new Font("Monospaced", Font.PLAIN, 60);

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public String[] lines;

    public int currentLine;

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
    }

    public void render(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.setRenderingHints(rh);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (i == currentLine) {
                line = ">" + line + "<";
            }

            g2.drawString(line,
                    (int) (((Global.maxTileX * Global.tileSize) / 2) - font.getStringBounds(line, g2.getFontRenderContext()).getCenterX()),
                    font.getSize() * i + font.getSize());
        }
    }


}