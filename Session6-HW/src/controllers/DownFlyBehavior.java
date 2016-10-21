package controllers;

import models.GameObject;

/**
 * Created by apple on 10/18/16.
 */
public class DownFlyBehavior implements FlyBehavior {

    private int speed;

    public DownFlyBehavior(int speed) {
        this.speed = speed;
    }

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(0, speed);
    }
}
