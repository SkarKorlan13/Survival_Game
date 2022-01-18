package world.tile;

import main.Global;
import world.ImageHandler;
import world.entity.Entity;
import world.item.Tool;

public class Tile_Tree_Oak extends Tile {

    public Tile_Tree_Oak() {
        super(ImageHandler.TREE_OAK, false);
        this.health = 20;
    }

    @Override
    public void interact(Entity e) {
        super.interact(e);

        if (e.getCurrentTool().toolType == Tool.ToolType.HAND) {
            health -= 4;
        }

        if (health <= 0) {
            System.out.println("Tree destroyed");
            Global.game.world.remove(e.getFacing(), 1);
        }
    }
}