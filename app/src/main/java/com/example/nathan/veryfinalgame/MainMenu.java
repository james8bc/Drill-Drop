package com.example.nathan.veryfinalgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btn = (Button)findViewById(R.id.button2);
    }
    public void changeAct(){

    }
    public void goToActivity2 (View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }


//btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            startActivity(new Intent(MainMenu.this, MainActivity.class));
//        }
//    });
}
