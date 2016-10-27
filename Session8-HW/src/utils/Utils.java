package utils;

import models.GameObject;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by apple on 10/9/16.
 */
public class Utils {
    /* Utilities */
    public static BufferedImage loadImage(String url) {
        BufferedImage returnImage = null; // empty
        try {
            returnImage = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnImage;
    }

    public static BufferedImage loadImageFromRes(String url) {
        return loadImage("resources/" + url);
    }

    public static BufferedImage rotateImage(BufferedImage src, double degrees) {
        double radians = Math.toRadians(degrees);

        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();

        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.floor(srcWidth * cos + srcHeight * sin);
        int newHeight = (int) Math.floor(srcHeight * cos + srcWidth * sin);

        BufferedImage result = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.translate((newWidth - srcWidth) / 2, (newHeight - srcHeight) / 2);
        g.rotate(radians, srcWidth / 2, srcHeight / 2);
        g.drawRenderedImage(src, null);

        return result;
    }

    public static Vector<Image> loadSprite(String sheetName, int count, int offsetX, int offsetY, int width, int height) {
        Vector<Image> imageVector = new Vector<>();
        BufferedImage sheetImage = loadImageFromRes(sheetName);
        for (int i = 0; i < count; i++) {
            int x = i * width + (i + 1)*offsetX;
            int y = offsetY;
            Image image = sheetImage.getSubimage(x, y, width, height);
            imageVector.add(image);
        }
        return imageVector;
    }

    public static void playSound(String audioUrl, boolean repeat) {
        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if(repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static double distance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double distance(GameObject gameObject1, GameObject gameObject2) {
        return distance(gameObject1.getMiddleX(), gameObject1.getMiddleY(), gameObject2.getMiddleX(), gameObject2.getMiddleY());
    }
}
