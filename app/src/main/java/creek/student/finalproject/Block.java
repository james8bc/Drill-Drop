package creek.student.finalproject;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Block extends Item implements BlockType{

    //Todo make sure not 4 blocks in one row
    //Todo add comments
    //Everything else should be good

    private ImageView img;
    private boolean isHit;
    private int type;
    private int points;

    public Block(int xPos, int yPos, int width, Activity activity) {
        super.setup(new ImageView(activity), activity, width / 4 * xPos, width / 4 * yPos, width);
        img = super.getImage();
        img.setImageResource(R.drawable.dirt_tile);
        int num = (int) (Math.random() * 100);
        if (yPos % 2 != 0) {
            if (num <= 40) {
                img.setImageResource(R.drawable.dirt_tile);
                setType(0);
                setPoints(10);
            }
            if (num <= 60 && num > 40) {
                img.setImageResource(R.drawable.ore1_tile);
                setType(1);
                setPoints(100);
            }
            if (num <= 80 && num > 60) {
                img.setImageResource(R.drawable.ore2_tile);
                setType(2);
                setPoints(100);
            }
            if (num <= 100 && num > 80) {
                img.setImageResource(R.drawable.ore3_tile);
                setType(3);
                setPoints(100);
            }
        }
        if(getY()==0)
            img.setImageResource(R.drawable.grass_tile);
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
    public int getType(){return type;}
    public int getPoints(){return points;}
    public void setType(int id){type = id;}
    public void setPoints(int id){points = id;}
}
