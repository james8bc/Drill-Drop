package com.example.nathan.veryfinalgame;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ImageView;


public class GameThread extends Thread{
    Handler handle;
    ImageView c;
    int num;
    MainActivity useClass;
    Block[][] blocksArr;
    public GameThread(MainActivity act){
        handle = new Handler();
        useClass = act;
        run();
        num = 0;
    }
    public GameThread(Block[][] blocks){
        handle = new Handler();
        blocksArr = blocks;
        run();
        num = 0;
    }
    private boolean paused;

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {//todo get correct speed for screen scrolling
            try {
                useClass.update();
            } finally {


                //num++;
                //c.setX(num);
                handle.postDelayed(mStatusChecker, 45);
            }
        }
    };

}
