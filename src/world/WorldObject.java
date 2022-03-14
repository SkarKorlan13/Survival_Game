package world;

import main.Global;
import world.entity.Entity;

import java.awt.*;

public abstract class WorldObject implements java.io.Serializable {

    protected String id; //is also imageID of tile

    //0 being default state
    protected int state = 0;

    public abstract void interact(Entity e);

    public void render(Graphics2D g2, Point pos) {
        g2.drawImage(ImageHandler.getImage(id), pos.x * Global.tileSize, pos.y * Global.tileSize, Global.tileSize, Global.tileSize, null);
    }

    public String getID() {
        return id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}