package world.tile_entity;

import main.Global;
import world.entity.Entity;
import world.item.Tool;

public class Tree_Oak extends Tile_Entity {

    @Override
    public String getID() {
        return "Bush";
    }

    public Tree_Oak() {
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
            Global.game.world.removeTile_Entity(e.getFacing());
        }
    }
}