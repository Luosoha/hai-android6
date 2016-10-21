package controllers;

import models.GameObject;
import views.GameView;

/**
 * Created by Lush on 10/21/2016.
 */
public class GiftController extends SingleController implements Contactable {
    public GiftController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        CollisionPool.instance.register(this);
    }

    @Override
    public void run() {
        gameObject.move(0, 3);
    }

    @Override
    public void onCollide(Contactable contactable) {
        if (contactable instanceof PlaneController) {
            this.destroy();
            ((PlaneController) contactable).isPowerUp = true;
        }
    }
}
