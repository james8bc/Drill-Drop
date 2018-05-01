package creek.student.finalproject;

import android.app.Activity;
import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Block extends Item {


    private final Activity activity;
    private int x;
    private int y;
    private int width;
    private ImageView img;
    private Rect hitBox;
    private boolean isHit;

    public Block(int xPos, int yPos, int w, ImageView imm, Activity activity) {
        img = imm;
        this.activity = activity;
        img.setImageResource(R.drawable.dirt_tile);
        int num = (int) (Math.random() * 100);
        //determines what type of block is to be initialized
        if (yPos % 2 != 0)
            if (num < 50) {
                img.setImageResource(R.drawable.ore1_tile);
                num = (int) (Math.random() * 100);
                if (num < 50) {
                    img.setImageResource(R.drawable.ore2_tile);
                    num = (int) (Math.random() * 100);
                    if (num < 50) {
                        img.setImageResource(R.drawable.ore3_tile);
                    }
                }
            }
        width = w;
        x = width / 4 * xPos;
        y = width / 4 * yPos;
        hitBox = new Rect();
        img.getDrawingRect(hitBox);
    }

    public ImageView getImage() {
        return img;
    }

    public void setImage(ImageView image) {
        img = image;
    }

    int getX() {
        return x;
    }

    void setX(int xx) {
        x = xx;
    }

    int getY() {
        return y;
    }

    void setY(int xx) {
        y = xx;
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public void update() {
        img.setX(x);
        img.setY(y);


    }

    public void setSize() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img.getLayoutParams();
        params.width = width / 4;
        params.height = width / 4;
        img.setLayoutParams(params);
    }

    public void hit() {
        isHit = true;
        img.setImageResource(R.drawable.dirt_tile);
    }

    public boolean isHit() {
        return isHit;
    }
}
