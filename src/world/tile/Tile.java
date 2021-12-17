package world.tile;

import world.WorldObject;
import world.entity.Entity;

public abstract class Tile extends WorldObject implements java.io.Serializable {
    protected int id;

    public void interact(Entity e) {
        System.out.println(id);
    }

    public int getID() {
        return id;
    }
}
