package creek.student.finalproject;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.PointF;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private int level = 1;
    private int margin = 93;
    private int row = R.id.tableRow1;
    private int count = 0;
    private boolean isPlaying;
    private static boolean goingUp = true;
    private Handler mHandler;
    private ConstraintLayout constraintLayout;
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private Player player;

    public void blockArray(){
        for(int i=0;i<100;i++){
            blocks.add(new Block((BlockType )))
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Player player = new Player(0, 0, R.id.drill);
        constraintLayout = findViewById(R.id.constraintLayout8);
        mHandler = new Handler();
        start();
    }

    public static boolean isGoingUp(){
        return goingUp;
    }
    public static void changeGoingUp(boolean change){
        goingUp=change;
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try{ move(); }
            finally {
                if (count >= 286 * 5 + 5000 + 93) stop();
                else mHandler.postDelayed(mStatusChecker, 1);
            }
        }
    };

    void start() {
        mStatusChecker.run();
    }
    void stop() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    private void move() {
        count++;
        if(margin == 0 && row == R.id.tableRow5 && goingUp) { goingUp = false; }
        if(margin == 0 && goingUp){
            margin = 286;
            row = row + 1;
        }
        if(margin == 286 && !goingUp){
            margin = 0;
            row = row - 1;
        }
        changeConstraint();
    }

    private void changeConstraint(){
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        if(goingUp) {
            margin--;
            constraintSet.connect(row, ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, margin);
        }
        else {
            margin++;
            if(row == R.id.tableRow1)
                constraintSet.connect(row, ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, margin);
            else
                constraintSet.connect(row, ConstraintSet.TOP, row - 1, ConstraintSet.TOP, margin);
        }
        constraintSet.applyTo(constraintLayout);
    }
}