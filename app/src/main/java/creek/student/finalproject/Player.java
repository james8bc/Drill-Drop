package creek.student.finalproject;


import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

public class Player {
    private ImageView img;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    private int x;
    private int y;
    private int speed;
    private Rect hitBox;
    private int frame;
    private Block[][] blocks;

    public Player(ImageView imm, Block[][] blockz) {
        img = imm;
        hitBox = new Rect();
        img.getDrawingRect(hitBox);
        frame = 0;
        blocks = blockz;
    }

    public void updatePlayer() {
        img.setX(x);
        img.setY(y);
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

    public void checkHit() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                if (Rect.intersects(hitBox, blocks[x][y].getHitBox())) {
                    Log.i("hit", "hit!");
                }
            }
        }

    }



  /*  public boolean intersected(Block b){ //todo hitboxes working properly
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
    }*/

}
