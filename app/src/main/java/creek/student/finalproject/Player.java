package creek.student.finalproject;


import android.app.Activity;
import android.graphics.Rect;
import android.widget.ImageView;


public class Player extends Item {
    private final Activity activity;
    private int xPos;
    private int yPos;
    private boolean isAlive;
    private int lives;
    private int Id;
    private int score;
    private Image image;
    public Player(int id, Activity _activity){
        this.activity = _activity;
        Id = id;
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

    void update(){
        image = new Image(Id, this.activity);
        xPos = (int)image.getxSize();
        yPos = (int)image.getySize();
    }
    public int getLives(){
        return lives;
    }
    public boolean intersected(Block b){ //todo hitboxes working properly
        //rect = new Rect(x, y, x+sprite.getWidth(), y+sprite.getHeight());
       // Rect rc1 = new Rect();
       // Rect rc2 = Sprite
        //image.getImageView().getDrawingRect(rc1);
        //b.getImage().getDrawingRect(rc2);
        //if (Rect.intersects(rc1, rc2)) {
            // intersection is detected
            // here is your method call
       // }
        Rect r1 = new Rect(b.getPosX(), b.getPosY(), b.getPosX() + 80,b.getPosY() + 85);
        Rect r2 = new Rect(xPos, yPos,xPos+image.getxSize(),yPos+image.getySize());
        return r1.intersect(r2);
    }

    int getPosX(){
        return xPos;
    }
    int getPosY(){
        return yPos;
    }
}
