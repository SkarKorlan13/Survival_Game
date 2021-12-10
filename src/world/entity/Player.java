package world.entity;

import main.GamePanel;
import util.ControlHandler;
import world.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static world.ImageHandler.PLAYER;

public class Player extends Entity {

    private int moveTicks = 0;
    private boolean isMove = false;
    private BufferedImage playerImage;

    public Player(int x, int y) {
        super();
        this.moveTime = 20;
        this.x = x;
        this.y = y;
        this.playerImage = ImageHandler.images[PLAYER];
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
            int ny = y;
            int nx = x;
            if (dir.direction == 0 && ControlHandler.UP.down()) {
                ny--;
                isMove = false;
            } else if (dir.direction == 2 && ControlHandler.DOWN.down()) {
                ny++;
                isMove = false;
            } else if (dir.direction == 3 && ControlHandler.LEFT.down()) {
                nx--;
                isMove = false;
            } else if (dir.direction == 1 && ControlHandler.RIGHT.down()) {
                nx++;
                isMove = false;
            } else if (ControlHandler.UP.down()) {
                ny--;
                isMove = false;
            } else if (ControlHandler.DOWN.down()) {
                ny++;
                isMove = false;
            } else if (ControlHandler.LEFT.down()) {
                nx--;
                isMove = false;
            } else if (ControlHandler.RIGHT.down()) {
                nx++;
                isMove = false;
            }

            if (nx != x || ny != y) {
                lastX = x;
                lastY = y;
                x = nx;
                y = ny;
            }
        }

        //OTHER
    }

    @Override
    public void render(Graphics2D g2) {
        super.render(g2);

        //g2.setColor(Color.WHITE);

        //g2.fillRect(x*Window.tileSize, y*Window.tileSize, width, height);

        g2.drawImage(playerImage, GamePanel.tileSize*(GamePanel.maxTileX/2), GamePanel.tileSize*(GamePanel.maxTileY/2), GamePanel.tileSize, GamePanel.tileSize, null);
    }
}
