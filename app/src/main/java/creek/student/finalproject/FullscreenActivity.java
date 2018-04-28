package creek.student.finalproject;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;

//todo add main menu functionality, options, and a firstplay check
public class FullscreenActivity extends AppCompatActivity {
    private int maxRow;
    private int level = 1;
    private int margin = 450;
    private int row;
    private int count = 0;
    private boolean isPlaying = true;
    private static boolean goingUp = true;
    private Handler mHandler;
    private ImageView img;
    private ConstraintLayout constraintLayout;
    private ArrayList < Block > blocks = new ArrayList < > ();
    private ArrayList < Block > tableRow = new ArrayList < > ();
    private Player player;
    int ids[][];

    public void blockArray(){//todo randomly generate an array of different blocktypes
        for(int i=R.id.imageView;i<=R.id.imageView9;i++){
            int r = (int) Math.random();
            if(r<.5)
                blocks.add(new Block(i, this,1));
            if(r>.5&&r<.66)                blocks.add(new Block(i, this,2));

            if(r>.66&&r<.82)
                blocks.add(new Block(i, this,3));
            else
                blocks.add(new Block(i, this,4));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        player = new Player(R.id.drill, this);
        img = player.getImage().getImageView();
        ids = new int[level + 5][5];
        constraintLayout = findViewById(R.id.constraintLayout8);
        setupRows();
        maxRow = 5;
        row = ids[0][0];
        mHandler = new Handler();
        start();

    }

    public static boolean isGoingUp() {
        return goingUp;
    }
    public static void changeGoingUp() {
        goingUp = true;
    }

    public void setupRows(){
        setUpConstraints();
        createRows(level +5);
    }
    public void createRows(int rows){
        for(int x = 0 ;x< rows; x++){
            TableRow tempRow = new TableRow(this);
            ids[x][0]=tempRow.getId();
            for(int y = 0; y<5;y++){
                ImageView temp = new ImageView(this);
                temp.setImageResource(R.drawable.dirt_tile);
                tempRow.addView(temp);
                temp.setId(R.id.imageView20+y);
                ids[x][y]= temp.getId();
            }
        }
    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {//todo get correct speed for screen scrolling
            try {
                move();
            } finally {
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

    private void move() {//todo if drill has finished going up, end level

        imageMove();
        //player = new Player(R.id.drill, this);
        for (int i = 0; i < level + 5; i++) {
            for(int j = 1; j < 5; j++) {
                Log.i("test",""+ ids[i][j]);
                Block block = new Block(    ids[i][j], this,1);
                player.update();
                block.update();

                if (player.intersected(block)) {
                    block.hit();
                }
            }
        }
        count++;
        if (margin == 0 && row == ids[ids.length-1][0] && goingUp) {
            goingUp = false;
        }
        //else if(margin == 640 && row == R.id.tableRow1 && !goingUp) { stop(); }
        if (margin == 0 && goingUp) {
            margin = 300;
            row = row + 1;
        }
        if (margin == 300 && !goingUp) {
            margin = 0;
            row = row - 1;
        }
        changeConstraint();
    }

    private void changeConstraint() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        if (goingUp) {
            margin--;
            constraintSet.connect(row, ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, margin);
        } else {
            margin++;
            if (row == ids[0][0])
                constraintSet.connect(row, ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, margin);
            else
                constraintSet.connect(row, ConstraintSet.TOP, row - 1, ConstraintSet.TOP, margin);
        }
        constraintSet.applyTo(constraintLayout);
    }

    public void pause(View v) {
        if (isPlaying) {
            isPlaying = false;

            stop();
        } else {
            start();
        }
    }

    private void setUpConstraints(){
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        for(int i = 0; i < ids.length; i++){
            if(i == 0)
                constraintSet.connect(ids[0][0], ConstraintSet.TOP, R.id.constraintLayout8, ConstraintSet.TOP, 100);
            else
                constraintSet.connect(ids[i][0], ConstraintSet.TOP, ids[i - 1][0], ConstraintSet.TOP, 10);

            for(int j = 1; j < ids[i].length; j++){
                if(j == 0)
                    constraintSet.connect(ids[i][j], ConstraintSet.LEFT, ids[i][0], ConstraintSet.LEFT, 0);
                else
                    constraintSet.connect(ids[i][j], ConstraintSet.LEFT, ids[i][j - 1], ConstraintSet.RIGHT, 0);
                if(j == ids[i].length - 1)
                    constraintSet.connect(ids[i][j], ConstraintSet.RIGHT, ids[i][0], ConstraintSet.RIGHT, 0);
                else
                    constraintSet.connect(ids[i][j], ConstraintSet.RIGHT, ids[i][j + 1], ConstraintSet.RIGHT, 0);
                constraintSet.connect(ids[i][j], ConstraintSet.TOP, ids[i][0], ConstraintSet.TOP, 0);
                constraintSet.connect(ids[i][j], ConstraintSet.BOTTOM, ids[i][0], ConstraintSet.BOTTOM, 0);
            }
        }
        constraintSet.applyTo(constraintLayout);
    }


    @SuppressLint("ClickableViewAccessibility")
    private void imageMove(){
        img.setOnTouchListener(new View.OnTouchListener() {
            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
            PointF StartPT = new PointF(); // Record Start Position of 'img'
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        //if(StartPT.x + event.getX() - DownPT.x >= width || StartPT.x + event.getX() - DownPT.x <= 0){
                            //break;
                        //}
                        StartPT.set(img.getX(), img.getY());
                        img.setX((int)(StartPT.x + event.getX() - DownPT.x));
                        StartPT.set(img.getX(), img.getY());
                        break;
                    case MotionEvent.ACTION_DOWN:
                        DownPT.set(event.getX(), event.getY());
                        StartPT.set(img.getX(), img.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        // Nothing have to do
                        break;
                    default :
                        break;
                }

                return true;
            }

        });
    }
}