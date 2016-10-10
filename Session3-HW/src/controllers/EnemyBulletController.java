package controllers;

import models.EnemyBullet;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by Lush on 10/10/2016.
 */
public class EnemyBulletController {
    private EnemyBullet enemyBullet;
    private EnemyBulletView enemyBulletView;

    public EnemyBulletController(EnemyBullet bullet, EnemyBulletView bulletView) {
        this.enemyBullet = bullet;
        this.enemyBulletView = bulletView;
    }

    public void draw(Graphics g) {
        // View - Model
        enemyBulletView.drawImage(g, enemyBullet);
    }

    public void run() {
        // Fly
        enemyBullet.fly();
    }
}
