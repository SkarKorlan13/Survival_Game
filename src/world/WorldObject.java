package world;

import main.Global;
import world.entity.Entity;

import java.awt.*;

public abstract class WorldObject implements java.io.Serializable {

    protected int id;

    protected int worldID;

    protected Point pos;

    protected boolean passable;

    //0 being default state
    protected int state = 0;

    public abstract void interact(Entity e);

    public void render(Graphics2D g2, Point pos) {
        g2.drawImage(ImageHandler.tiles_entities[getStateID()], pos.x * Global.tileSize, pos.y * Global.tileSize, Global.tileSize, Global.tileSize, null);
    }

    public int getID() {
        return id;
    }

    public int getWorldID() {
        return worldID;
    }

    public void setWorldID(int worldID) {
        this.worldID = worldID;
    }

    public int getStateID() {
        return id + state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Point getPos() {
        return new Point(pos);
    }

    public void setPos(Point pos) {
        this.pos = new Point(pos);
    }

    public boolean isPassable() {
        return passable;
    }
}