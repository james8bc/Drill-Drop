package creek.student.finalproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

//Todo add comments
//Todo game over screen when lives == 0 or end of screen (maybe like if all blocks y position is less than 0)
//Todo fix lives and score text to be on top
//Todo create pause button
//Everything else should be good

public class MainActivity extends AppCompatActivity {
    RelativeLayout rl;
    private int speed = -40;
    private GameThread thread;
    private Block[][] blocks = new Block[200][4];
    private ImageView[][] borders = new ImageView[8][2];
    private ArrayList<ImageView> trail = new ArrayList<ImageView>();
    private Player drill;
    private int width;
    private int height;
    private int score = 0;
    private int lives = 10;
    private TextView scoreView;
    private TextView livesView;
    private boolean goingDown = true;
    private int trailCount = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        rl = findViewById(R.id.constraintz);

        setup();
        thread = new GameThread(this);
        thread.mStatusChecker.run();
    }

    public boolean onTouchEvent(MotionEvent event) {
        drill.setXPos((int) event.getX());
        drill.update();
        return true;
    }

    public void update() {
        moveBlocks();
        if (goingDown == false)
            speed = 40;
        for (Block[] b : blocks)
            for (Block bl : b)
                if (bl.getY() > 0 && bl.getY() < height)
                    if (drill.intersected(bl)) {
                        bl.hit();
                        score+=bl.getPoints();
                        ((TextView) findViewById(R.id.textView)).setText("Score: " + score);
                        if (goingDown&&bl.getType()>0) {
                            lives--;
                            ((TextView) findViewById(R.id.textView2)).setText("Lives: " + lives);
                        }
                    }
        drill.nextFrame();
    }

    public void setup() {
        drill = new Player((ImageView) findViewById(R.id.drill), (ImageView) findViewById(R.id.drillBound), blocks, width, this);
        drill.setYPos(300);
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y] = new Block(y, x, width, this);
                rl.addView(blocks[x][y].getImage());
                blocks[x][y].update();
                blocks[x][y].setSize();
            }
        }
        for(int x =0;x< trailCount;x++){
            ImageView tr = new ImageView(this);
            tr.setImageResource(R.drawable.drill_trail2);
            tr.setX((int)(drill.getX()-drill.getWidth()/9));
            tr.setY((int)(drill.getY()+drill.getHeight()*0.5));
            rl.addView(tr);
            trail.add(tr);
        }
        rl.removeView(drill.getImage());
        rl.addView(drill.getImage());
        for (int x = 0; x < borders.length; x++) {
            for (int y = 0; y < borders[x].length; y++) {
                borders[x][y] = new ImageView(this);
                if (y == 0) {
                    borders[x][y].setImageResource(R.drawable.dirt_border);
                    borders[x][y].setX(0);

                } else {
                    borders[x][y].setImageResource(R.drawable.dirt_border2);
                    borders[x][y].setX(width / 4 * 3);
                }
                borders[x][y].setY(width / 4 * x);
                rl.addView(borders[x][y]);
            }
        }
        scoreView = findViewById(R.id.textView);
        livesView = findViewById(R.id.textView2);
        rl.removeView(scoreView);
        rl.addView(scoreView);
        rl.removeView(livesView);
        rl.addView(livesView);
    }

    public void moveBlocks() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].setYPos(blocks[x][y].getY() + speed);
                blocks[x][y].update();
            }
        }
        trail.get(0).setX((int)(drill.getX()-drill.getWidth()/9));
        trail.get(0).setY((int)(drill.getY()+drill.getHeight()*0.5));
        trail.add(trail.size(),trail.get(0));
        trail.remove(0);
        for(int x = 0;x<trail.size();x++){
            trail.get(x).setY(trail.get(x).getY()+speed);
            if(trail.size()>30)
                trail.remove(0);
        }
        if (blocks[blocks.length - 1][blocks[0].length - 1].getY() < height && goingDown == true)
            goingDown = false;
    }

    public void layer(){

    }

}
