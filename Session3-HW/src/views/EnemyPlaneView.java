package views;

import models.EnemyPlane;

import java.awt.*;

/**
 * Created by Lush on 10/10/2016.
 */
public class EnemyPlaneView {
    private Image image;
    public Image getImage() {
        return image;
    }

    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, EnemyPlane enemyPlane) {
        g.drawImage(
                image,
                enemyPlane.getX(), enemyPlane.getY(),
                EnemyPlane.ENEMY_PLANE_WIDTH, EnemyPlane.ENEMY_PLANE_HEIGHT,
                null
        );
    }
}
