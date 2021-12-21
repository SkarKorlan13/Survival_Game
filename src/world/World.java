package world;

import main.Global;
import util.OpenSimplexNoise;
import world.entity.Entity;
import world.entity.Player;
import world.tile.*;

import java.awt.*;
import java.util.Random;

public class World implements java.io.Serializable {

    public WorldObject[][][] layers;
    //0: Ground Tiles
    //1: Tiles and Entities
    //2: Flying things etc.

    public Point playerPos = new Point();

    public int size;
    public long seed;

    public String saveName;


    public World(int size, long seed) {

        this.size = size;
        this.seed = seed;

        layers = new WorldObject[3][size][size];

        generate();

        /*
        try {
            FileReader in = new FileReader("res/world/tempWorld.txt");
            BufferedReader read = new BufferedReader(in);

            for (int y = 0; y < tiles.length; y++) {
                String[] tileNums = read.readLine().split(" ");

                for (int x = 0; x < tiles[0].length; x++) {
                    int tileNum = Integer.parseInt(tileNums[x]);
                    groundTiles[y][x] = tileNum;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    public void generate() {
        OpenSimplexNoise noise = new OpenSimplexNoise(seed);
        Random random = new Random(seed);
        //GROUND TILES

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double eval = noise.eval((double)x/10, (double)y/10);

                Tile tile;

                if (eval < -0.5) {
                    tile = new Tile_Water();
                } else {
                    tile = new Tile_Grass();
                }

                layers[0][y][x] = tile;

                System.out.println(tile.getID());
            }
        }

        //TILES
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (layers[0][y][x] instanceof Tile_Water) continue;

                double eval = noise.eval((double)x/10, (double)y/10);

                Tile tile;

                if (eval > 0.5) {
                    tile = random.nextInt(16) < 1 ? new Tile_Tree_Oak_Apples() : new Tile_Tree_Oak();
                } else {
                    tile = random.nextInt(16) < 1 ? new Tile_Bush() : null;
                }

                if (tile != null) {
                    layers[1][y][x] = tile;
                }
            }
        }
    }

    public void tick() {
        for (WorldObject[] y : layers[1]) {
            for (WorldObject w : y) {
                if (w == null) continue;
                if (w instanceof Entity) {
                    ((Entity) w).tick();
                }
            }
        }
    }

    public void update() {
        for (WorldObject[] y : layers[1]) {
            for (WorldObject w : y) {
                if (w == null) continue;
                if (w instanceof Entity) {
                    ((Entity) w).update();
                }
            }
        }
    }

    public void render(Graphics2D g2) {


        Point TL = new Point(); //Top Left
        //Point BR = new Point(); //Bottom Right
        TL.x = Global.game.camera.x - (Global.maxTileX/2);
        TL.y = Global.game.camera.y - (Global.maxTileY/2);
        //BR.x = TL.x + Global.maxTileX;
        //BR.y = TL.y + Global.maxTileY;

        //GROUND TILES
        for (int y = 0; y < Global.maxTileY; y++) {
            for (int x = 0; x < Global.maxTileX; x++) {
                if (layers[0][y + TL.y][x + TL.x] == null) continue;

                g2.drawImage(ImageHandler.groundTiles[layers[0][y + TL.y][x + TL.x].getID()],
                        x* Global.tileSize, y*Global.tileSize,
                        Global.tileSize, Global.tileSize,
                        null);
            }
        }


        //TILES/ENTITIES
        for (int y = 0; y < Global.maxTileY; y++) {
            for (int x = 0; x < Global.maxTileX; x++) {
                if (layers[1][y + TL.y][x + TL.x] == null) continue;

                g2.drawImage(ImageHandler.tiles_entities[layers[1][y + TL.y][x + TL.x].getID()],
                        x*Global.tileSize, y*Global.tileSize,
                        Global.tileSize, Global.tileSize,
                        null);
            }
        }
    }

    public boolean addEntity(Entity e, Point pos) {
        if (layers[1][pos.y][pos.x] != null) return false;
        layers[1][pos.y][pos.x] = e;
        if (e instanceof Player) {
            playerPos.setLocation(pos.x, pos.y);
        }
        return true;
    }

    public boolean forceAddEntity(Entity e, Point pos) {
        layers[1][pos.y][pos.x] = null;
        return addEntity(e, pos);
    }


    //Returns false if newPos is occupied
    public boolean moveEntity(Point oldPos, Point newPos) {
        if (!passable(newPos)) {
            System.out.println(layers[1][newPos.y][newPos.x]);
            return false;
        }

        layers[1][newPos.y][newPos.x] = layers[1][oldPos.y][oldPos.x];
        layers[1][oldPos.y][oldPos.x] = null;

        if (layers[1][newPos.y][newPos.x] instanceof Player) {
            playerPos.setLocation(newPos);
        }

        return true;
    }

    public boolean passable(Point pos) {
        return layers[1][pos.y][pos.x] == null;
    }

    public void interact(Point pos, Entity e) {
        if (layers[1][pos.y][pos.x] != null) {
            layers[1][pos.y][pos.x].interact(e);
        } else if (layers[0][pos.y][pos.x] != null) {
            layers[0][pos.y][pos.x].interact(e);
        }
    }

    public WorldObject getWorldObject(int layer, Point pos) {
        return layers[layer][pos.y][pos.x];
    }
}
