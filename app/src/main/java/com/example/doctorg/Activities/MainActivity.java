package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.doctorg.R;

public class MainActivity extends AppCompatActivity {

    private static final int SPS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this,UserLogin_Activity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPS);
    }
}