package world.tile_entity;

import world.entity.Entity;

public class Bush extends Tile_Entity {

    @Override
    public String getID() {
        return "Bush";
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);
    }
}
