package controllers;

import models.Bullet;
import models.GameConfig;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class EnemyPlaneController extends SingleController implements Contactable {

    private static final int SPEED = 1;

    private ControllerManager butletControllerManager;

    private int count = 0;

    public EnemyPlaneController(GameObject gameObject, GameView gameView) {
        super(gameObject, gameView);
        butletControllerManager = new ControllerManager();
        CollisionPool.instance.register(this);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        butletControllerManager.draw(g);
    }


    @Override
    public void run() {
        gameObject.move(0, SPEED);

        butletControllerManager.run();

        count++;
        if (GameConfig.instance.getSeconds(count) > 1) {
            count = 0;
            EnemyBulletController bulletController = new EnemyBulletController(
                    new Bullet(gameObject.getMiddleX() - Bullet.BULLET_WIDTH / 2, gameObject.getBottom()),
                    new GameView(Utils.loadImageFromRes("enemy_bullet.png")
                    ));
            butletControllerManager.add(bulletController);
        }
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof BulletController) {
            ((BulletController) contactable).destroy();
        }
    }
}
