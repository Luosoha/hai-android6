package controllers;

import models.Bullet;
import models.EnemyBullet;
import models.EnemyPlane;
import utils.Utils;
import views.EnemyBulletView;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Lush on 10/10/2016.
 */
public class EnemyPlaneController {
    public static final int RELOAD_TIME = 60;

    private EnemyPlane enemyPlane;
    private EnemyPlaneView enemyPlaneView;
    private int count;

    private Vector<EnemyBulletController> enemyBulletControllerVector;

    public EnemyPlaneController(EnemyPlane enemyPlane, EnemyPlaneView enemyPlaneView) {
        this.count = 0;
        this.enemyPlane = enemyPlane;
        this.enemyPlaneView = enemyPlaneView;
        enemyBulletControllerVector = new Vector<>();
    }

    public void draw(Graphics g){
        enemyPlaneView.drawImage(g, enemyPlane);
        for (EnemyBulletController enemyBulletController : enemyBulletControllerVector){
            enemyBulletController.draw(g);
        }
    }

    public void run() {
        count ++;
        if(count >= RELOAD_TIME) {
            count = 0;
            EnemyBulletController enemyBulletController = new EnemyBulletController(
                    new EnemyBullet(enemyPlane.getMiddleX() - EnemyBullet.BULLET_WIDTH / 2, enemyPlane.getY() + EnemyBullet.BULLET_HEIGHT / 2),
                    new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png"))
            );
            enemyBulletControllerVector.add(enemyBulletController);
        }
        enemyPlane.fly();
        for (EnemyBulletController enemyBulletController : enemyBulletControllerVector){
            enemyBulletController.run();
        }
    }
}
