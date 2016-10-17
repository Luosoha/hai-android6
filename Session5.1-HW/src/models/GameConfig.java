package models;

/**
 * Created by apple on 10/16/16.
 */
public class GameConfig {
    private final static int DEFAULT_DELAY = 17;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    private int threadDelayInMiliseconds;
    private int screenWidth;
    private int screenHeight;

    public double getSeconds(int count) {
        return (threadDelayInMiliseconds * count) / 1000;
    }

    public double getMiliseconds(int count) {
        return threadDelayInMiliseconds * count;
    }

    private GameConfig(int threadDelayInMiliseconds, int screenWidth, int screenHeight) {
        this.threadDelayInMiliseconds = threadDelayInMiliseconds;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public int getThreadDelayInMiliseconds() {
        return threadDelayInMiliseconds;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setThreadDelayInMiliseconds(int threadDelayInMiliseconds) {
        this.threadDelayInMiliseconds = threadDelayInMiliseconds;
    }

    //    private static GameConfig instance; //object
//    public static GameConfig getInstance() {
//        if (instance == null) {
//            instance = new GameConfig(DEFAULT_DELAY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
//        }
//        return instance;
//    }

    public boolean yOutsideScreen(int y) {
        return y < 0 || y > screenHeight;
    }

    public boolean yOutsideScreen(GameObject gameObject) {
        return yOutsideScreen(gameObject.getY());
    }

    public static final GameConfig instance = new GameConfig(
            DEFAULT_DELAY,
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT
    );
}
