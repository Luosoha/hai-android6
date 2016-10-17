import controllers.*;
import models.GameConfig;
import models.Plane;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    Image backgroundImage = null;
    Image backBufferImage;

    PlaneController planeController;
    PlaneController planeController2;

    int backgroundWidth;
    int backgroundHeight;

    private ControllerManager controllerManager;

    public GameWindow() {
        backgroundWidth = GameConfig.instance.getScreenWidth();
        backgroundHeight = GameConfig.instance.getScreenHeight();

        controllerManager = new ControllerManager();

        planeController = PlaneController.planeController;
        planeController2 = PlaneController.planeController2;

        controllerManager.add(planeController);
        controllerManager.add(planeController2);
        controllerManager.add(new EnemyPlaneControllerManager());
        controllerManager.add(CollisionPool.instance);

        backBufferImage = new BufferedImage(backgroundWidth,
                backgroundHeight, BufferedImage.TYPE_INT_ARGB);


        Image enemyPlaneImage = Utils.loadImageFromRes("plane1.png");


        this.setVisible(true);
        this.setSize(GameConfig.instance.getScreenWidth(),
                GameConfig.instance.getScreenHeight());

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

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                planeController2.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");
                planeController.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                planeController.keyReleased(e);
                System.out.println("keyReleased");
            }
        });

        backgroundImage = Utils.loadImageFromRes("background.png");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);

        controllerManager.draw(backBufferGraphics);

        g.drawImage(backBufferImage,
                0, 0,
                backgroundWidth, backgroundHeight, null);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(GameConfig
                        .instance
                        .getThreadDelayInMiliseconds());
                repaint();
                controllerManager.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
