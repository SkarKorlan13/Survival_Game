package world.tile;

import world.ImageHandler;
import world.entity.Entity;

public class Tile_Farm extends Tile {

    public Tile_Farm() {
        super(ImageHandler.FARM_DRY, true);
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);

    }
}
