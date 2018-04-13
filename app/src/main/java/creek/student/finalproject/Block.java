package creek.student.finalproject;

import android.content.ClipData;

/**
 * Created by student on 4/13/18.
 */

public class Block extends Item {
    private int xPos;
    private int yPos;
    private boolean isHit;
    private int blockType;

    void hit() {
        if (FullscreenActivity.goingUp == true) {
            isHit=true;
        }
        else{
            //player.hit
        }

    }
}