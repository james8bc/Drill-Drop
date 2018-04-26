package creek.student.finalproject;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ImageView;


public class BlockType {
    private String name;
    private Image image;
    private Color color;
    private int pointsGained;

    public BlockType(int id, Activity _activity) {
        image = new Image(id, _activity);h65
    }//todo make sure image gets assigned properly

    public int getPoitnsGained(){
        return pointsGained;
    }
    public String getName(){
        return name;
    }
    public Image getImage(){
        return image;
    }
    public Color getColor(){
        return color;
    }
}
