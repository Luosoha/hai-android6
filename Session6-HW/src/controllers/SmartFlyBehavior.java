package controllers;

import models.GameObject;

import static controllers.PlaneController.planeController2;

/**
 * Created by Lush on 10/20/2016.
 */
public class SmartFlyBehavior implements FlyBehavior {

    private float dx;
    private float dy;

    public SmartFlyBehavior(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void doFly(GameObject gameObject) {

        gameObject.move(dx, dy);
    }
}
