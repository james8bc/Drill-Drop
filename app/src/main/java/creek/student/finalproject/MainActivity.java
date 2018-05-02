package creek.student.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

//Todo add comments on every activity
//Todo create pause button

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
    private TextView depthView;
    private TextView livesView;
    private TextView notif;
    private Button restart;
    private Button pause;
    private boolean goingDown = true;
    private int trailCount = 200;
    private boolean paused = false;
//initializes most variables and creates the gamethread.
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
//controls x value of drill.
    public boolean onTouchEvent(MotionEvent event) {
        if(!paused) {
            drill.setXPos((int) event.getX());
            drill.update();
        }
        return true;
    }
//calls once-per-frame block and drill methods.
    public void update() {
        if (!paused) {
            moveBlocks();
            depthView.setText("Depth: " + ((-blocks[0][0].getY())+320));
            if (goingDown == false)
                speed = 40;
            }
            if (lives == 0)
                goingDown = false;
            for (Block[] b : blocks)
                for (Block bl : b)
                    if (bl.getY() > 0 && bl.getY() < height)
                        if (drill.intersected(bl)) {
                            bl.hit();
                            score += bl.getPoints();
                            scoreView.setText("Score: " + score);
                            if (goingDown && bl.getType() > 0) {
                                lives--;
                                livesView.setText("Lives: " + lives);
                            }
                        }
            drill.nextFrame();
        }


//Initializes ImageViews, layers layout items correctly, and fills block border and trail arrays.
    public void setup() {
        pause = findViewById(R.id.pause);
        restart = findViewById(R.id.restart);
        rl.removeView(restart);
        notif = findViewById(R.id.textView5);
        drill = new Player((ImageView) findViewById(R.id.drill), (ImageView) findViewById(R.id.drillBound), blocks, width, this);
        drill.setYPos((int) (height * 0.4));
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y] = new Block(y, x, width, this);
                rl.addView(blocks[x][y].getImage());
                blocks[x][y].update();
                blocks[x][y].setSize();
            }
        }
        for (int x = 0; x < trailCount; x++) {
            ImageView tr = new ImageView(this);
            tr.setImageResource(R.drawable.drill_trail2);
            tr.setX(-1000);
            tr.setY(-1000);
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
                    borders[x][y].setX((int) (width * 0.725));
                }
                borders[x][y].setY(width / 4 * x);
                rl.addView(borders[x][y]);
            }
        }
        scoreView = findViewById(R.id.score);
        livesView = findViewById(R.id.lives);
        depthView = findViewById(R.id.depth);
        rl.removeView(scoreView);
        rl.addView(scoreView);
        rl.removeView(livesView);
        rl.addView(livesView);
        rl.removeView(depthView);
        rl.addView(depthView);
        rl.removeView(pause);
        rl.addView(pause);
    }

    //handles all image updates besides drill.
    public void moveBlocks() {
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                blocks[x][y].setYPos(blocks[x][y].getY() + speed);
                blocks[x][y].update();
            }
        }
        trail.get(0).setX(drill.getX() - drill.getWidth() / 9);
        trail.get(0).setY((int) (drill.getY() + drill.getHeight() * 0.5));
        trail.add(trail.size(), trail.get(0));
        trail.remove(0);
        for (int x = 0; x < trail.size(); x++) {
            trail.get(x).setY(trail.get(x).getY() + speed);
            if (trail.size() > 30)
                trail.remove(0);
        }
        if ((blocks[blocks.length - 1][blocks[0].length - 1].getY() < height && goingDown == true)||lives<1) {
            goingDown = false;
            drill.turnAround();
        }
        if(lives<5)
            livesView.setTextColor(Color.rgb(255-51*lives,0,0));
        if(blocks[0][0].getY() >300 && goingDown == false)
            endGame();
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        this.finish();
        startActivity(intent);

    }
    public void pauseGame(View view){
        paused = !paused;
    }
    private void endGame() {
        paused = true;
        notif.setText("GAME OVER!");
        rl.removeView(notif);
        rl.addView(notif);
        rl.addView(restart);

    }

}