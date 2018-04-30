package com.example.nathan.veryfinalgame;

import android.graphics.Rect;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

public class Player {
    private ImageView img;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    private int x;
    private int y;
    private int speed;
    private Rect hitBox;
    private int frame;
    private Block[][] blocks;
    public Player(ImageView imm,Block[][] blockz){
        img = imm;
        hitBox = new Rect();
        img.getDrawingRect(hitBox);
        frame = 0;
        blocks = blockz;
    }
    public void updatePlayer(){
        img.setX(x);
        img.setY(y);
    }
    public void nextFrame(){
        if(frame == 0){
            img.setImageResource(R.drawable.drill_tile);
        }
        if(frame == 1){
            img.setImageResource(R.drawable.drill_tile2);
        }
        if(frame == 2){
            img.setImageResource(R.drawable.drill_tile3);
        }
        if(frame == 3){
            img.setImageResource(R.drawable.drill_tile4);
        }
        if(frame == 4){
            img.setImageResource(R.drawable.drill_tile5);
            frame = -1;
        }
        frame ++;
    }
    public void checkHit() {
        for(int x = 0; x<blocks.length;x++){
            for(int y = 0; y<blocks[x].length;y++){
                if(Rect.intersects(hitBox,blocks[x][y].getHitBox())){
                    Log.i("hit","hit!");
                }
            }
        }

    }


}
