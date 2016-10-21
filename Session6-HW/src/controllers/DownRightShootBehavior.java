package controllers;

import models.EnemyBullet;
import models.GameObject;

/**
 * Created by Lush on 10/18/2016.
 */
public class DownRightShootBehavior implements ShootBehavior {
    @Override
    public void doShoot(GameObject gameObject, ControllerManager bulletControllerManager) {
        EnemyBulletController enemyBulletController = EnemyBulletController.create(
                gameObject.getX(), gameObject.getY(),
                new DownRightFlyBehavior(3)
        );
        bulletControllerManager.add(enemyBulletController);
    }
}
