package creek.student.finalproject;

import android.app.Activity;
import android.graphics.Color;

//Todo pretty much everything
//Todo add comments
//Everything else should be good

public class BlockType {
    private String name;
    private Color color;
    private int pointsGained;

    public BlockType(int id, Activity _activity) {

    }

    public int getPoitnsGained(){
        return pointsGained;
    }
    public String getName(){
        return name;
    }
    public Color getColor(){
        return color;
    }
}
