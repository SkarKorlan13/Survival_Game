package world.tile;

import world.WorldObject;
import world.entity.Entity;

public abstract class Tile extends WorldObject implements java.io.Serializable {

    @Override
    public void interact(Entity e) {
        //System.out.println("id: " + getID() + " | tool: " + e.getCurrentTool().toolType);
        //System.out.println(state);
    }

    public double getSpeedModifier() {
        return 1; //default value, larger number = slower movement
    }
}
