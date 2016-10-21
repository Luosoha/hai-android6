package models;

/**
 * Created by apple on 10/18/16.
 */
public class GameObjectWithHP extends GameObject {

    private int hp;

    public GameObjectWithHP(int x, int y, int width, int height, int hp) {
        super(x, y, width, height);
        this.hp = hp;
    }

    public void decreaseHP(int amount) {
        hp -= amount;
        if(hp <= 0) {
            isAlive = false;
        }
    }

    public void increaseHP(int amount) {
        hp += amount;
    }
}
