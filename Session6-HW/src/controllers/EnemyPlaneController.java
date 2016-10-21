package controllers;

import models.Bullet;
import models.EnemyPlane;
import models.GameConfig;
import models.GameObject;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by apple on 10/11/16.
 */
public class EnemyPlaneController extends SingleController implements Contactable {

    private ControllerManager butletControllerManager;

    private int count = 0;

    private FlyBehavior flyBehavior;
    private ShootBehavior shootBehavior;

    public EnemyPlaneController(GameObject gameObject, GameView gameView,
                                FlyBehavior flyBehavior,
                                ShootBehavior shootBehavior) {
        super(gameObject, gameView);
        this.flyBehavior = flyBehavior;
        this.shootBehavior = shootBehavior;
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
        // Fly
        // Needs game object
        //gameObject.move(0, SPEED);
        if(flyBehavior != null) {
            this.flyBehavior.doFly(this.gameObject);
        }
        // End Fly

        // Shot
        // Needs: count, butletControllerManager
        count++;
        if (GameConfig.instance.getSeconds(count) > 1) {
            count = 0;
            if (shootBehavior != null) {
                shootBehavior.doShoot(this.gameObject, this.butletControllerManager);
            }
//            EnemyBulletController bulletController = new EnemyBulletController(
//                    new Bullet(gameObject.getMiddleX() - Bullet.BULLET_WIDTH / 2, gameObject.getBottom()),
//                    new GameView(Utils.loadImageFromRes("enemy_bullet.png")
//                    ));
//            butletControllerManager.add(bulletController);
        }
        // End Shot

        butletControllerManager.run();
    }

    @Override
    public void onCollide(Contactable contactable) {
        if(contactable instanceof BulletController) {
            ((BulletController) contactable).destroy();
        }
    }

    public static EnemyPlaneController create(int x, int y, EnemyPlaneType enemyPlaneType) {
        Image image = null;
        FlyBehavior flyBehavior = null;
        ShootBehavior shootBehavior = null;
        if (enemyPlaneType == EnemyPlaneType.GRAY) {
            image = Utils.loadImageFromRes("plane1.png");
            flyBehavior = null;
            shootBehavior = new SmartShootBehavior();
        }
//        else if (enemyPlaneType == EnemyPlaneType.RED) {
//            image = Utils.loadImageFromRes("plane2.png");
//            flyBehavior =  new WallFlyBehavior(1);
//            shootBehavior = new DownRightShootBehavior();
//        }
        else if (enemyPlaneType == EnemyPlaneType.YELLOW) {
            image = Utils.loadImageFromRes("enemy_plane_yellow_1.png");
            flyBehavior =  new WallFlyBehavior(1);
            shootBehavior = new DownRightShootBehavior();
        }

        return new EnemyPlaneController(
                new EnemyPlane(x, y),
                new GameView(image),
                flyBehavior,
                shootBehavior);
    }
}
