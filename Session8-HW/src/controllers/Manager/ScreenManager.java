package controllers.Manager;

import java.util.Stack;

/**
 * Created by Lush on 10/25/2016.
 */
public interface ScreenManager {

//
//    public ScreenManager() {
//        screenStack = new Stack<>();
//    }

    public void change(GameScreen gameScreen, boolean addToBackstack);
}
