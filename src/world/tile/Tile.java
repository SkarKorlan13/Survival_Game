package world.tile;

import world.WorldObject;
import world.entity.Entity;

public abstract class Tile extends WorldObject implements java.io.Serializable {
    public int id;

    public void interact(Entity e) {

    }
}
