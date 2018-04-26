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
    private BlockType blockType;//todo assign each number of type to the correct blocktype
    private int imageId;
    private Image image;
    public Block(int id, Activity _activity,int type){
        this.activity = _activity;
        imageId = id;
        blockType = new BlockType(imageId, this.activity);
        image = new Image(id, this.activity);
        xPos = (int)image.getImageView().getX();
        yPos = (int)image.getImageView().getY();
        super.setup(id, _activity);
    }
    public boolean isHit(){
        return isHit == true;
    }
    void update(){
        image = new Image(imageId, this.activity);
        xPos = (int)image.getImageView().getX();
        yPos = (int)image.getImageView().getY();
    }
    void hit() {
        //if (FullscreenActivity.isGoingUp()) {
            isHit=true;
            image.getImageView().setVisibility(ImageView.GONE);
        //}
        //else{
        //    imageId = imageId;
            //player.hit
        //}
    }   public int getPosX(){
        return xPos;
    }public int getPosY(){
        return yPos;
    }
    public Image getImage(){
        return blockType.getImage();
    }

}