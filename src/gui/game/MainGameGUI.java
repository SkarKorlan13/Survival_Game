package gui.game;

import gui.GUI;
import gui.menu.MenuGUI;
import main.Global;
import main.Window;
import util.Renderer;

import java.awt.*;

public class MainGameGUI implements GUI {

    private static Rectangle dim;

    private GUI[] openGUIs; //Order of menus opened

    private int index=0;

    public enum GameGUIType { //same as MenuType: currently unused but may be useful later
        Inventory,
        Crafting,
        Main
    }

    public MainGameGUI() {
        openGUIs = new GUI[GameGUIType.values().length];
        openGUIs[0] = new MainGUI();
        updateDim();
    }

    public void nextGUI(GUI GUI) {
        index++;
        openGUIs[index] = GUI;
    }

    public void previousGUI() {
        if (index == 0) return;
        openGUIs[index] = null;
        index--;
    }

    @Override
    public void tick() {
        openGUIs[index].tick();
    }

    @Override
    public void render(Graphics2D g2) {
        //g2.setColor(Color.PINK);
        //g2.fillRect(dim.x, dim.y, dim.width, dim.height);

        //openGUIs[index].render(g2);

        Renderer.renderText(dim, Global.getFont(30), false, g2, Color.WHITE, ((MenuGUI) openGUIs[index]).getRenderText());
    }

    @Override
    public void updateDim() {
        dim = new Rectangle(Global.tileSize * Global.maxTileX, 0,
                            Window.gamePanel.getWidth()-(Global.tileSize * Global.maxTileX),
                            Window.gamePanel.getHeight());

        System.out.println("mainGUIHandler: " + dim);
    }
}
