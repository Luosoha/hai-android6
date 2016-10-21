package controllers;

import models.GameObject;

/**
 * Created by apple on 10/18/16.
 */

public interface ShootBehavior {
    void doShoot(GameObject gameObject, ControllerManager bulletControllerManager);
}