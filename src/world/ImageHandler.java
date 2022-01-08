package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings("UnusedAssignment")
public class ImageHandler {

    public static BufferedImage[] tiles_entities;

    //public static BufferedImage ICON; TODO

    //ENTITIES
    public static int PLAYER=0;

    //TILES
    public static int GRASS=1;
    public static int WATER=2;
    public static int FARM_DRY=3;
    public static int FARM_DAMP=4;
    public static int FARM_WET=5;
    public static int TREE_OAK=6;
    public static int TREE_OAK_APPLES=7;
    public static int TREE_PINE=8;
    public static int BUSH=9;
    public static int BUSH_BERRIES=10;

    static {
        try {
            FileInputStream in;

            tiles_entities = new BufferedImage[] {
                    ImageIO.read(in = new FileInputStream("res/entity/Player.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Grass.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Water.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Wet.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Damp.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Dry.png")),
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
