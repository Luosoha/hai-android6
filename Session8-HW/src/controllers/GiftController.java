package controllers;

import controllers.Manager.CollisionPool;
import controllers.Manager.NotificationCenter;
import models.GameObject;
import utils.Utils;
import views.GameDrawer;
import views.SingleDrawer;

/**
 * Created by Lush on 10/25/2016.
 */
public class GiftController extends SingleController implements Contactable {

    private static final int SPEED = 1;

    public GiftController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.instance.register(this);
    }

    @Override
    public void run() {
        super.run();
        gameObject.move(0, SPEED);
    }

    public static GiftController create(int x, int y) {
        return new GiftController(
                new GameObject(x, y, 35, 40),
                new SingleDrawer(Utils.loadImageFromRes("bomb.png"))
        );
    }

    @Override
    public void onCollide(Contactable contactable) {
        if (contactable instanceof PlaneController) {
            NotificationCenter.instance.onEvent(EventType.BOMB_EXPLODE, this);
            this.destroy();
        }
    }
}
