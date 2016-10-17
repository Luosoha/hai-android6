package controllers;

import models.GameObject;

/**
 * Created by apple on 10/16/16.
 */
public interface Contactable {
    GameObject getGameObject();
    void onCollide(Contactable contactable);
}
