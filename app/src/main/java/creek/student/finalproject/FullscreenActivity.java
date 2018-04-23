package creek.student.finalproject;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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

    private Handler mHandler;
    private int level = 1;
    private int margin = 93 ;int row = 2131165323;
    private int count = 0;
    private int one = 1;
    private ConstraintLayout view;
    private ConstraintLayout constraintLayout;
    private boolean isPlaying;
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private static boolean goingUp = true;
    private ImageView drill;
    private ImageView box;

    public static boolean isGoingUp(){
        return goingUp;
    }
    public static void changeGoingUp(boolean change){
        goingUp=change;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        view = (findViewById(R.id.tableRow1));
        drill = (findViewById(R.id.drill));
        box = (findViewById(R.id.hitBox));
        constraintLayout = findViewById(R.id.constraintLayout8);
        mHandler = new Handler();
        start();
        box.setOnTouchListener(new View.OnTouchListener() {
            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
            PointF StartPT = new PointF(); // Record Start Position of 'img'

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        Log.i("yeah", StartPT.toString());
                        drill.setX((int) (event.getX() - event.getX() * 0.3));
                        //drill.setY((int)(StartPT.y + event.getY() - DownPT.y));
                        StartPT.set(drill.getX(), drill.getY());
                        break;

                    case MotionEvent.ACTION_UP:
                        // Nothing have to do
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                move();
                count++;
            } finally {
                if (count >= 286 * 5 + 3000 + 93) stop();
                else mHandler.postDelayed(mStatusChecker, 0);
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
        if(margin == 0 && row == R.id.tableRow5 && goingUp) {
            goingUp = false;
            margin = 1500;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(2131165323, ConstraintSet.BOTTOM, 2131165324, ConstraintSet.TOP);
            constraintSet.connect(2131165324, ConstraintSet.BOTTOM, 2131165325, ConstraintSet.TOP);
            constraintSet.connect(2131165325, ConstraintSet.BOTTOM, 2131165326, ConstraintSet.TOP);
            constraintSet.connect(2131165326, ConstraintSet.BOTTOM, 2131165327, ConstraintSet.TOP);
            constraintSet.applyTo(constraintLayout);
        }

        if(margin == 0 && goingUp){
            margin = 286;
            view = findViewById(row);
            row = row + 1;
            Log.i("BLAH", "FUCKING WORK ALREADY PLEASE" + row);
        }

        /*if(margin == 10 && !goingUp){
            margin = 0;
            view = findViewById(row);
            row = row - 1;
            Log.i("BLAH", "FUCKING WORK ALREADY PLEASE GOING DOWN" + row);
        }*/

        if(goingUp)
            changeConstraint(R.id.constraintLayout8);
        if(!goingUp)
            changeConstraint(row - 1);
    }
    private void changeConstraint(int connection){
        margin--;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        if(goingUp)
            constraintSet.connect(row, ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, margin);
        else {
            constraintSet.connect(2131165327, ConstraintSet.BOTTOM, R.id.constraintLayout8, ConstraintSet.BOTTOM, margin);
            Log.i("Row", "" + margin);
        }
        constraintSet.applyTo(constraintLayout);
        //Log.i("Row", "" + margin);
    }
}