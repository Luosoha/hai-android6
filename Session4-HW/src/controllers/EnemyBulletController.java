package controllers;

import models.EnemyPlane;
import models.GameObject;
import views.GameView;

/**
 * Created by Lush on 10/13/2016.
 */
public class EnemyBulletController extends SingleController {

    public static final int SPEED = 5;

    public EnemyBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }

    @Override
    public void run() {
        gameObject.move(0, SPEED);
    }
}
