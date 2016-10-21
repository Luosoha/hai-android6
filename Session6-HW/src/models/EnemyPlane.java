package models;

import java.awt.*;

/**
 * Created by apple on 10/9/16.
 */
public class EnemyPlane extends GameObjectWithHP {

    public static final int ENEMY_PLANE_WIDTH = 30;
    public static final int ENEMY_PLANE_HEIGHT = 20;

    public EnemyPlane(int x, int y) {
        super(x, y, ENEMY_PLANE_WIDTH, ENEMY_PLANE_HEIGHT, 1);
    }
}
