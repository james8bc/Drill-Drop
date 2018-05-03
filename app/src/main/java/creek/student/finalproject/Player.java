package creek.student.finalproject;


import android.app.Activity;
import android.graphics.Rect;
import android.widget.ImageView;

public class Player extends Item {
    private ImageView img;
    private ImageView hitBox;
    private int frame = 0;
    private int lives = 10;
    private int rotDiff = 10;
    private Block[][] blocks;
    private boolean goingDown = true;

    public Player(ImageView image, ImageView box, Block[][] b, int width, Activity activity) {
        super.setup(image, activity, (int) image.getX(), (int) image.getY(), width);
        img = image;
        hitBox = box;
        blocks = b;
        image.setImageResource(R.drawable.drill_tile);
        image.setScaleX(image.getScaleX() * 2);
        image.setScaleY(image.getScaleY() * 2);
    }
//updates the coordinates of the drill.
    @Override
    public void update() {

        img.setX(getX() - img.getWidth() / 2);
        img.setY(getY());
        hitBox.setX(getX());
        hitBox.setY((int) (img.getY() + img.getHeight() * 0.9));
    }
//animates the drill.
    public void nextFrame() {
        if (frame == 0)
            img.setImageResource(R.drawable.drill_tile);
        if (frame == 1)
            img.setImageResource(R.drawable.drill_tile2);
        if (frame == 2)
            img.setImageResource(R.drawable.drill_tile3);
        if (frame == 3)
            img.setImageResource(R.drawable.drill_tile4);
        if (frame == 4) {
            img.setImageResource(R.drawable.drill_tile5);
            frame = -1;
        }
        frame++;
    }

    public void hit() {
        lives--;
    }

    public boolean intersected(Block b) {
        if (b.isHit() == true)
            return false;
        Rect r1 = new Rect();
        Rect r2 = new Rect();
        b.update();
        b.getImage().getGlobalVisibleRect(r1);
        hitBox.getGlobalVisibleRect(r2);
        return r1.intersect(r2);
    }

    public int getHeight() {
        return img.getHeight();
    }
    public void turnAround(){
        img.setRotation(180);
    }

    public float getRotation() {
        return img.getRotation();
    }

    public void lookAt(int x, int y){
        float rot = img.getRotation();
        int opp = (int)Math.abs(x-img.getX());
        int adj = (int)Math.abs(y-img.getY());
        double angle = Math.atan2(opp,adj);
        if(x>img.getX())
        {
            rot = 360-(float)Math.toDegrees(angle);
        }
        else{rot = (float)Math.toDegrees(angle);}
        if(goingDown)
            img.setRotation(rot+rotDiff);
        else
            img.setRotation(-(rot+rotDiff*0.75f-180));
//        if(x<img.getX())
//        {
//            rot = (float)Math.toDegrees(angle);
//        }
    }

    public void setGoingDown(boolean going) {
        goingDown = going;
    }
}