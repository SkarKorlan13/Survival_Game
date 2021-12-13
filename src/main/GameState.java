package main;

import world.World;
import world.entity.Player;

import java.awt.*;

public class GameState implements State{

    //public GUIHandler guiHandler;

    public World world;

    public int worldSize = 32;  //Size of world in tiles, last tile is worldSize - 1 b/c starts at 0
    public Rectangle worldBounds;

    public Point camera = new Point();  //Location of camera - usually centered on player
    public Rectangle cameraBounds;  //Area in which the camera can be without "rendering" areas outside the defined world size


    public GameState() {

    }

    public void init() {
        cameraBounds = new Rectangle(Global.maxTileX/2, Global.maxTileY/2,
                worldSize - Global.maxTileX + 1, worldSize - Global.maxTileY + 1);
        //Rectangle.contains checks if point is < bounds, not <= so add one to make it work

        //System.out.println("TL: " + cameraBounds.getLocation() + " BR: " + (new Point((int) cameraBounds.getMaxX(), (int) cameraBounds.getMaxY())));

        worldBounds = new Rectangle(0, 0, worldSize, worldSize);
        world = new World(worldSize, 101299);
        Player player = new Player(worldSize/2, worldSize/2);
        updateCameraPos(player.pos);
        world.addEntity(player, worldSize/2, worldSize/2);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void update() {
        world.update();
    }

    @Override
    public void render(Graphics2D g2) {
        world.render(g2);
    }

    public void updateCameraPos(Point cameraPos) {
        if (cameraPos.x < cameraBounds.x) {
            cameraPos.x = cameraBounds.x;
        }
        if (cameraPos.y < cameraBounds.y) {
            cameraPos.y = cameraBounds.y;
        }
        //-1's are because of Rectangle.contains issue stated in init
        if (cameraPos.x > cameraBounds.getMaxX() - 1) {
            cameraPos.x = (int) cameraBounds.getMaxX() -1;
        }
        if (cameraPos.y > cameraBounds.getMaxY() - 1) {
            cameraPos.y = (int) cameraBounds.getMaxY() - 1;
        }
        if (cameraBounds.contains(cameraPos)) {
            Global.game.camera.setLocation(cameraPos);
            //return true;
        } else {
            System.out.println("Error Positioning Camera: " + cameraPos);
            //return false;
        }
    }
}
