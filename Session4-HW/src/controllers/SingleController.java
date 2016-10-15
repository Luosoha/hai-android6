package controllers;
import java.awt.*;
import models.GameObject;
import views.GameView;

/**
 * Created by Lush on 10/11/2016.
 */
public class SingleController implements BaseController {
    protected GameView gameView;
    protected GameObject gameObject;

    public SingleController(GameObject gameObject, GameView gameView) {
        this.gameView = gameView;
        this.gameObject = gameObject;
    }

    public void draw(Graphics g) {
        gameView.drawImage(g, gameObject);
    }

    public void run() {

    }
}
