package creek.student.finalproject;


import android.graphics.Rect;

/**
 * Created by student on 4/17/18.
 */

public class Player extends Item {
    private int xPos;
    private int yPos;
    private boolean isAlive;
    private int lives;
    private int score;
    private Image image;
    public Player(int x, int y,int id){
        xPos = x;
        yPos = y;
        image = new Image(id);
    }

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
    public boolean intersected(Block b){
        Rect r1 = new Rect(b.getPosX(),b.getPosY(),b.getPosX()+b.getImage().getxSize(),b.getPosY()+b.getImage().getySize());
        Rect r2 = new Rect(xPos,yPos,xPos+getImage().getxSize(),yPos+getImage().getySize());
        if(Rect.intersects(r1,r2))
            return true;
        else
            return false;
    }

}
