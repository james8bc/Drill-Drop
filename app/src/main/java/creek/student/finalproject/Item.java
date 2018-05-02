package creek.student.finalproject;

import android.app.Activity;
import android.widget.ImageView;

public abstract class Item {
    private int x;
    private int y;
    private int width;
    private ImageView img;

    public void setup(ImageView imageView, Activity _activity, int xPos, int yPos, int w) {
        img = imageView;
        x = xPos;
        y = yPos;
        width = w;
    }

    abstract void hit();
    abstract void update();

    int getWidth() {
        return width;
    }
    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setXPos(int xPos) {
        x = xPos;
    }


    void setYPos(int yPos) {
        y = yPos;
    }

    ImageView getImage() {
        return img;
    }

}