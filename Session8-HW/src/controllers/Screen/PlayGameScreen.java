package controllers.Screen;

import controllers.Manager.*;
import controllers.GiftController;
import controllers.PlaneController;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lush on 10/25/2016.
 */
public class PlayGameScreen extends GameScreen {

    private Image backgroundImage = null;
    private PlaneController planeController;
    private PlaneController planeController2;
    private ControllerManager controllerManager;

    public PlayGameScreen(ScreenManager screenManager) {
        super(screenManager);
        controllerManager = new ControllerManager();

        planeController = PlaneController.planeController;
        planeController2 = PlaneController.planeController2;

        controllerManager.add(planeController);
        controllerManager.add(planeController2);
        controllerManager.add(new EnemyPlaneControllerManager());
        controllerManager.add(CollisionPool.instance);
        controllerManager.add(ControllerManager.explosionManager);
        controllerManager.add(GiftController.create(GameConfig.instance.getScreenWidth() / 2, 0));

        backgroundImage = Utils.loadImageFromRes("background.png");
    }

    @Override
    public void run() {
        controllerManager.run();
    }

    @Override
    public void update(Graphics g) {

        g.drawImage(backgroundImage,
                0, 0,
                GameConfig.instance.getScreenWidth(), GameConfig.instance.getScreenHeight(),
                null
        );

        controllerManager.draw(g);

    }

    /********************************Key Listener********************************/
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        planeController.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        planeController.keyReleased(e);
    }

    /********************************Mouse Listener********************************/
    @Override
    public void mouseClicked(MouseEvent e) {
        planeController2.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /*****************************Mouse Motion Listener*****************************/
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        planeController2.mouseMoved(e);
    }
}
