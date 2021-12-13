package world.entity;

import main.Global;
import util.Direction;

import java.awt.*;

public abstract class Entity implements java.io.Serializable {
    public Point pos;   //world position in tiles

    public Point lastPos;   //last world position in tiles

    public int width, height; //size in pixels

    public int moveTime; //number of ticks between each movement

    public int id;

    public boolean passable;

    //public Dimension dimension; //TODO add dimensions

    protected Direction dir;

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
}
