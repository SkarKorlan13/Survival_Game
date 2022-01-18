package world.tile;

import world.ImageHandler;
import world.entity.Entity;

public class Tile_Grass extends Tile {

    public Tile_Grass() {
        super(ImageHandler.GRASS, true);
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);
    }
}