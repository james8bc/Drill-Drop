package creek.student.finalproject;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Block extends Item {

    //Todo make sure not 4 blocks in one row
    //Todo add comments
    //Everything else should be good

    private ImageView img;
    private boolean isHit;

    public Block(int xPos, int yPos, int width, Activity activity) {
        super.setup(new ImageView(activity), activity, width / 4 * xPos, width / 4 * yPos, width);
        img = super.getImage();
        img.setImageResource(R.drawable.dirt_tile);
        int num = (int) (Math.random() * 100);
        if (yPos % 2 != 0) {
            if (num <= 40)
                img.setImageResource(R.drawable.dirt_tile);
            if (num <= 60 && num > 40)
                img.setImageResource(R.drawable.ore1_tile);
            if (num <= 80 && num > 60)
                img.setImageResource(R.drawable.ore2_tile);
            if (num <= 100 && num > 80)
                img.setImageResource(R.drawable.ore3_tile);
        }
    }

    @Override
    public void update() {
        img.setX(getX());
        img.setY(getY());
    }

    public void setSize() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img.getLayoutParams();
        params.width = getWidth() / 4;
        params.height = getWidth() / 4;
        img.setLayoutParams(params);
    }

    @Override
    public void hit() {
        isHit = true;
        img.setImageResource(R.drawable.dirt_tile);
    }

    public boolean isHit() {
        return isHit;
    }
}
