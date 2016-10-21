package controllers;

import models.GameObject;

/**
 * Created by apple on 10/18/16.
 */
public class DownRightFlyBehavior implements FlyBehavior {

    private int speed;

    public DownRightFlyBehavior(int speed) {
        this.speed = speed;
    }

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(speed, speed);
    }
}
