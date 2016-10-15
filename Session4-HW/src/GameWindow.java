import controllers.*;
import models.Plane;
import utils.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    private static final int BACKGROUND_WIDTH = 600;
    private static final int BACKGROUND_HEIGHT = 400;

    Image backgroundImage = null;
    Image backBufferImage;

    PlaneController planeController;
    PlaneController planeController2;

    private Vector<BaseController> controllers;

    public GameWindow() {

        controllers = new Vector<>();

        planeController = new PlaneController(
                new Plane(BACKGROUND_WIDTH / 2,  BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT - 10),
                new GameView(Utils.loadImageFromRes("plane3.png"))
        );

        planeController2 = new PlaneController(
                new Plane(BACKGROUND_WIDTH / 2,  BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT*2 - 10 ),
                new GameView(Utils.loadImageFromRes("plane4.png"))
        );

        controllers.add(planeController);
        controllers.add(planeController2);
        controllers.add(new EnemyPlaneControllerManager());

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //--------------------Handling window events----------------------
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

        //---------------------Handling mouse events----------------------
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                planeController2.mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                planeController2.mousePressed(mouseEvent);
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
        });

        //--------------------Handling keyboard events--------------------
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");
                planeController.keyPressed(e);

//                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    Image bulletImage = Utils.loadImage("resources/bullet.png");
//                    models.Bullet newBullet = new models.Bullet(
//                            plane.getMiddleX() - models.Bullet.BULLET_WIDTH / 2,
//                            plane.getY() - models.Bullet.BULLET_HEIGHT,
//                            bulletImage);
//                    bulletVector.add(newBullet);
//                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased");
                planeController.keyReleased(e);
            }

        });

        //----------------------Creating background-----------------------
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
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        //----------------------------------------------------
        for (BaseController baseController : controllers){
            baseController.draw(backBufferGraphics);
        }

        g.drawImage(backBufferImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(17);
                repaint();
                for (BaseController baseController : controllers) {
                    baseController.run();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
