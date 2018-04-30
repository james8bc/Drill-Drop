package creek.student.finalproject;

import android.app.Activity;
import android.widget.ImageView;

public abstract class Item {
    private int x;
    private int y;
    private Image image;
    private int idImageView;

    public void setup(int Id, Activity _activity){
        idImageView = Id;
        ImageView view = _activity.findViewById(idImageView);
        //xPos = (int)view.getX();
        //yPos = (int)view.getY();
    }

   abstract void hit();

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

}
