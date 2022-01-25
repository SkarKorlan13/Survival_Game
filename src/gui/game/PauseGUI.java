package gui.game;

import gui.GUI;
import main.Global;
import main.Window;
import util.ControlHandler;

import java.awt.*;

public class PauseGUI implements GUI {

    public boolean active = false;

    private Rectangle dim;

    private int currentLine=0;

    private String[] lines;

    public PauseGUI() {
        lines = new String[] {
                "Save",
                "Quit Game",
                "Back"
        };

        updateDim();
    }

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
            Global.game.gamePaused = false;
        }

        if (ControlHandler.SELECT.pressedTick()) {
            switch (currentLine) {
                case 0 -> Global.game.save();
                case 1 -> Global.setState(Global.StateType.MENU, null);
                case 2 -> Global.game.gamePaused = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {

        g2.setColor(Color.BLACK);
        g2.fillRect(dim.x, dim.y, dim.width, dim.height);

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
                    Global.getFont().getSize() * i + Global.getFont().getSize() + dim.y);
        }
    }

    @Override
    public void updateDim() {
        dim = new Rectangle(Window.gamePanel.getWidth()/6,
                            Window.gamePanel.getHeight()/6,
                            Window.gamePanel.getWidth()*2/3,
                            Window.gamePanel.getHeight()*2/3);

        System.out.println("PauseGUI: " + dim);
    }
}
