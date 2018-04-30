package creek.student.finalproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private int speed;
    private GameThread thready;
    private ImageView test;
    //private ImageView[][]blocks;
    private Block[][] blocks;
    private ImageView[][] borders;
    RelativeLayout rl;
    private Player drill;
    private int bordNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_main);
        bordNum = 0;
        test = findViewById(R.id.drill);
        test.setImageResource(R.drawable.drill_tile);
        test.setScaleX(test.getScaleX() * 2);
        test.setScaleY(test.getScaleY() * 2);

//        ImageView yuh = new ImageView(this);
//        yuh.setImageResource(R.drawable.ic_launcher_foreground);
        rl = findViewById(R.id.constraintz);
        blocks = new Block[200][4];
        borders = new ImageView[10][2];
        createBlocks();
        createBorder();
        drill = new Player(test, blocks);
        thready = new GameThread(this);

        thready.mStatusChecker.run();
//        ImageView ye = new ImageView(this);
//        ye.setImageResource(R.drawable.dirt_tile);
//        rl.addView(ye);
//        ye.setX(1000);

        speed = -50;


    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        test.setX(x - 150);
        Log.i("thing", "" + test.getX());
        Log.i("dfh", "srgh");
        return true;
    }

    public void update() {
        //Log.i("tag","workinggg");
        moveBlocks();


        drill.nextFrame();
        drill.checkHit();
        //checkHit();
    }


    public void createBlocks() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y] = new Block(y, x, new ImageView(this));
                //blocks[x][y].setImage(new ImageView(this));
                //blocks[x][y].getImage().setImageResource(R.drawable.dirt_tile);
                rl.addView(blocks[x][y].getImage());
                blocks[x][y].update();
                Log.i("block", x + " " + y);
            }
        }
        rl.removeView(test);
        rl.addView(test);
    }

    public void createBorder() {
        for (int x = 0; x < borders.length; x++) {
            for (int y = 0; y < borders[x].length; y++) {
                borders[x][y] = new ImageView(this);
                if (y == 0) {
                    borders[x][y].setImageResource(R.drawable.dirt_border);
                    borders[x][y].setX(0);
                } else {
                    borders[x][y].setImageResource(R.drawable.dirt_border2);
                    borders[x][y].setX(1050);

                }
                borders[x][y].setY(400 * x);
                rl.addView(borders[x][y]);
            }
        }
        rl.removeView(test);
        rl.addView(test);
    }

    public void moveBlocks() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].setY(blocks[x][y].getY() + speed);
                blocks[x][y].update();
            }
        }
    }

}
