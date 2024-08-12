package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.doctorg.R;

public class UserLogin_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }

    public void register_Activity(View view) {
        Intent intent = new Intent(getApplicationContext(), User_Registration_Activity.class);
        startActivity(intent);

    }
}