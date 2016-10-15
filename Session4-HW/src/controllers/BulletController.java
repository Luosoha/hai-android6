package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by Lush on 10/9/2016.
 */
public class BulletController extends SingleController {

    public static final int SPEED = 10;

    public BulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
    }

    @Override
    public void run() {
        gameObject.move(0, -SPEED);
    }
}
