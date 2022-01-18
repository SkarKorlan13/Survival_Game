package util;

import java.awt.*;

public class Direction implements java.io.Serializable {
    public int direction;

    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int RIGHT = 1;
    public static final int LEFT = 3;


    //    0
    //  3 X 1
    //    2

    public Direction() {
        this(0);
    }

    public Direction(int direction) {
        this.direction = direction;
    }

    public int getFlippedY() {
        return direction == 0 ? 2 : 0;
    }

    public void flipY() {
        this.direction = getFlippedY();
    }

    public int getFlippedX() {
        return direction == 1 ? 3 : 1;
    }

    public void flipX() {
        this.direction = getFlippedX();
    }

    public Point getFacing() {
        Point facing = new Point();
        switch (direction) {
            case UP -> facing.y = -1;
            case RIGHT -> facing.x = 1;
            case DOWN -> facing.y = 1;
            case LEFT -> facing.x = -1;
        }
        return facing;
    }
}
