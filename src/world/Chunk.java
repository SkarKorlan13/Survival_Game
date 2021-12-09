package world;

import world.tile.Tile;

import java.io.Serializable;

public class Chunk implements Serializable {
    public Tile[][] tiles;

    public Chunk(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
