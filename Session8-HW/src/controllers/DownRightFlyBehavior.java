package controllers;

import models.GameObject;

/**
 * Created by apple on 10/18/16.
 */
public class DownRightFlyBehavior implements FlyBehavior {
    private static int SPEED = 1;

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(SPEED, SPEED);
    }
}
