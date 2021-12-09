package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageHandler {

    public static BufferedImage[] images;

    public static BufferedImage ICON;

    public static BufferedImage PLAYER;

    public static BufferedImage BUSH;
    public static BufferedImage BUSH_BERRIES;
    public static BufferedImage FARM_DAMP;
    public static BufferedImage FARM_DRY;
    public static BufferedImage FARM_WET;
    public static BufferedImage GRASS;
    public static BufferedImage TREE_OAK;
    public static BufferedImage TREE_OAK_APPLES;
    public static BufferedImage TREE_PINE;
    public static BufferedImage WATER;

    static {
        try {
            FileInputStream in;

            PLAYER = ImageIO.read(in = new FileInputStream("res/entity/Player.png"));

            images = new BufferedImage[] {
                    GRASS = ImageIO.read(in = new FileInputStream("res/tile/Grass.png")),
                    WATER = ImageIO.read(in = new FileInputStream("res/tile/Water.png")),
                    TREE_OAK = ImageIO.read(in = new FileInputStream("res/tile/Tree_Oak.png")),
                    TREE_OAK_APPLES = ImageIO.read(in = new FileInputStream("res/tile/Tree_Oak_Apples.png")),
                    TREE_PINE = ImageIO.read(in = new FileInputStream("res/tile/Tree_Pine.png")),
                    BUSH = ImageIO.read(in = new FileInputStream("res/tile/Bush.png")),
                    BUSH_BERRIES = ImageIO.read(in = new FileInputStream("res/tile/Bush_Berries.png")),
                    FARM_DRY = ImageIO.read(in = new FileInputStream("res/tile/Farm_Wet.png")),
                    FARM_DAMP = ImageIO.read(in = new FileInputStream("res/tile/Farm_Damp.png")),
                    FARM_WET = ImageIO.read(in = new FileInputStream("res/tile/Farm_Dry.png"))
            };



            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
