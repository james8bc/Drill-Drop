package creek.student.finalproject;
/**
 * Created by student on 4/30/18.
 */

import android.os.Handler;
import android.widget.ImageView;
//overarching method to control all once-per-frame methods, calls itself once every x milliseconds.
public class GameThread extends Thread {
    Handler handle;
    ImageView c;
    int num;
    MainActivity useClass;
    Block[][] blocksArr;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                useClass.update();
            } finally {
                handle.postDelayed(mStatusChecker, 1);
            }
        }
    };

    private boolean paused;

    public GameThread(MainActivity act) {
        handle = new Handler();
        useClass = act;
        run();
        num = 0;
    }

    public GameThread(Block[][] blocks) {
        handle = new Handler();
        blocksArr = blocks;
        run();
        num = 0;
    }

}