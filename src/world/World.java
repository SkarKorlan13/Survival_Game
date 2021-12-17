package world;

import main.Global;
import util.OpenSimplexNoise;
import world.entity.Entity;
import world.entity.Player;
import world.tile.*;

import java.awt.*;

public class World implements java.io.Serializable {

    public WorldObject[][] groundTiles;     //Grass, water, etc.
    public WorldObject[][] tiles_entities;  //Trees, bushes, entities, etc.

    public Point playerPos = new Point();

    public int size;
    public long seed;

    public String saveName;


    public World(int size, long seed) {

        this.size = size;
        this.seed = seed;

        groundTiles = new WorldObject[size][size];
        tiles_entities = new WorldObject[size][size];

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
        //GROUND TILES

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double eval = noise.eval((double)x/10, (double)y/10);
                if (eval < -0.5) {
                    groundTiles[y][x] = new Tile_Water();
                } else {
                    groundTiles[y][x] = new Tile_Grass();
                }
            }
        }

        groundTiles[size/2][size/2] = new Tile_Water();

        //TILES
        /*
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double eval = noise.eval((double)x/10, (double)y/10);
                if (eval < -0.5) {
                    tiles[y][x] = Tile.TILES[0];
                } else {
                    tiles[y][x] = 0;
                }
            }
        }
         */
    }

    public void tick() {
        for (WorldObject[] y : tiles_entities) {
            for (WorldObject w : y) {
                if (w == null) continue;
                if (w instanceof Entity) {
                    ((Entity) w).tick();
                }
            }
        }
    }

    public void update() {
        for (WorldObject[] y : tiles_entities) {
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
                if (groundTiles[y + TL.y][x + TL.x] instanceof Tile_Water) {
                    //System.out.println("water" + groundTiles[y + TL.y][x + TL.x].id);
                    //TODO this stuff isn't working, only rendering grass, wrong id's, etc.
                }
                g2.drawImage(ImageHandler.groundTiles[groundTiles[y + TL.y][x + TL.x].id],
                        x* Global.tileSize, y*Global.tileSize,
                        Global.tileSize, Global.tileSize,
                        null);
            }
        }


        //TILES/ENTITIES
        for (int y = 0; y < Global.maxTileY; y++) {
            for (int x = 0; x < Global.maxTileX; x++) {
                if (tiles_entities[y + TL.y][x + TL.x] == null) continue;
                g2.drawImage(ImageHandler.tiles_entities[tiles_entities[y + TL.y][x + TL.x].id],
                        x*Global.tileSize, y*Global.tileSize,
                        Global.tileSize, Global.tileSize,
                        null);
            }
        }
    }

    public boolean addEntity(Entity e, Point pos) {
        if (tiles_entities[pos.y][pos.x] != null) return false;
        tiles_entities[pos.y][pos.x] = e;
        if (e instanceof Player) {
            playerPos.setLocation(pos.x, pos.y);
        }
        return true;
    }

    public boolean forceAddEntity(Entity e, Point pos) {
        tiles_entities[pos.y][pos.x] = null;
        return addEntity(e, pos);
    }


    //Returns false if newPos is occupied
    public boolean moveEntity(Point oldPos, Point newPos) {
        if (!passable(newPos)) {
            System.out.println(tiles_entities[newPos.y][newPos.x]);
            return false;
        }

        tiles_entities[newPos.y][newPos.x] = tiles_entities[oldPos.y][oldPos.x];
        tiles_entities[oldPos.y][oldPos.x] = null;

        if (tiles_entities[newPos.y][newPos.x] instanceof Player) {
            playerPos.setLocation(newPos);
        }

        return true;
    }

    public boolean passable(Point pos) {
        return tiles_entities[pos.y][pos.x] == null;
    }

    public void interact(Point pos, Entity e) {
        if (tiles_entities[pos.y][pos.x] != null) {
            tiles_entities[pos.y][pos.x].interact(e);
        } else if (groundTiles[pos.y][pos.x] != null) {
            groundTiles[pos.y][pos.x].interact(e);
        }
    }
}
