package views;

import models.EnemyBullet;

import java.awt.*;

/**
 * Created by Lush on 10/10/2016.
 */
public class EnemyBulletView {
    private Image image;
    public Image getImage() {
        return image;
    }

    public EnemyBulletView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, EnemyBullet enemyBullet) {
        g.drawImage(image,
                enemyBullet.getX(), enemyBullet.getY(),
                EnemyBullet.BULLET_WIDTH, EnemyBullet.BULLET_HEIGHT,
                null
        );
    }
}
