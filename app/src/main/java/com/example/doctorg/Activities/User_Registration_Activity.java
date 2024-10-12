package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doctorg.Model.User_Registration_Model;
import com.example.doctorg.R;
import com.example.doctorg.Response.User_Registration_Response;
import com.example.doctorg.Utils.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Registration_Activity extends AppCompatActivity {

    Button registration_btn;
    EditText firstName, lastName, cities, addresss, states, emailes, phoneNumber, dateOfBirth;
    RadioGroup genderRadioGroup;

    List<User_Registration_Model> userRegistrationModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        registration_btn = findViewById(R.id.registration_btn);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        addresss = findViewById(R.id.address);
        states = findViewById(R.id.state);
        cities = findViewById(R.id.city);
        emailes = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phonenumber);
        dateOfBirth = findViewById(R.id.dob);
        genderRadioGroup = findViewById(R.id.gender);

        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f_name = firstName.getText().toString().trim();
                String l_name = lastName.getText().toString().trim();
                String address = addresss.getText().toString().trim();
                String city = cities.getText().toString().trim();
                String state = states.getText().toString().trim();
                String email = emailes.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String dob = dateOfBirth.getText().toString().trim();
                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender != null ? selectedGender.getText().toString() : "";

                User_Register(f_name, l_name, gender, address, city, state, phone, email, dob);
            }
        });
    }

    public void User_Register(String f_name, String l_name, String gender, String address, String city, String state, String phone, String email, String dob) {
        Call<User_Registration_Response> userRegistrationResponseCall = ApiClient.getInstance()
                .getApiService().registerUser(f_name, l_name, gender, address, city, state, phone, email, dob);
        userRegistrationResponseCall.enqueue(new Callback<User_Registration_Response>() {
            @Override
            public void onResponse(Call<User_Registration_Response> call, Response<User_Registration_Response> response) {
                User_Registration_Response registrationResponse = response.body();

                if (registrationResponse != null) {
                    // Log the response to debug
                    Log.d("API Response", "Message from server: " + registrationResponse.getMessage());

                    // Trim and check the result
                    String result = registrationResponse.getMessage().trim();

                    if (result.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    } else if (result.equalsIgnoreCase("exist")) {
                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Done" + result, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home_Page_Activity.class);
                        startActivity(intent);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No response from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User_Registration_Response> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Not Done: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}

