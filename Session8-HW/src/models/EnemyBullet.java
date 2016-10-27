package models;

/**
 * Created by apple on 10/18/16.
 */
public class EnemyBullet extends GameObject {
    private static final int WIDTH = 30;

    public EnemyBullet(int x, int y) {
        super(x, y, WIDTH, WIDTH);
    }
}
