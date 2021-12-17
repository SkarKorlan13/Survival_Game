package gui;

import main.Global;
import main.Window;
import util.ControlHandler;

import javax.swing.*;
import java.awt.*;

public class PauseGUI extends MenuGUI implements GUI {

    public Rectangle dim;

    public PauseGUI() {
        lines = new String[] {
                "Save",
                "Quit Game",
                "Back"
        };

        dim = new Rectangle((int) (Window.maxSize.getWidth()/6), (int) (Window.maxSize.getHeight()/6), (int) (Window.maxSize.getWidth()*2/3), (int) (Window.maxSize.getHeight()*2/3));
    }

    @Override
    public void tick() {
        super.tick();

        if (ControlHandler.BACK.pressedTick()) {
            Global.game.gamePaused = false;
        }

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                case 0 -> Global.game.save();
                case 1 -> {
                    int i = JOptionPane.showConfirmDialog(Window.frame,"Save Game?", "Save Game", JOptionPane.YES_NO_OPTION);
                    if (i == JOptionPane.YES_OPTION) {
                        Global.game.save();
                    }
                    Global.setState(Global.StateType.MENU, null);
                }
                case 2 -> Global.game.gamePaused = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {

        g2.setColor(Color.BLACK);
        g2.fillRect(dim.x, dim.y, dim.width, dim.height);

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
                    font.getSize() * i + font.getSize() + dim.y);
        }
    }
}
