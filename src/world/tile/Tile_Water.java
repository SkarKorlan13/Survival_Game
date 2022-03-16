package world.tile;

import world.entity.Entity;

public class Tile_Water extends Tile {

    @Override
    public void interact(Entity e) {
        super.interact(e);

    }

    @Override
    public String getID() {
        return "Tile_Water";
    }

    @Override
    public double getSpeedModifier() {
        return 2;
    }
}
