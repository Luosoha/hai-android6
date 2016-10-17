package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by apple on 10/16/16.
 */
public class EnemyBulletController extends SingleController {
    private static final int SPEED = 3;

    public EnemyBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }

    @Override
    public void run() {
        super.run();
        gameObject.move(0, SPEED);
    }
}
