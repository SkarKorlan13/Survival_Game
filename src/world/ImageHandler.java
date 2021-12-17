package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings("UnusedAssignment")
public class ImageHandler {

    public static BufferedImage[] groundTiles;
    public static BufferedImage[] tiles_entities;

    //public static BufferedImage ICON; TODO

    //GROUND TILES
    public static int GRASS=0;
    public static int WATER=1;
    public static int FARM_WET=2;
    public static int FARM_DAMP=3;
    public static int FARM_DRY=4;

    //ENTITIES
    public static int PLAYER=0;

    //TILES
    public static int TREE_OAK=1;
    public static int TREE_OAK_APPLES=2;
    public static int TREE_PINE=3;
    public static int BUSH=4;
    public static int BUSH_BERRIES=5;

    static {
        try {
            FileInputStream in;

            groundTiles = new BufferedImage[] {
                    ImageIO.read(in = new FileInputStream("res/tile/Grass.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Water.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Wet.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Damp.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Dry.png")),
            };

            tiles_entities = new BufferedImage[] {
                    ImageIO.read(in = new FileInputStream("res/entity/Player.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Tree_Oak.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Tree_Oak_Apples.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Tree_Pine.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Bush.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Bush_Berries.png")),
            };

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
