package world.entity;

import main.GamePanel;
import main.Window;
import util.ControlHandler;
import world.ImageHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private int moveTicks = 0;
    private boolean isMove = false;
    private BufferedImage playerImage;

    public Player() {
        super();
        this.moveTime = 20;
        this.x = 0;
        this.y = 0;
        this.playerImage = ImageHandler.PLAYER;
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
            if (dir.direction == 0 && ControlHandler.UP.down()) {
                y--;
                isMove = false;
            } else if (dir.direction == 2 && ControlHandler.DOWN.down()) {
                y++;
                isMove = false;
            } else if (dir.direction == 3 && ControlHandler.LEFT.down()) {
                x--;
                isMove = false;
            } else if (dir.direction == 1 && ControlHandler.RIGHT.down()) {
                x++;
                isMove = false;
            } else if (ControlHandler.UP.down()) {
                y--;
                isMove = false;
            } else if (ControlHandler.DOWN.down()) {
                y++;
                isMove = false;
            } else if (ControlHandler.LEFT.down()) {
                x--;
                isMove = false;
            } else if (ControlHandler.RIGHT.down()) {
                x++;
                isMove = false;
            }
        }

        //OTHER
    }

    @Override
    public void render(Graphics2D g2) {
        super.render(g2);

        //g2.setColor(Color.WHITE);

        //g2.fillRect(x*Window.tileSize, y*Window.tileSize, width, height);

        g2.drawImage(playerImage, x* GamePanel.tileSize, y*GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
    }
}
