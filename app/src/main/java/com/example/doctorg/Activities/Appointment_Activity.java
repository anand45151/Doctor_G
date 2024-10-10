package com.example.doctorg.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctorg.R;

public class Appointment_Activity extends AppCompatActivity {
    private TextView doctorNameTextView, doctorSpecialtyTextView, doctorLocationTextView, doctorExperiencesTextView;
    private ImageView doctorPhotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        doctorNameTextView = findViewById(R.id.doctor_name_appointment);
        doctorSpecialtyTextView = findViewById(R.id.doctor_specialty_appointment);
        doctorLocationTextView = findViewById(R.id.doctor_location_appointment);
        doctorExperiencesTextView = findViewById(R.id.doctor_experiences_appointment);
        doctorPhotoImageView = findViewById(R.id.doctor_photo_appointment);
        String doctorName = getIntent().getStringExtra("doctor_name");
        String doctorSpecialty = getIntent().getStringExtra("doctor_specialty");
        String doctorLocation = getIntent().getStringExtra("doctor_location");
        String doctorExperiences = getIntent().getStringExtra("doctor_experiences");
        String doctorPhoto = getIntent().getStringExtra("doctor_photo");

        doctorNameTextView.setText(doctorName);
        doctorSpecialtyTextView.setText(doctorSpecialty);
        doctorLocationTextView.setText(doctorLocation);
        doctorExperiencesTextView.setText(doctorExperiences);

        if (doctorPhoto != null && !doctorPhoto.isEmpty()) {
            byte[] decodedString = Base64.decode(doctorPhoto, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            doctorPhotoImageView.setImageBitmap(decodedByte);
        }

    }
}