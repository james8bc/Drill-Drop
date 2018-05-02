package creek.student.finalproject;


import android.app.Activity;
import android.graphics.Rect;
import android.widget.ImageView;

//Todo add comments
//Todo Make lines 36-47 a switch statement
//Todo Make something with hit function
//Score and lives work, just the text is behind the blocks so you cant see it. that needs to be fixed
//fixed. layout.addview on anything makes it go back to front. keep that in mind when rearranging lines in setup
//Everything else should be good

public class Player extends Item {
    private ImageView img;
    private ImageView hitBox;
    private int frame = 0;
    private Block[][] blocks;

    public Player(ImageView image, ImageView box, Block[][] b, int width, Activity activity) {
        super.setup(image, activity, (int) image.getX(), (int) image.getY(), width);
        img = image;
        hitBox = box;
        blocks = b;
        image.setImageResource(R.drawable.drill_tile);
        image.setScaleX(image.getScaleX() * 2);
        image.setScaleY(image.getScaleY() * 2);
    }

    @Override
    public void update() {
        img.setX(getX()-img.getWidth()/2);
        hitBox.setX(getX());
        hitBox.setY(img.getY()+img.getHeight()/2);
    }

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

}