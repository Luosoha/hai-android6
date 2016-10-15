package models;

import java.awt.*;

/**
 * Created by apple on 10/9/16.
 */
public class EnemyBullet extends GameObject{
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;

    public EnemyBullet(int x, int y, int width, int height) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT);
    }
}
