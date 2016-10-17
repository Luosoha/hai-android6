package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by apple on 10/9/16.
 */
public class Utils {
    /* Utilities */
    public static Image loadImage(String url) {
        Image returnImage = null; // empty
        try {
            returnImage = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnImage;
    }

    public static Image loadImageFromRes(String url) {
        return loadImage("resources/" + url);
    }
}
