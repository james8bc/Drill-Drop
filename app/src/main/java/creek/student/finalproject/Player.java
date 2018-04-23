package creek.student.finalproject;

import Item;

/**
 * Created by student on 4/17/18.
 */

public class Player extends Item {
    private int xPos;
    private boolean isAlive;
    private int lives;
    public void dead(){
        if(lives==0){
            FullscreenActivity.changeGoingUp(true);
        }
    }
    public void hit(){
        lives--;
    }
    public int getLives(){
        return lives;
    }

}
