package controllers;

import models.GameObject;

/**
 * Created by Lush on 10/18/2016.
 */
public class DownLeftFlyBehavior implements FlyBehavior {

    private int speed;

    public DownLeftFlyBehavior(int speed) {
        this.speed = speed;
    }

    @Override
    public void doFly(GameObject gameObject) {
        gameObject.move(-speed, speed);
    }

}
