package world.tile;

import world.ImageHandler;
import world.entity.Entity;

public class Tile_Bush extends Tile {

    public Tile_Bush() {
        super(ImageHandler.BUSH, false);
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);

    }
}
