package creek.student.finalproject;


/**
 * Created by student on 4/17/18.
 */

public class Player extends Item {
    private int xPos;
    private boolean isAlive;
    private int lives;
    private int score;
    public void kill(){
        FullscreenActivity.changeGoingUp(true);
    }
    public void hit(){
        lives--;
        if(lives==0){
            kill();
        }

    }
    public int getLives(){
        return lives;
    }

}
