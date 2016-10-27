package controllers.Screen;

import controllers.Manager.GameScreen;
import controllers.Manager.ScreenManager;
import controllers.PlaneController;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lush on 10/26/2016.
 */
public class GameOverScreen extends GameScreen {

    private final Image gameOverImage = Utils.loadImageFromRes("game_over_image.jpg");

    public GameOverScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    @Override
    public void run() {

    }

    @Override
    public void update(Graphics g) {

        g.drawImage(
                gameOverImage,
                0, 0,
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
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.screenManager.change(
                    new LaunchScreen(screenManager),
                    false
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
