package controllers;

import models.Bullet;
import views.BulletView;

import java.awt.*;

/**
 * Created by Lush on 10/10/2016.
 */
public class BulletController {

    private Bullet bullet;
    private BulletView bulletView;

    public BulletController(Bullet bullet, BulletView bulletView) {
        this.bullet = bullet;
        this.bulletView = bulletView;
    }

    public void draw(Graphics g) {
        // View - Model
        bulletView.drawImage(g, bullet);
    }

    public void run() {
        // Fly
        bullet.fly();
    }
}
