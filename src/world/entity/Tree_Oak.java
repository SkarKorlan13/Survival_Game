package world.entity;

import main.Global;
import world.entity.Entity;
import world.item.Tool;

import java.awt.*;

public class Tree_Oak extends Entity {

    @Override
    public String getID() {
        return "Bush";
    }

    public Tree_Oak(Point pos) {
        super(pos);
        //this.health = 20;
    }

    @Override
    public void interact(Entity e) {
        //super.interact(e);
        /*
        if (e.getCurrentTool().toolType == Tool.ToolType.HAND) {
            health -= 4;
        }

        if (health <= 0) {
            System.out.println("Tree destroyed");
            Global.game.world.removeTile_Entity(e.getFacing());
        }
         */
    }

    @Override
    boolean isPassable() {
        return false;
    }
}