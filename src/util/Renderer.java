package util;

import main.Window;

import java.awt.*;

public class Renderer {
    public static void renderText(Rectangle dim, Font font, boolean centeredX, Graphics2D g2, Color color, String... lines) {

        if (lines == null) {
            System.out.println("renderText.lines == null");
            return;
        }

        g2.setFont(font);
        g2.setColor(color);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Point pos = new Point();

            if (centeredX) {
                pos.x = (int) ((dim.getCenterX()) - font.getStringBounds(line, g2.getFontRenderContext()).getCenterX());
            } else {
                pos.x = dim.x + font.getSize();
            }

            pos.y = dim.y + font.getSize() * i + font.getSize();

            g2.drawString(line, pos.x, pos.y);
        }
    }

    public static void renderText(Font font, boolean centeredX, Graphics2D g2, Color color, String... lines) {
        renderText(Window.gamePanel.getBounds(), font, centeredX, g2, color, lines);
    }
}
