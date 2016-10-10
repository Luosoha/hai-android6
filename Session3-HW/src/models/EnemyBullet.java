package models;

import java.awt.*;

/**
 * Created by apple on 10/9/16.
 */
public class EnemyBullet {
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;
    public static final int ENEMY_BULLET_SPEED = 3;

    private int x;
    private int y;

    public EnemyBullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void fly() {
        this.y += ENEMY_BULLET_SPEED;
    }

}
