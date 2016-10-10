package models;

import controllers.BulletController;

import java.awt.*;

/**
 * Created by apple on 10/4/16.
 */
public class Bullet {
    public static final int BULLET_WIDTH = 13;
    public static final int BULLET_HEIGHT = 30;
    public static final int BULLET_SPEED = 10;

    private int x;
    private int y;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void fly(){
        y -= BULLET_SPEED;
    }
}
