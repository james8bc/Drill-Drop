package creek.student.finalproject;

import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Block {


    private int x;
    private int y;
    private int width;
    private ImageView img;
    private Rect hitBox;

    public Block(int xPos, int yPos, int w, ImageView imm) {
        img = imm;
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

    public void setImage(ImageView thiss) {
        img = thiss;
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public void update() {
        img.setX(x);
        img.setY(y);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img.getLayoutParams();
        params.width = width / 4;
        params.height = width / 4;
        img.setLayoutParams(params);
    }

    public int getX() {
        return x;
    }

    public void setX(int xx) {
        x = xx;
    }

    public int getY() {
        return y;
    }

    public void setY(int xx) {
        y = xx;
    }
}
