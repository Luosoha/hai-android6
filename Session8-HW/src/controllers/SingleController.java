package controllers;
import java.awt.*;

import models.GameConfig;
import models.GameObject;
import views.GameDrawer;

/**
 * Created by apple on 10/11/16.
 */
public class SingleController implements BaseController {

    protected GameDrawer gameDrawer;
    protected GameObject gameObject;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer) {
        this.gameDrawer = gameDrawer;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameDrawer.drawImage(g, gameObject);
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
