package controllers;

import models.EnemyPlane;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Lush on 10/12/2016.
 */
public class EnemyPlaneControllerManager extends ControllerManager {

    private static final int SPAWN_TIME = 100;
    int count = 0;

    public EnemyPlaneControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (count == SPAWN_TIME) {
            count = 0;
            Image enemyPlaneImage = Utils.loadImageFromRes("plane1.png");
            EnemyPlaneControler enemyPlaneControler = new EnemyPlaneControler(
                    new GameObject(random(), 0, EnemyPlane.ENEMY_PLANE_WIDTH, EnemyPlane.ENEMY_PLANE_HEIGHT),
                    new GameView(enemyPlaneImage)
            );
            singleControllers.add(enemyPlaneControler);
        }

        for (int i = 0; i < singleControllers.size(); i++) {
            if (singleControllers.get(i).gameObject.getY() >= 400) {
                singleControllers.remove(i);
            }
            for (int j = 0; j < PlaneController.bulletControllers.size(); j++) {
                BulletController bulletController = PlaneController.bulletControllers.get(j);
                if (bulletController.gameObject.getY() <= singleControllers.get(i).gameObject.getY() + singleControllers.get(i).gameObject.getHeight()
                        && bulletController.gameObject.getX() <= singleControllers.get(i).gameObject.getX() +  singleControllers.get(i).gameObject.getWidth()
                        && bulletController.gameObject.getX() + bulletController.gameObject.getWidth() >= singleControllers.get(i).gameObject.getX()
                    ){
                    singleControllers.remove(i);
                    PlaneController.bulletControllers.remove(j);
                    System.out.println("Collided");
                }
                if (bulletController.gameObject.getY() <= 0) {
                    PlaneController.bulletControllers.remove(j);
                }
            }
        }
    }

    private int random() {
        Random rd = new Random();
        int range = rd.nextInt(600);
        return range;
    }
}
