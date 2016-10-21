package controllers;

import models.GameConfig;
import models.GameObject;

/**
 * Created by Lush on 10/20/2016.
 */
public class WallFlyBehavior implements FlyBehavior {

    private int speed;
    private boolean changeDirection;

    public WallFlyBehavior(int speed) {
        this.speed = speed;
        this.changeDirection = true;
    }

    @Override
    public void doFly(GameObject gameObject) {
        if (changeDirection) {
            gameObject.move(-speed, speed);
            if (gameObject.getX() <= 0) {
                changeDirection = false;
            }
        }
        else {
            gameObject.move(speed, speed);
            if (gameObject.getX() >= GameConfig.instance.getScreenWidth() - gameObject.getWidth()) {
                changeDirection = true;
            }
        }
    }
}
