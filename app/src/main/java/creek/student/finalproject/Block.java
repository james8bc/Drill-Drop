package creek.student.finalproject;

import android.content.ClipData;

/**
 * Created by student on 4/13/18.
 */

public class Block extends Item {
    private int xPos;
    private int yPos;
    private boolean isHit;
    private BlockType blockType;

    public Block(BlockType type){
        blockType = type;
    }
    void hit(Item x) {
        if (FullscreenActivity.isGoingUp() == true) {
            isHit=true;
        }
        else{
            //player.hit
        }
    }

    public Image getImage(){
        return blockType.getImage();
    }

}