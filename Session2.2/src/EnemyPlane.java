import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lush on 10/9/2016.
 */
public class EnemyPlane {
    public static final int PLANE_SPEED = 1;
    public static final int DELAY_TIME = 50;

    private int x;
    private int y;
    private int bulletDelay;
    private Image image;
    private ArrayList<Bullet> bullets;

    public EnemyPlane(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
        bulletDelay = 0;
        bullets = new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet() {
        Bullet bullet = null;
        int X = x + Plane.PLANE_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int Y = y + Bullet.BULLET_HEIGHT;
        if (bulletDelay == DELAY_TIME) {
            try {
                bullet = new Bullet(X, Y, ImageIO.read(new File("resources/bullet.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bullets.add(bullet);
            bulletDelay = 0;
        }
        else bulletDelay++;
    }

    public void autoMove(){
        y += PLANE_SPEED;
    }

    public void drawImage(Graphics g){
        g.drawImage(image, x, y, Plane.PLANE_WIDTH, Plane.PLANE_HEIGHT, null);
    }
}
