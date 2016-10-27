package controllers;

import controllers.Manager.ControllerManager;
import models.EnemyBullet;
import models.GameObject;
import utils.Utils;
import views.SingleDrawer;

/**
 * Created by apple on 10/18/16.
 */

public class DownShootBehavior implements ShootBehavior {

    @Override
    public void doShoot(GameObject gameObject, ControllerManager bulletControllerManager) {

        EnemyBulletController enemyBulletController = new EnemyBulletController(
                new EnemyBullet(gameObject.getX(), gameObject.getY()),
                new SingleDrawer(Utils.loadImageFromRes("enemy_bullet.png")),
                new DownFlyBehavior(5)
        );

        bulletControllerManager.add(enemyBulletController);
    }
}
