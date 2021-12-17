package world.entity;

import main.Global;
import util.Direction;
import world.WorldObject;

import java.awt.*;

public abstract class Entity extends WorldObject implements java.io.Serializable {
    protected Point pos;   //world position in tiles

    protected Point lastPos;   //last world position in tiles

    protected int width, height; //size in pixels

    protected int moveTime; //number of ticks between each movement

    public int id;

    //public Dimension dimension; //TODO add dimensions

    protected Direction dir;

    public Inventory inventory;

    public Tool currentTool;

    public Entity() {
        this.width = Global.tileSize;
        this.height = Global.tileSize;
        dir = new Direction();
    }

    public Direction getDir() {
        return dir;
    }

    public void tick() {

    }

    public void update() {

    }

    public void render(Graphics2D g2) {

    }

    public Tool getCurrentTool() {
        return currentTool == null ? new Tool_Hand() : currentTool;
    }
}
