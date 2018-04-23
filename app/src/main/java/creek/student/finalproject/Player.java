package creek.student.finalproject;


import android.app.Activity;
import android.graphics.Rect;


public class Player extends Item {
    private final Activity activity;
    private int xPos;
    private int yPos;
    private boolean isAlive;
    private int lives;
    private int score;
    private Image image;
    public Player(int id, Activity _activity){
        this.activity = _activity;
        image = new Image(id, this.activity);
        xPos = (int)image.getImageView().getX();
        yPos = (int)image.getImageView().getY();
        super.setup(id, _activity);
    }


    public void kill(){
        FullscreenActivity.changeGoingUp();
    }
    public Image getImage(){
        return image;
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
        Rect rc1 = new Rect();
        image.getImageView().getDrawingRect(rc1);
        Rect rc2 = new Rect();
        b.getImage().getImageView().getDrawingRect(rc2);
        //if (Rect.intersects(rc1, rc2)) {
            // intersection is detected
            // here is your method call
       // }
        //Rect r1 = new Rect(b.getPosX(),b.getPosY(),b.getPosX()+b.getImage().getxSize(),b.getPosY()+b.getImage().getySize());
        //Rect r2 = new Rect(xPos,yPos,xPos+image.getxSize(),yPos+image.getySize());
        return Rect.intersects(rc1, rc2);
    }

}
