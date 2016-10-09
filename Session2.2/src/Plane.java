import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lush on 10/4/2016.
 */

public class Plane {
    public static final int PLANE_WIDTH = 40;
    public static final int PLANE_HEIGHT = 30;

    private int x;
    private int y;
    private Image image;
    private ArrayList<Bullet> bullets;

    public Plane(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
        bullets = new ArrayList<Bullet>();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet() {
        Bullet bullet = null;
        int X = x + PLANE_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int Y = y - Bullet.BULLET_HEIGHT / 2;
        try {
            bullet = new Bullet(X, Y, ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bullets.add(bullet);
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                x += 10;
                break;
            case KeyEvent.VK_LEFT:
                x -= 10;
                break;
            case KeyEvent.VK_UP:
                y -= 10;
                break;
            case KeyEvent.VK_DOWN:
                y += 10;
                break;
            case KeyEvent.VK_SPACE:
                addBullet();
                break;
        }
    }

    public void mouseMoved(MouseEvent mouseEvent) {
        x = mouseEvent.getX() - PLANE_WIDTH / 2;
        y = mouseEvent.getY() - PLANE_HEIGHT / 2;
    }

    public void mousePressed() {
        addBullet();
    }

    public void drawImage(Graphics g){
        g.drawImage(image, x, y, PLANE_WIDTH, PLANE_HEIGHT, null);
    }
}