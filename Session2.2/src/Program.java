/**
 * Created by Lush on 10/2/2016.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
