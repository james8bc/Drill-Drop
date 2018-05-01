package creek.student.finalproject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rl;
    private int speed;
    private GameThread thready;
    private ImageView drillImage;
    private ImageView hitBox;
    private Block[][] blocks;
    private ImageView[][] borders;
    private Player drill;
    private int width;
    private int height;
    private int bordNum;
    private boolean goingDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_main);
        bordNum = 0;
        hitBox = findViewById(R.id.drillBound);
        drillImage = findViewById(R.id.drill);
        drillImage.setImageResource(R.drawable.drill_tile);
        drillImage.setScaleX(drillImage.getScaleX() * 2);
        drillImage.setScaleY(drillImage.getScaleY() * 2);
        goingDown = true;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        rl = findViewById(R.id.constraintz);
        blocks = new Block[200][4];
        borders = new ImageView[10][2];
        createBlocks();
        createBorder();
        drill = new Player(drillImage, hitBox, blocks);
        thready = new GameThread(this);

        thready.mStatusChecker.run();
        speed = -50;
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        drillImage.setX(x - 150);
        return true;
    }

    public void update() {
        moveBlocks();
        if (goingDown == false) {
            speed = 50;
        }
        for (Block[] b : blocks) {
            for (Block bl : b) {

                if (drill.intersected(bl))
                    bl.hit();
            }
        }
        drill.nextFrame();
        //drill.checkHit();
    }


    public void createBlocks() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y] = new Block(y, x, width, new ImageView(this), this);
                rl.addView(blocks[x][y].getImage());
                blocks[x][y].update();
                blocks[x][y].setSize();
            }
        }
        rl.removeView(drillImage);
        rl.addView(drillImage);
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
                    borders[x][y].setX(width / 4 * 3);

                }
                borders[x][y].setY(width / 4 * y);
                rl.addView(borders[x][y]);
            }
        }
        rl.removeView(drillImage);
        rl.addView(drillImage);
    }

    public void moveBlocks() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].setY(blocks[x][y].getY() + speed);
                blocks[x][y].update();
            }
        }
        if (blocks[blocks.length - 1][blocks[0].length - 1].getY() < height && goingDown == true) {
            goingDown = false;
        }
    }

}
