package creek.student.finalproject; /**
 * Created by student on 4/30/18.
 */

import android.os.Handler;
import android.widget.ImageView;

//Todo pause button
//Todo add comments
//Everything else should be good

public class GameThread extends Thread {
    Handler handle;
    ImageView c;
    int num;
    MainActivity useClass;
    Block[][] blocksArr;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {//todo get correct speed for screen scrolling
            try {
                useClass.update();
            } finally {
                handle.postDelayed(mStatusChecker, 35);
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
