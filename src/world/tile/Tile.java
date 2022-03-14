package world.tile;

import world.WorldObject;
import world.entity.Entity;

public abstract class Tile extends WorldObject implements java.io.Serializable {

    private int speedModifier = 1;

    public Tile(String id) {
        this.id = id;
    }

    public void interact(Entity e) {
        System.out.println("id: " + id + " | tool: " + e.getCurrentTool().toolType);
        System.out.println(state);
    }

    public int getSpeedModifier() {
        return speedModifier;
    }

    public void setSpeedModifier(int speedModifier) {
        this.speedModifier = speedModifier;
    }
}
