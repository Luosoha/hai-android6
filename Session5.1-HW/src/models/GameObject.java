package models;

import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class GameObject {

    private int x;
    private int y;
    private int hp;
    private int width;
    private int height;
    private boolean isAlive;

    public GameObject(int x, int y, int hp, int width, int height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
        this.isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void getShot(int Damages) {
        this.hp -= Damages;
        if (this.hp < 0) {
            this.isAlive = false;
        }
    }

    public int getMiddleX() {
        return x + width / 2;
    }

    public int getBottom() {
        return y + height;
    }

    public int getMiddleY() {
        return y  + height / 2;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public boolean checkCollideWith(GameObject gameObject) {
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = gameObject.getRect();
        return rect1.intersects(rect2);
    }
}
