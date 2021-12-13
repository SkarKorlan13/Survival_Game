package util;

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
}
