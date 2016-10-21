package controllers;

import models.GameObject;

import static controllers.PlaneController.planeController2;

/**
 * Created by Lush on 10/20/2016.
 */
public class SmartShootBehavior implements ShootBehavior {
    SmartFlyBehavior smartFlyBehavior;

    @Override
    public void doShoot(GameObject gameObject, ControllerManager bulletControllerManager) {
        float ke = (float) (planeController2.getGameObject().getX()  - gameObject.getX());
        float huyen = (float) Math.sqrt(
                  (planeController2.getGameObject().getX() - gameObject.getX())
                * (planeController2.getGameObject().getX() - gameObject.getX())
                + (planeController2.getGameObject().getY() - gameObject.getY())
                * (planeController2.getGameObject().getY() - gameObject.getY()));
        float cos = ke / huyen;
        float dy = 5;
        float dx = dy * cos;
        if (dx - 0.5 > (int) dx) {
            dx += 1;
        }
        smartFlyBehavior = new SmartFlyBehavior(dx, dy);

        EnemyBulletController enemyBulletController = EnemyBulletController.create(
                gameObject.getX(), gameObject.getY(),
                smartFlyBehavior
        );
        bulletControllerManager.add(enemyBulletController);
    }
}
