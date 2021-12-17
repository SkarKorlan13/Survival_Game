package world.tile;

import world.entity.Entity;
import world.entity.Tool;

public class Tile_Grass extends Tile {

    public Tile_Grass(int id) {
        this.id = id;
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);

        if (e.getCurrentTool().toolType == Tool.ToolType.HAND) {
            System.out.println("Hand");
        }
    }
}
