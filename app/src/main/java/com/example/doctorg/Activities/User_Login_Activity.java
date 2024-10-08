package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doctorg.R;

public class User_Login_Activity extends AppCompatActivity {

    TextView registerYourSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login2);

        registerYourSelf = findViewById(R.id.registerYourSelf);

        // Set the onClick listener programmatically
        registerYourSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_Registration_Activity.class);
                startActivity(intent);
            }
        });
    }
}
