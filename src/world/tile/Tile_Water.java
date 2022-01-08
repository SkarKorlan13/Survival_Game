package world.tile;

import world.ImageHandler;
import world.entity.Entity;

public class Tile_Water extends Tile {

    public Tile_Water() {
        super(ImageHandler.WATER);
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);

    }
}
