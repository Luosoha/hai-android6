package controllers;
import java.awt.*;

import models.GameConfig;
import models.GameObject;
import views.GameView;

/**
 * Created by apple on 10/11/16.
 */
public class SingleController implements BaseController {
    private GameView gameView;
    protected GameObject gameObject;

    public SingleController(GameObject gameObject, GameView gameView) {
        this.gameView = gameView;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameView.drawImage(g, gameObject);
    }

    public void run() {
        if (GameConfig.instance.yOutsideScreen(gameObject)) {
            gameObject.setAlive(false);
        }
    }

    public void destroy() {
        gameObject.setAlive(false);
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
