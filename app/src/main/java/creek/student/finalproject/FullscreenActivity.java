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
    private int start = 93 ;int row = 2131165320;
    private int count = 0;
    private int one = 1;
    private ConstraintLayout view;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        view = (findViewById(R.id.tableRow1));
        constraintLayout = findViewById(R.id.constraintLayout8);
        mHandler = new Handler();
        startRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                startTimer();
                count++;
            } finally {
                if (count >= 286 * 6 + 93) stopRepeatingTask();
                else mHandler.postDelayed(mStatusChecker, 10);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    private void startTimer() {
        start = start - one;
        //if(start == 0 && row == R.id.tableRow5){
          //  one = -1;
        //    count = 0;
      //  }
        if(start == 0){
            start = 286;


            view = findViewById(row);
            row = row + one;
            Log.i("BLAH", "FUCKING WORK ALREADY PLEASE" + row);
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(row, ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, start);
        constraintSet.applyTo(constraintLayout);
    }
}