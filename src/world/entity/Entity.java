package world.entity;

import main.Global;
import util.Direction;
import world.WorldObject;
import world.item.Inventory;
import world.item.Tool;
import world.item.Tool_Hand;
import world.tile.Tile_Water;

import java.awt.*;

public abstract class Entity extends WorldObject implements java.io.Serializable {

    protected Point lastPos; //last world position in tiles

    protected int width, height; //size in pixels, unused currently

    protected int moveTime; //number of ticks between each movement

    protected int moveTicks = 0;

    //public Dimension dimension; //TODO add dimensions

    protected Direction dir;

    public Inventory inventory;

    protected Tool currentTool;

    public Entity() {
        this.width = Global.tileSize;
        this.height = Global.tileSize;
        this.lastPos = new Point();
        dir = new Direction();
    }

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
            System.out.println("Move");
            lastPos.setLocation(pos);
            pos.setLocation(newPos);
            Global.game.updateCameraPos(new Point(pos));

            if (Global.game.world.get(0, pos) instanceof Tile_Water) {
                moveTicks -= moveTime; //half move speed on water
            }
        } else {
            System.out.println("New Position Occupied");
        }
    }

    public abstract void tick();

    public abstract void update();

    public Tool getCurrentTool() {
        return currentTool == null ? new Tool_Hand() : currentTool;
    }
}
