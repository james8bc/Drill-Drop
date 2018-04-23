package creek.student.finalproject;

import android.graphics.Color;


/**
 * Created by student on 4/17/18.
 */

public class BlockType extends Block {
    private String name;
    private Image image;
    private Color color;
    private int pointsGained;
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
