package creek.student.finalproject;

import android.widget.ImageView;

/**
 * Created by student on 4/17/18.
 */

public class Image {
    private int imageViewId;
    private String imagePath;
    private int ySize;
    private int xSize;
    public Image(int ID){
        imageViewId = ID;
    }
    public int getImageViewId() {
        return imageViewId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
}
