package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorg.Controllers.ApiService;
import com.example.doctorg.Model.LoginResponse;
import com.example.doctorg.R;
import com.example.doctorg.Utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Login_Activity extends AppCompatActivity {

    TextView registerYourSelf;
    private EditText etFirstName, etEmail;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login2);
        etEmail = findViewById(R.id.etEmail);
        etFirstName = findViewById(R.id.etFirstName);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        registerYourSelf = findViewById(R.id.registerYourSelf);


        registerYourSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_Registration_Activity.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<LoginResponse> call = apiService.loginUser(email, firstName);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        String patientId = loginResponse.getPatientId();
                        Log.d("LoginActivity", "Patient ID: " + patientId);
                        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_id", patientId);
                        editor.apply();
                        Toast.makeText(User_Login_Activity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(User_Login_Activity.this, Home_Page_Activity.class);
                        intent.putExtra("user_id", loginResponse.getPatientId());
                        startActivity(intent);
                        finish();

                    } else {
                        String message = loginResponse.getMessage() != null ? loginResponse.getMessage() : "Login failed, please try again.";
                        Toast.makeText(User_Login_Activity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(User_Login_Activity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(User_Login_Activity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}