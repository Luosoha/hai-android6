package controllers;

import controllers.Manager.CollisionPool;
import models.EnemyBullet;
import utils.Utils;
import views.SingleDrawer;

/**
 * Created by apple on 10/16/16.
 */
public class EnemyBulletController extends SingleController implements Contactable {
    private static final int SPEED = 3;

    private FlyBehavior flyBehavior;

    public EnemyBulletController(EnemyBullet gameObject, SingleDrawer singleDrawer, FlyBehavior flyBehavior) {
        super(gameObject, singleDrawer);
        this.flyBehavior = flyBehavior;
        CollisionPool.instance.register(this);
    }

    @Override
    public void run() {
        super.run();
        if(flyBehavior != null) {
            flyBehavior.doFly(this.gameObject);
        }
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof PlaneController) {
            ((PlaneController) contactable).getHit(1);
            this.destroy();
        }
    }

    // Factory

    public static EnemyBulletController create(int x, int y, String image, FlyBehavior flyBehavior) {

        EnemyBulletController enemyBulletController =
                new EnemyBulletController(
                    new EnemyBullet(x, y),
                    new SingleDrawer(Utils.loadImageFromRes(image)),
                    flyBehavior
                );

        return enemyBulletController;
    }

    public static EnemyBulletController create(int x, int y, FlyBehavior flyBehavior) {
        return create(x, y, "enemy_bullet.png", flyBehavior);
    }
}
