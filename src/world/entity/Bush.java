package world.entity;

import world.entity.Entity;

public class Bush extends Entity {

    @Override
    public String getID() {
        return "Bush";
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);
    }

    @Override
    boolean isPassable() {
        return true;
    }
}
