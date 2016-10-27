package models;

import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    protected boolean isAlive;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
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
