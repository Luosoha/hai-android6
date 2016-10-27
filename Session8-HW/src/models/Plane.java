package models;

/**
 * Created by apple on 10/4/16.
 */
public class Plane extends GameObjectWithHP {
    public static final int PLANE_WIDTH = 50;
    public static final int PLANE_HEIGHT = 35;
    public static final int DEFAULT_HP = 3;

    public Plane(int x, int y) {
        super(x, y, PLANE_WIDTH, PLANE_HEIGHT, DEFAULT_HP);
    }
}
