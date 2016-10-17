package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by apple on 10/16/16.
 */
public class EnemyBulletController extends SingleController implements Contactable {
    private static final int SPEED = 3;
    private static final int DAMAGES = 1;

    public EnemyBulletController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        CollisionPool.instance.register(this);
    }

    @Override
    public void run() {
        super.run();
        gameObject.move(0, SPEED);
    }

    @Override
    public void onCollide(Contactable contactable) {
        if (contactable instanceof PlaneController) {
            contactable.getGameObject().getShot(DAMAGES);
        }
    }
}
