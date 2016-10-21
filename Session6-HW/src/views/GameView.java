package views;

import models.GameObject;
import models.Plane;

import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class GameView {

    private Image image;

    public GameView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, GameObject gameObject) {
        g.drawImage(image,
                gameObject.getX(),
                gameObject.getY(),
                gameObject.getWidth(),
                gameObject.getHeight(),
                null);
    }
}
