package creek.student.finalproject;

import android.app.Activity;
import android.widget.ImageView;

public abstract class Item {
    //private int xPos;
    //private int yPos;
    private Image image;
    private int idImageView;

    public void setup(int Id, Activity _activity){
        idImageView = Id;
        ImageView view = _activity.findViewById(idImageView);
        //xPos = (int)view.getX();
        //yPos = (int)view.getY();
    }

   abstract void hit();

   /*int getPosX(){
       return xPos;
   }int getPosY(){
       return yPos;
    }*/
}
