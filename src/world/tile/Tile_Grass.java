package world.tile;

import world.entity.Entity;

public class Tile_Grass extends Tile {

    @Override
    public void interact(Entity e) {
        super.interact(e);

    }

    @Override
    public String getID() {
        return "Tile_Grass";
    }
}