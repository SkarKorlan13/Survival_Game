package world;

import main.Global;
import world.entity.Entity;

import java.awt.*;

public abstract class WorldObject implements java.io.Serializable {

    //0 being default state
    protected int state = 0;

    public abstract void interact(Entity e);

    public String getID() {
        return "getID method not set";
    } //constant for each WorldObject

    public void render(Graphics2D g2, Point pos) {
        g2.drawImage(ImageHandler.getImage(getID()), pos.x * Global.tileSize, pos.y * Global.tileSize, Global.tileSize, Global.tileSize, null);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}