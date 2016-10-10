package views;

import models.Bullet;

import java.awt.*;

/**
 * Created by Lush on 10/10/2016.
 */
public class BulletView {
    public BulletView(Image image) {
        this.image = image;
    }

    private Image image;
    public Image getImage() {
        return image;
    }

    public void drawImage(Graphics g, Bullet bullet) {
        g.drawImage(image,
                bullet.getX(), bullet.getY(),
                Bullet.BULLET_WIDTH, Bullet.BULLET_HEIGHT,
                null
        );
    }
}
