package models;

/**
 * Created by Lush on 10/25/2016.
 */
public class Explosion extends GameObject {

    private static final int EXPLOSION_WIDTH = 32;
    private static final int EXPLOSION_HEIGHT = 32;

    public Explosion(int x, int y) {
        super(x, y, EXPLOSION_WIDTH, EXPLOSION_HEIGHT);
    }
}
