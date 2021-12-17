package world.entity;

import main.Global;
import util.ControlHandler;
import world.ImageHandler;
import world.tile.Tile_Water;

import java.awt.*;

public class Player extends Entity {

    private int moveTicks = 0;
    private boolean isMove = false;

    public Player(Point pos) {
        super();
        this.moveTime = 20;
        this.pos = new Point(pos);
        this.lastPos = new Point(pos);

        this.id = ImageHandler.PLAYER;
    }

    @Override
    public void tick() {
        super.tick();

        //----------------MOVE----------------//

        if (!isMove) moveTicks++;
        if (moveTicks >= moveTime) {
            isMove = true;
            moveTicks = 0;
        }

        //UPDATE DIRECTION
        if (ControlHandler.UP.pressedTick()) dir.direction = 0;
        else if (ControlHandler.DOWN.pressedTick()) dir.direction = 2;
        else if (ControlHandler.LEFT.pressedTick()) dir.direction = 3;
        else if (ControlHandler.RIGHT.pressedTick()) dir.direction = 1;

        //UPDATE POSITION
        if (isMove) {
            Point newPos = new Point(pos);

            if (dir.direction == 0 && ControlHandler.UP.down()) {
                newPos.y--;
                isMove = false;
            } else if (dir.direction == 2 && ControlHandler.DOWN.down()) {
                newPos.y++;
                isMove = false;
            } else if (dir.direction == 3 && ControlHandler.LEFT.down()) {
                newPos.x--;
                isMove = false;
            } else if (dir.direction == 1 && ControlHandler.RIGHT.down()) {
                newPos.x++;
                isMove = false;
            } else if (ControlHandler.UP.down()) {
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

            if (!(newPos.x == pos.x && newPos.y == pos.y)) {    //TODO Fix this massive mess
                if (Global.game.worldBounds.contains(newPos)) {
                    System.out.println("Move");
                    lastPos.setLocation(pos);
                    pos.setLocation(newPos);

                    if (!Global.game.world.moveEntity(lastPos, pos)) {
                        System.out.println("Player Move Failed");
                    }

                    Global.game.updateCameraPos(newPos);
                } else {
                    System.out.println("No Move");
                }


                //if (Global.game.worldSize - pos.x < Global.maxTileX/2)
                //Global.game.worldSize - newPos.x <= Global.maxTileX/2 || Global.game.worldSize - newPos.y <= Global.maxTileY/2
                //updateCameraPos();
            }
        }

        //INTERACT
        if (ControlHandler.INTERACT.pressedTick()) {
            Point facing = new Point(pos);
            Point dirFacing = dir.getFacing();
            facing.translate(dirFacing.x, dirFacing.y);
            //System.out.println(facing);
            Global.game.world.interact(facing, this);
        }

        //OTHER
    }

    @Override
    public void render(Graphics2D g2) {
        super.render(g2);

        //g2.setColor(Color.WHITE);

        //g2.fillRect(x*Window.tileSize, y*Window.tileSize, width, height);

        g2.drawImage(ImageHandler.tiles_entities[this.id], Global.tileSize*(Global.maxTileX/2), Global.tileSize*(Global.maxTileY/2), Global.tileSize, Global.tileSize, null);
    }

    @Override
    public void interact(Entity e) {
        //MAYBE SOMETHING LATER
    }
}
