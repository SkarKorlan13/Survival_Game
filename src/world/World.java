package world;

import main.GamePanel;
import world.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.List;

public class World {

    //public List<List<Chunk>> chunks;
    public int[][] groundTiles = new int[GamePanel.maxTileY][GamePanel.maxTileX];

    public Tile[][] tiles = new Tile[GamePanel.maxTileY][GamePanel.maxTileX];

    BufferedImage image;

    public World() {
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


    }

    public void render(Graphics2D g2) {

        //GROUND TILES
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                g2.drawImage(ImageHandler.images[groundTiles[y][x]],
                        x*GamePanel.tileSize, y*GamePanel.tileSize,
                        GamePanel.tileSize, GamePanel.tileSize,
                        null);
            }
        }




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
