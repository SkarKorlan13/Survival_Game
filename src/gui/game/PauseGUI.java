package gui.game;

import gui.GUI;
import main.Global;
import main.Window;
import util.ControlHandler;
import util.Renderer;

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

        String[] renderLines = lines.clone();
        renderLines[currentLine] = ">" + renderLines[currentLine] + "<";

        Renderer.renderText(dim, Global.getFont(), true, g2, Color.WHITE, renderLines);
    }

    @Override
    public void updateDim() {
        dim = new Rectangle(
                (int)(Global.getMaxTileX()/3 * Global.tileSize),
                (int)(Global.getMaxTileY()/3 * Global.tileSize),
                (int)(Global.getMaxTileX()/3 * Global.tileSize),
                (int)(Global.getMaxTileY()/3 * Global.tileSize)
        );

        System.out.println("PauseGUI: " + dim);
    }
}
