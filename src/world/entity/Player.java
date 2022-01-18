package world.entity;

import main.Global;
import util.ControlHandler;
import util.Direction;
import world.ImageHandler;

import java.awt.*;

public class Player extends Entity {

    private boolean isMove = false;

    public Player() {
        super();
        this.moveTime = 20;
        this.id = ImageHandler.PLAYER;
    }

    @Override
    public void tick() {

        //----------------MOVE----------------//

        if (!isMove) moveTicks++;
        if (moveTicks >= moveTime) {
            isMove = true;
            moveTicks = 0;
        }

        //UPDATE DIRECTION
        if (ControlHandler.UP.pressedTick()) dir.direction = Direction.UP;
        else if (ControlHandler.DOWN.pressedTick()) dir.direction = Direction.DOWN;
        else if (ControlHandler.LEFT.pressedTick()) dir.direction = Direction.LEFT;
        else if (ControlHandler.RIGHT.pressedTick()) dir.direction = Direction.RIGHT;

        //UPDATE POSITION
        if (isMove) {
            Point newPos = new Point(pos);

            if (dir.direction == Direction.UP && ControlHandler.UP.down()) {
                newPos.y--;
                isMove = false;
            } else if (dir.direction == Direction.DOWN && ControlHandler.DOWN.down()) {
                newPos.y++;
                isMove = false;
            } else if (dir.direction == Direction.LEFT && ControlHandler.LEFT.down()) {
                newPos.x--;
                isMove = false;
            } else if (dir.direction == Direction.RIGHT && ControlHandler.RIGHT.down()) {
                newPos.x++;
                isMove = false;
            }
            /*
            else if (ControlHandler.UP.down()) {
                newPos.y--;
                isMove = false;
            } else if (ControlHandler.DOWN.down()) {
                newPos.y++;
                isMove = false;
            } else if (ControlHandler.LEFT.down()) {
                newPos.x--;
                isMove = false;
            } else if (ControlHandler.RIGHT.down()) {
                newPos.x++;
                isMove = false;
            }
             */

            if (!(newPos.equals(pos))) {
                if (Global.game.worldBounds.contains(newPos)) {
                    super.move(newPos);
                } else {
                    System.out.println("Edge of World");
                }
            }
        }

        //----------------INTERACT----------------//
        if (ControlHandler.INTERACT.pressedTick()) {
            Global.game.world.interact(getFacing(), this);
        }

        //OTHER
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2, Point pos) {
        super.render(g2, pos);

        Point facing = dir.getFacing();
        facing.translate(pos.x, pos.y);
        g2.setColor(Color.WHITE);
        g2.drawRect(facing.x * Global.tileSize, facing.y * Global.tileSize, Global.tileSize, Global.tileSize);
        //g2.drawRect(facing.x, facing.y, (int) (Global.tileSize*.9), (int) (Global.tileSize*.9));
    }

    @Override
    public void interact(Entity e) {
        //MAYBE SOMETHING LATER
    }
}