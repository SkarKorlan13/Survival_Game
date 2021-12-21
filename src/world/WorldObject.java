package world;

import world.entity.Entity;

public abstract class WorldObject {

    public int id;

    public abstract void interact(Entity e);

    public int getID() {
        return id;
    }
}
