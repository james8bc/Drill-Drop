package creek.student.finalproject;

import android.app.Activity;
import android.content.ClipData;
import android.view.View;
import android.widget.ImageView;

public class Block extends Item {
    private final Activity activity;
    private int xPos;
    private int yPos;
    private boolean isHit;
    private ImageView view;
    private BlockType blockType;
    private int imageId;

    public Block(int id, Activity _activity){
        this.activity = _activity;
        imageId = id;
        blockType = new BlockType(imageId, this.activity);
        view = this.activity.findViewById(imageId);
        xPos = (int)view.getX();
        yPos = (int)view.getY();
        super.setup(id, _activity);
    }
    void update(){
        blockType = new BlockType(imageId, this.activity);
        view = this.activity.findViewById(imageId);
        xPos = (int)view.getX();
        yPos = (int)view.getY();
    }
    void hit() {
        if (FullscreenActivity.isGoingUp()) {
            isHit=true;
            view.setVisibility(ImageView.GONE);
        }
        else{
            imageId = imageId;
            //player.hit
        }
    }

    public Image getImage(){
        return blockType.getImage();
    }

    int getPosX(){
        return xPos;
    }
    int getPosY(){
        return yPos;
    }
}