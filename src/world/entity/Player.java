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
        if (ControlHandler.W.pressedTick() && dir.direction != Direction.UP) {
            dir.direction = Direction.UP;
            isMove = false;
            moveTicks = moveTime / 2;
        } else if (ControlHandler.S.pressedTick() && dir.direction != Direction.DOWN) {
            dir.direction = Direction.DOWN;
            isMove = false;
            moveTicks = moveTime / 2;
        } else if (ControlHandler.A.pressedTick() && dir.direction != Direction.LEFT) {
            dir.direction = Direction.LEFT;
            isMove = false;
            moveTicks = moveTime / 2;
        } else if (ControlHandler.D.pressedTick() && dir.direction != Direction.RIGHT) {
            dir.direction = Direction.RIGHT;
            isMove = false;
            moveTicks = moveTime / 2;
        }

        //UPDATE POSITION
        else if (isMove) {
            Point newPos = new Point(pos);

            if (dir.direction == Direction.UP && ControlHandler.W.down()) {
                newPos.y--;
                isMove = false;
            } else if (dir.direction == Direction.DOWN && ControlHandler.S.down()) {
                newPos.y++;
                isMove = false;
            } else if (dir.direction == Direction.LEFT && ControlHandler.A.down()) {
                newPos.x--;
                isMove = false;
            } else if (dir.direction == Direction.RIGHT && ControlHandler.D.down()) {
                newPos.x++;
                isMove = false;
            }

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