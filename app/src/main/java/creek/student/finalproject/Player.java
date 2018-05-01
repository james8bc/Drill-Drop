package creek.student.finalproject;


import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

public class Player extends Item {
    private ImageView img;
    private int x;
    private int y;
    private int speed;
    private ImageView hitBox;
    private int frame;
    private Block[][] blocks;

    public Player(ImageView img, ImageView box, Block[][] blockz) {
        img = img;
        hitBox = box;
        frame = 0;
        blocks = blockz;
    }

    public int getSpeed() {
        return speed;
    }

    public void updatePlayer() {
        img.setX(x);
        img.setY(y);
        hitBox.setX(x);
        hitBox.setY(y);
    }

    public void nextFrame() {
        if (frame == 0) {
            img.setImageResource(R.drawable.drill_tile);
        }
        if (frame == 1) {
            img.setImageResource(R.drawable.drill_tile2);
        }
        if (frame == 2) {
            img.setImageResource(R.drawable.drill_tile3);
        }
        if (frame == 3) {
            img.setImageResource(R.drawable.drill_tile4);
        }
        if (frame == 4) {
            img.setImageResource(R.drawable.drill_tile5);
            frame = -1;
        }
        frame++;
    }
    public void hit() {

    }

    public boolean intersected(Block b) { //todo hitboxes working properly
        Rect r1 = new Rect();
        Rect r2 = new Rect();
        b.update();
        b.getImage().getGlobalVisibleRect(r1);
        img.getGlobalVisibleRect(r2);
        hitBox.getGlobalVisibleRect(r2);
        return r1.intersect(r2);
    }

}
