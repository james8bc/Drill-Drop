package com.example.nathan.veryfinalgame;

import android.util.Log;

public class Updater {
    private static Block[][]blocks;
    public Updater(Block[][]blo){
        blocks=blo;
    }
    public static void moveBlocks(){
        for(int x = 0; x<blocks.length;x++){
            for(int y = 0; y<blocks[x].length;y++){


                blocks[x][y].update();
                blocks[x][y].setX(blocks[x][y].getX()+10);
                blocks[x][y].setY(blocks[x][y].getY()+10);

                Log.i("block",x+" "+y);


            }
        }
    }
}
