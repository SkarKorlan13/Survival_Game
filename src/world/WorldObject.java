package world;

import main.Global;
import world.entity.Entity;

import java.awt.*;

/*
 *  WorldObject Subclasses:
 *      Tile: "floor" layer of world
 *      TileEntity: "main" layer of world, doesn't move
 *      Entity: "main" layer of world, moves, can be on top of passable TileEntities
 */

public abstract class WorldObject implements java.io.Serializable {

    protected Point pos;

    //0 being default state
    protected int state = 0;

    public abstract void interact(Entity e);

    public String getID() {
        return "getID method not set";
    } //constant for each WorldObject

    abstract void render(Graphics2D g2, Point pos);

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}