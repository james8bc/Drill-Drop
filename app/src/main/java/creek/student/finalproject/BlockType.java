package creek.student.finalproject;

import android.app.Activity;
import android.graphics.Color;

//Todo pretty much everything
//Todo add comments
//Everything else should be good

public interface BlockType {
    int type = 0;
    int points = 0;

    public int getType();
    public int getPoints();
    public void setType(int id);
    public void setPoints(int id);
}
