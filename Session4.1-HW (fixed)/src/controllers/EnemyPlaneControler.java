package controllers;

import models.Bullet;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Lush on 10/11/2016.
 */
public class EnemyPlaneControler extends SingleController {

    private static final int SPEED = 1;
    private static final int RELOAD_TIME = 50;

    private Vector<EnemyBulletController>  enemyBulletControllers;
    private int count;

    public EnemyPlaneControler(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        enemyBulletControllers = new Vector<>();
        count = 0;
    }

    private void createEnemyBullet() {
        EnemyBulletController bulletController = new EnemyBulletController(
                new Bullet(gameObject.getMiddleX() - Bullet.BULLET_WIDTH / 2, gameObject.getY() - Bullet.BULLET_HEIGHT / 2),
                new GameView(Utils.loadImageFromRes("enemy_bullet.png"))
        );
        enemyBulletControllers.add(bulletController);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        for (EnemyBulletController enemyBulletController : enemyBulletControllers) {
            enemyBulletController.draw(g);
        }
    }

    @Override
    public void run() {
        gameObject.move(0, SPEED);
        count++;
        if (count == RELOAD_TIME) {
            count = 0;
            createEnemyBullet();
        }

        for (EnemyBulletController enemyBulletController : enemyBulletControllers) {
            enemyBulletController.run();
        }
    }


}
