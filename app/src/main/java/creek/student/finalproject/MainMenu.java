package creek.student.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//Todo Redesign screen
//Todo add comments
//Everything else should be good

public class MainMenu extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btn = findViewById(R.id.button2);
    }

    public void goToActivity2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}