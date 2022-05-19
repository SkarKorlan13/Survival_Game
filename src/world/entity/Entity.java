package world.entity;

import world.WorldObject;
import java.awt.*;

public abstract class Entity extends WorldObject implements java.io.Serializable {

    protected Point pos; //world position in tiles

    //protected Point lastPos; //last world position in tiles

    //protected int width, height; //size in pixels, unused currently

    //protected int moveTime; //number of ticks between each movement

    //protected int moveTicks = 0;

    //public Dimension dimension; //TODO add dimensions

    //protected Direction dir;

    //protected Inventory inventory;

    //protected Tool currentTool;

    public Entity(Point pos) {
        this.pos = new Point(pos);
    }

    public abstract void update();

    /*
    public Direction getDir() {
        return dir;
    }

    public Point getFacing() {
        Point facing = new Point(pos);
        Point dirFacing = dir.getFacing();
        facing.translate(dirFacing.x, dirFacing.y);
        return facing;
    }

    public void move(Point newPos) {
        if (Global.game.world.move(pos, newPos)) {
            System.out.println("Move: " + this);
            lastPos.setLocation(pos);
            pos.setLocation(newPos);
            Global.game.updateCameraPos(new Point(pos));

            if (Global.game.world.get(0, pos) instanceof Tile) {
                moveTicks -= moveTime; //half move speed on water
            }
        } else {
            System.out.println("New Position Occupied");
        }
    }

    public Tool getCurrentTool() {
        return currentTool == null ? new Tool_Hand() : currentTool;
    }

    public String[] getInventory() {
        return inventory.getItemsArray();
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
     */
}
