package com.example.doctorg.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorg.Controllers.ApiService;
import com.example.doctorg.Model.AppointmentResponse;
import com.example.doctorg.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Appointment_Activity extends AppCompatActivity {
    private TextView doctorNameTextView, doctorSpecialtyTextView, doctorLocationTextView, doctorExperiencesTextView , userEmailTextView;
    private ImageView doctorPhotoImageView;
    Button fix_appointment_button;
    private ProgressBar progressBar;
    private EditText patientIdEditText, doctorIdEditText, diseaseDescriptionEditText, appointmentDateEditText;
    private ScrollView contentScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        progressBar = findViewById(R.id.progress_bar);
        contentScrollView = findViewById(R.id.content_scroll_view);
        doctorNameTextView = findViewById(R.id.doctor_name_appointment);
        doctorSpecialtyTextView = findViewById(R.id.doctor_specialty_appointment);
        doctorLocationTextView = findViewById(R.id.doctor_location_appointment);
        doctorExperiencesTextView = findViewById(R.id.doctor_experiences_appointment);
        doctorPhotoImageView = findViewById(R.id.doctor_photo_appointment);
        patientIdEditText = findViewById(R.id.patient_id);
        doctorIdEditText = findViewById(R.id.doctor_id);
        diseaseDescriptionEditText = findViewById(R.id.disease_description);
        appointmentDateEditText = findViewById(R.id.appointment_date);
        userEmailTextView = findViewById(R.id.user_email);
        fix_appointment_button = findViewById(R.id.fix_appointment_button);
        String doctorName = getIntent().getStringExtra("doctor_name");
        String doctorSpecialty = getIntent().getStringExtra("doctor_specialty");
        String doctorLocation = getIntent().getStringExtra("doctor_location");
        String doctorExperiences = getIntent().getStringExtra("doctor_experiences");
        String doctorPhoto = getIntent().getStringExtra("doctor_photo");
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userID = sharedPreferences.getString("user_id", "Id not found");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                contentScrollView.setVisibility(View.VISIBLE);
            }
        }, 1000);
        fix_appointment_button.setText("Loading...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fix_appointment_button.setText("Fix Appointment");
            }
        }, 2000);
        appointmentDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Appointment_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Set the selected date in the EditText
                                String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                appointmentDateEditText.setText(selectedDate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        userEmailTextView.setText(userID);
        doctorNameTextView.setText(doctorName);
        doctorSpecialtyTextView.setText(doctorSpecialty);
        doctorLocationTextView.setText(doctorLocation);
        doctorExperiencesTextView.setText(doctorExperiences);

        if (doctorPhoto != null && !doctorPhoto.isEmpty()) {
            byte[] decodedString = Base64.decode(doctorPhoto, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            doctorPhotoImageView.setImageBitmap(decodedByte);
        }

        fix_appointment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookAppointment();
            }
        });
    }
    private void bookAppointment() {
        int patientId = Integer.parseInt(patientIdEditText.getText().toString());
        int doctorId = Integer.parseInt(doctorIdEditText.getText().toString());
        String diseaseDescription = diseaseDescriptionEditText.getText().toString();
        String appointmentDate = appointmentDateEditText.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103/androidapi/Appointment_Fixing_Api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<AppointmentResponse> call = apiService.bookAppointment(patientId, doctorId, diseaseDescription, appointmentDate);
        call.enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                if (response.isSuccessful()) {
                    AppointmentResponse appointmentResponse = response.body();
                    if (appointmentResponse != null) {
                        if (appointmentResponse.getMessage() != null) {
                            new AlertDialog.Builder(Appointment_Activity.this)
                                    .setTitle("Success")
                                    .setMessage("Appointment Fixed")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                            Toast.makeText(Appointment_Activity.this, appointmentResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (appointmentResponse.getError() != null) {
                            Toast.makeText(Appointment_Activity.this, appointmentResponse.getError(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Appointment_Activity.this, "Request failed. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AppointmentResponse> call, Throwable t) {

                Toast.makeText(Appointment_Activity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}