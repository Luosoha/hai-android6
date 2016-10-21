package controllers;

import models.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 10/16/16.
 */
public class CollisionPool implements BaseController {
    private Vector<Contactable> contactableVector;

    private CollisionPool() {
        contactableVector = new Vector<>();
    }

    @Override
    public void run() {
        for(int i = 0; i < contactableVector.size() - 1; i++) {
            for(int j = i + 1; j < contactableVector.size(); j++) {
                Contactable contactablei = contactableVector.get(i);
                Contactable contactablej = contactableVector.get(j);

                GameObject gameObjecti = contactablei.getGameObject();
                GameObject gameObjectj = contactablej.getGameObject();

                if (gameObjecti.checkCollideWith(gameObjectj)) {
                    contactablei.onCollide(contactablej);
                    contactablej.onCollide(contactablei);
                }
            }
        }

        Iterator<Contactable> iterator = contactableVector.iterator();
        while(iterator.hasNext()) {
            Contactable baseController = iterator.next();
            if (!baseController.getGameObject().isAlive()) {
                iterator.remove();
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    public void register(Contactable contactable) {
        contactableVector.add(contactable);
    }

    public static final CollisionPool instance = new CollisionPool();
}
