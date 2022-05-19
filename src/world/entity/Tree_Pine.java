package world.entity;

import world.entity.Entity;

public class Tree_Pine extends Entity {

    @Override
    public String getID() {
        return "Tree_Pine";
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);
    }

    @Override
    boolean isPassable() {
        return false;
    }
}
