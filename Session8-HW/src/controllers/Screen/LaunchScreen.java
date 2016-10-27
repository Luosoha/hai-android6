package controllers.Screen;

import controllers.Manager.GameScreen;
import controllers.Manager.NotificationCenter;
import controllers.Manager.ScreenManager;
import controllers.PlaneController;
import models.GameConfig;
import models.Plane;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lush on 10/26/2016.
 */
public class LaunchScreen extends GameScreen {

    private final Image backgroundImage = Utils.loadImageFromRes("1945-logo.png");

    public LaunchScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    @Override
    public void run() {
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(
                backgroundImage,
                0, 20,
                GameConfig.instance.getScreenWidth(),
                GameConfig.instance.getScreenHeight(),
                null
        );
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            this.screenManager.change(
                    new PlayGameScreen(screenManager),
                    true
            );
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
