package controllers;

import models.Bullet;
import models.Plane;
import utils.Utils;
import views.BulletView;
import views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by Lush on 10/10/2016.
 */
public class PlaneController {
    public static final int PLANE_SPEED = 5;

    private Plane plane;
    private PlaneView planeView;
    private int dx;
    private int dy;

    private Vector<BulletController> bulletControllerVector;

    public PlaneController(Plane plane, PlaneView planeView) {
        bulletControllerVector = new Vector<>();
        this.plane = plane;
        this.planeView = planeView;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                dx = PLANE_SPEED;
                break;
            case KeyEvent.VK_LEFT:
                dx = -PLANE_SPEED;
                break;
            case KeyEvent.VK_UP:
                dy = -PLANE_SPEED;
                break;
            case KeyEvent.VK_DOWN:
                dy = PLANE_SPEED;
                break;
            case KeyEvent.VK_SPACE:
                //Create BulletController (Model, View)
                BulletController bulletController = new BulletController(
                        new Bullet(plane.getMiddleX() - Bullet.BULLET_WIDTH / 2, plane.getY() - Bullet.BULLET_HEIGHT / 2),
                        new BulletView(Utils.loadImageFromRes("bullet.png"))
                );
                bulletControllerVector.add(bulletController);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
        }
    }

    public void mouseMoved(MouseEvent e) {
        plane.moveTo(e.getX() - (Plane.PLANE_WIDTH / 2),
                e.getY() - (Plane.PLANE_HEIGHT / 2));
    }

    public void mousePressed(MouseEvent mouseEvent) {
        BulletController bulletController = new BulletController(
                new Bullet(plane.getMiddleX() - Bullet.BULLET_WIDTH / 2, plane.getY() - Bullet.BULLET_HEIGHT / 2),
                new BulletView(Utils.loadImageFromRes("bullet.png"))
        );
        bulletControllerVector.add(bulletController);
    }

    public void run(){
        // Update plane model
        plane.move(dx, dy);
        for (BulletController bulletController : bulletControllerVector){
            bulletController.run();
        }
    }

    public void draw(Graphics g) {
        planeView.drawImage(g, plane);
        for (BulletController bulletController : bulletControllerVector){
            bulletController.draw(g);
        }
    }
}
