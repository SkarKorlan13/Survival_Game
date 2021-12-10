package world;

import main.GamePanel;
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

    BufferedImage image;

    public int size;
    public long seed;
    public Player player;

    public World(int size, long seed, Player player) {

        this.size = size;
        this.seed = seed;
        this.player = player;

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

        //TILES

        OpenSimplexNoise noise = new OpenSimplexNoise(seed);

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
    }

    public void tick() {
        player.tick();
    }

    public void render(Graphics2D g2) {


        Point TL = new Point(); //Top Left
        //Point BR = new Point(); //Bottom Right
        TL.x = player.x - (GamePanel.maxTileX/2);
        TL.y = player.y - (GamePanel.maxTileY/2);
        //BR.x = TL.x + GamePanel.maxTileX;
        //BR.y = TL.y + GamePanel.maxTileY;

        //GROUND TILES
        for (int y = 0; y < GamePanel.maxTileY; y++) {
            for (int x = 0; x < GamePanel.maxTileX; x++) {
                g2.drawImage(ImageHandler.images[groundTiles[y + TL.y][x + TL.x]],
                        x*GamePanel.tileSize, y*GamePanel.tileSize,
                        GamePanel.tileSize, GamePanel.tileSize,
                        null);
            }
        }


        //TILES


        //ENTITIES
        player.render(g2);


        /*
        for (int y = 1; y < 13; y++) {
            g2.drawImage(ImageHandler.BUSH, 0,    GamePanel.tileSize*y,  GamePanel.tileSize, GamePanel.tileSize, null);
        }

        g2.drawImage(ImageHandler.BUSH, 0,    0,  GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.BUSH_BERRIES, GamePanel.tileSize,   0,  GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.FARM_DAMP, GamePanel.tileSize*2, 0,  GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.FARM_DRY, GamePanel.tileSize*3, 0,  GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.FARM_WET, GamePanel.tileSize*4, 0,  GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.GRASS, 0,    GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.TREE_OAK, GamePanel.tileSize,   GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.TREE_OAK_APPLES, GamePanel.tileSize*2, GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.TREE_PINE, GamePanel.tileSize*3, GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
        g2.drawImage(ImageHandler.WATER, GamePanel.tileSize*4, GamePanel.tileSize, GamePanel.tileSize, GamePanel.tileSize, null);
         */
    }
}
