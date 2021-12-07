package entity;

import main.GamePanel;
import util.Direction;

import java.awt.*;

public abstract class Entity {
    public int x, y; //world position in tiles

    public int lastX, lastY; //last world position in tiles

    public int width, height; //size in pixels

    public int moveTime; //number of ticks between each movement

    //public Dimension dimension; //TODO add dimensions

    protected Direction dir;

    public Entity() {
        this.width = GamePanel.tileSize;
        this.height = GamePanel.tileSize;
        dir = new Direction();
    }

    public Direction getDir() {
        return dir;
    }

    public void tick() {

    }

    public void render(Graphics2D g2) {

    }
}
