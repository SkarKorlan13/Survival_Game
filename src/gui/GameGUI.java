package gui;

import java.awt.*;

public class GameGUI implements GUI {

    public Rectangle dim; //Size of GUI

    public int priority;    //Order in which to render GUI's, from low to high (higher numbers rendered last)

    public GameGUI() {

    }

    public void tick() {

    }

    @Override
    public void render(Graphics2D g2) {

    }
}
