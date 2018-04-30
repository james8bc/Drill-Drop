package com.example.nathan.veryfinalgame;

import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.Rectangle;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Block {


    private int x;
    private int y;
    private ImageView img;
    private Rect hitBox;

    public Block(int xPos,int yPos,ImageView imm){
        img = imm;
        img.setImageResource(R.drawable.dirt_tile);
        int num = (int)(Math.random()*100);
        //determines what type of block is to be initialized
        if(yPos%2!=0)
            if(num<50) {
                img.setImageResource(R.drawable.ore1_tile);
                num = (int) (Math.random() * 100);
                if (num < 50) {
                    img.setImageResource(R.drawable.ore2_tile);
                    num = (int) (Math.random() * 100);
                    if (num < 50) {
                        img.setImageResource(R.drawable.ore3_tile);
                    }
                }
            }
        x = 400 * xPos-50;
        y = 400 * yPos;
        hitBox = new Rect();
        img.getDrawingRect(hitBox);
    }

    public void setImage(ImageView thiss){
        img = thiss;
    }

    public ImageView getImage() {
        return img;
    }
    public Rect getHitBox() {
        return hitBox;
    }
    public void update(){
        img.setX(x);
        img.setY(y);
    }
    public void setX(int xx){
        x=xx;
    }
    public void setY(int xx){
        y=xx;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
