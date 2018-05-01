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

    public Player(ImageView imm, ImageView box, Block[][] blockz) {
        img = imm;
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

//    public void checkHit() {
//        for (int x = 0; x < blocks.length; x++) {
//            for (int y = 0; y < blocks[x].length; y++) {
//                if (Rect.intersects(hitBox, blocks[x][y].getHitBox())) {
//                    Log.i("hit", "hit!");
//                }
//            }
//        }
//
//    }

    public void hit() {

    }

    public boolean intersected(Block b) { //todo hitboxes working properly

        Rect r1 = new Rect();
        Rect r2 = new Rect();
        if(b.isHit()){
            return false;
        }
        b.update();
        b.getImage().getGlobalVisibleRect(r1);
        hitBox.getGlobalVisibleRect(r2);
        if (r1.intersect(r2))
            Log.i("hit!", "hit!");
        return r1.intersect(r2);
    }

}
