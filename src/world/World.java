package world;

import main.Global;
import util.OpenSimplexNoise;
import world.entity.Entity;
import world.entity.Player;
import world.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class World {

    //public List<List<Chunk>> chunks;

    public int[][] groundTiles; //Grass, water, etc.
    public Tile[][] tiles;      //Trees, bushes, etc.
    public Entity[][] entities; //Player, animals, etc.

    public int size;
    public long seed;

    public OpenSimplexNoise noise;


    public World(int size, long seed) {

        this.size = size;
        this.seed = seed;

        this.noise = new OpenSimplexNoise(seed);

        groundTiles = new int[size][size];
        tiles = new Tile[size][size];
        entities = new Entity[size][size];

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

        //GROUND TILES

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double eval = noise.eval((double)x/10, (double)y/10);
                if (eval < -0.5) {
                    groundTiles[y][x] = 1;
                } else {
                    groundTiles[y][x] = 0;
                }
            }
        }

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

        for (Entity[] y : entities) {
            for (Entity e : y) {
                if (e == null) continue;
                e.tick();
            }
        }
    }

    public void update() {
        for (Entity[] y : entities) {
            for (Entity e : y) {
                if (e == null) return;
                e.update();
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
                g2.drawImage(ImageHandler.groundTiles[groundTiles[y + TL.y][x + TL.x]],
                        x* Global.tileSize, y*Global.tileSize,
                        Global.tileSize, Global.tileSize,
                        null);
            }
        }


        //TILES
        /*
        for (int y = 0; y < GamePanel.maxTileY; y++) {
            for (int x = 0; x < GamePanel.maxTileX; x++) {
                g2.drawImage(ImageHandler.images[tiles[y + TL.y][x + TL.x].id],
                        x*GamePanel.tileSize, y*GamePanel.tileSize,
                        GamePanel.tileSize, GamePanel.tileSize,
                        null);
            }
        }
         */

        //ENTITIES
        for (int y = 0; y < Global.maxTileY; y++) {
            for (int x = 0; x < Global.maxTileX; x++) {

                if (entities[y + TL.y][x + TL.x] == null) continue;

                                //get image relating to id of current entity
                g2.drawImage(ImageHandler.entities[entities[y + TL.y][x + TL.x].id],
                        x*Global.tileSize, y*Global.tileSize,
                        Global.tileSize, Global.tileSize,
                        null);
            }
        }
    }

    public void addEntity(Entity e, int x, int y) {
        if (entities[y][x] != null) return;
        entities[y][x] = e;
    }

    //Returns false if newPos is occupied TODO fix this
    public boolean moveEntity(Point oldPos, Point newPos) {
        if (entities[newPos.y][newPos.x] != null) {
            System.out.println(entities[newPos.y][newPos.x]);
            return false;
        }
        entities[newPos.y][newPos.x] = entities[oldPos.y][oldPos.x];
        entities[oldPos.y][oldPos.x] = null;
        return true;
    }
}
