package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageHandler {

    public static BufferedImage[] images;

    //public static BufferedImage ICON; TODO

    public static int PLAYER=10;

    public static int BUSH=5;
    public static int BUSH_BERRIES=6;
    public static int FARM_DAMP=8;
    public static int FARM_DRY=9;
    public static int FARM_WET=7;
    public static int GRASS=0;
    public static int TREE_OAK=2;
    public static int TREE_OAK_APPLES=3;
    public static int TREE_PINE=4;
    public static int WATER=1;

    static {
        try {
            FileInputStream in;


            //noinspection UnusedAssignment
            images = new BufferedImage[] {
                    //TILES
                    ImageIO.read(in = new FileInputStream("res/tile/Grass.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Water.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Tree_Oak.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Tree_Oak_Apples.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Tree_Pine.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Bush.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Bush_Berries.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Wet.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Damp.png")),
                    ImageIO.read(in = new FileInputStream("res/tile/Farm_Dry.png")),

                    //ENTITIES
                    ImageIO.read(in = new FileInputStream("res/entity/Player.png"))
            };



            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
