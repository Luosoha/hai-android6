package controllers.Manager;

import controllers.EnemyPlaneController;
import controllers.EnemyPlaneType;
import controllers.Manager.ControllerManager;
import models.EnemyPlane;
import models.GameConfig;

/**
 * Created by apple on 10/11/16.
 */
public class EnemyPlaneControllerManager extends ControllerManager {

    public static final int SPAWN_TIME = 3;
    private int count;

    public EnemyPlaneControllerManager() {
        super();
        count = 0;
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (GameConfig.instance.getSeconds(count) >= SPAWN_TIME) {
            count = 0;
            for (int i = 0; i < 10; i++) {
                int y = 60;
                int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
                EnemyPlaneController enemyPlaneController =
                        EnemyPlaneController.create(x, y, EnemyPlaneType.GRAY);
                baseControllers.add(enemyPlaneController);
            }

            for (int i = 0; i < 10; i++) {
                int y = 100;
                int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
                EnemyPlaneController enemyPlaneController =
                        EnemyPlaneController.create(x, y, EnemyPlaneType.RED);
                baseControllers.add(enemyPlaneController);
            }
        }
    }
}
