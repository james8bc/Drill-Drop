package creek.student.finalproject;


import android.app.Activity;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;


public class Player extends Item {
    private final Activity activity;
    private int xPos;
    private int yPos;
    private boolean isAlive;
    private int lives;
    private int Id;
    private int score;//todo implement scoring while going down and have each blocktype return the correct score going up
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
        xPos = (int)image.getImageView().getX();
        yPos = (int)image.getImageView().getY();
    }
    public int getLives(){
        return lives;
    }
    public boolean intersected(Block b){ //todo hitboxes working properly
        //rect = new Rect(x, y, x+sprite.getWidth(), y+sprite.getHeight());
        Rect r1 = new Rect();
        Rect r2 = new Rect();
        //image.getImageView().getDrawingRect(rc1);
        //b.getImage().getDrawingRect(rc2);
        //if (Rect.intersects(rc1, rc2)) {
            // intersection is detected
            // here is your method call
       // }
        if(b.isHit()){
            return false;
        }
        b.update();
        b.getImage().getImageView().getGlobalVisibleRect(r1);
        image.getImageView().getGlobalVisibleRect(r2);

        //Log.e("YSIZEBLOCK", b.getImage().getySize() + "");
        //Log.e("YSIZEDRILL", image.getySize() + "");
        Log.e("YPOSBLOCK", "" + String.valueOf(r1.top));
        Log.e("YXXXXXXPOSBLOCK", "" + b.getPosX());
        //Log.e("YPOSDRILL", yPos   + "");
        //Rect r1 = new Rect(b.getPosX(), b.getPosY(), b.getPosX() + b.getImage().getySize(),b.getPosY() + b.getImage().getySize());
        //Rect r2 = new Rect(xPos, yPos,xPos+image.getxSize(),yPos+image.getySize());

        return r1.intersect(r2);
    }

}
