import controllers.Manager.ControllerManager;
import controllers.Manager.GameScreen;
import controllers.Manager.NotificationCenter;
import controllers.Manager.ScreenManager;
import controllers.PlaneController;
import controllers.Screen.GameOverScreen;
import controllers.Screen.LaunchScreen;
import controllers.Screen.PlayGameScreen;
import models.GameConfig;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable, ScreenManager {

    private Image backBufferImage;

    private int backgroundWidth;
    private int backgroundHeight;

    private GameScreen currentGameScreen;
    private Stack<GameScreen> screenStack;


    public GameWindow() {
        new Thread(() -> Utils.playSound("resources/background_song.wav", true)).start();

        backgroundWidth = GameConfig.instance.getScreenWidth();
        backgroundHeight = GameConfig.instance.getScreenHeight();

        screenStack = new Stack<>();

        backBufferImage = new BufferedImage(backgroundWidth,
                backgroundHeight, BufferedImage.TYPE_INT_ARGB);

        this.setVisible(true);
        this.setSize(backgroundWidth, backgroundHeight);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addMouseListener(currentGameScreen);
        this.addMouseMotionListener(currentGameScreen);
        this.addKeyListener(currentGameScreen);

        change(new LaunchScreen(this), false);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    back();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        currentGameScreen.update(backBufferGraphics);

        g.drawImage(backBufferImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(GameConfig.instance.getThreadDelayInMiliseconds());
                repaint();
                checkDead();
                currentGameScreen.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void change(GameScreen newGameScreen, boolean addToBackstack) {
        if (currentGameScreen != null ) {
            this.detach(currentGameScreen);
        }
        if (addToBackstack && currentGameScreen != null) {
            screenStack.push(currentGameScreen);
        }

        this.attach(newGameScreen);
    }

    public void back() {
        if (screenStack.size() > 0) {
            GameScreen newGameScreen = screenStack.pop();
            detach(currentGameScreen);
            attach(newGameScreen);
            NotificationCenter.instance.reinit();
        }
    }

    private void attach(GameScreen newGameScreen) {
        this.currentGameScreen = newGameScreen;
        this.addMouseListener(newGameScreen);
        this.addKeyListener(newGameScreen);
        this.addMouseMotionListener(newGameScreen);
    }

    private void detach(GameScreen oldGameScreen) {
        this.removeKeyListener(oldGameScreen);
        this.removeMouseListener(oldGameScreen);
        this.removeMouseMotionListener(oldGameScreen);
    }

    private void checkDead() {
        if (!PlaneController.planeController.getGameObject().isAlive()
                || !PlaneController.planeController2.getGameObject().isAlive()) {
            this.change(new GameOverScreen(this), false);
        }
    }
}
