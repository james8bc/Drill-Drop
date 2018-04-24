package creek.student.finalproject;

import android.app.Activity;
import android.widget.ImageView;

public class Image {
    private final Activity activity;
    private int imageViewId;
    private String imagePath;
    private ImageView view;
    private int ySize;
    private int xSize;
    public Image(int Id, Activity _activity){
        this.activity = _activity;
        imageViewId = Id;
        view = this.activity.findViewById(imageViewId);
    }
    public int getImageViewId() {
        return imageViewId;
    }

    public String getImagePath() {
        return imagePath;
    }
    public ImageView getImageView() {
        return view;
    }

    public int getxSize() {
        return view.getWidth();
    }

    public int getySize() {
       return view.getHeight();
    }
}
