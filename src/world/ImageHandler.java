package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

public class ImageHandler {

    private static HashMap<String, BufferedImage> images;

    public static BufferedImage getImage(String imageName) {
        return images.get(imageName);
    }

    public static void initializeImages() {
        images = new HashMap<>();
        loadImages(new File("res"));
    }

    private static void loadImages(File file) {
        File[] files;
        if (file.getName().endsWith(".png")) {
            putImage(file);
        } else if (file.isDirectory() && (files = file.listFiles()) != null) {
            for (File temp : files) {
                loadImages(temp);
            }
        }
    }

    private static void putImage(File imageFile) {
        FileInputStream in;

        String name = imageFile.getName().replaceFirst(".png", "");

        if (images.containsKey(name)) {
            System.out.println("Duplicate image " + imageFile.getName());
        }

        try {
            images.put(name, ImageIO.read(in = new FileInputStream(imageFile)));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Image Loaded: " + imageFile.getName());
    }
}
