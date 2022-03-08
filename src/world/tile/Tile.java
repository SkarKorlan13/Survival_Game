package world.tile;

import world.WorldObject;
import world.entity.Entity;

public abstract class Tile extends WorldObject implements java.io.Serializable {

    protected int health;

    protected boolean passable;

    protected int progress;

    protected int[][] drops;

    public Tile(String imageID, boolean passable) {
        this.id = imageID;
        this.passable = passable;
    }

    public void interact(Entity e) {
        System.out.println("id: " + id + " | tool: " + e.getCurrentTool().toolType);
        System.out.println(state);
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isPassable() {
        return passable;
    }
}
