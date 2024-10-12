package com.example.doctorg.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorg.Adapter.DoctorAdapter;
import com.example.doctorg.Model.Doctor_response_model;
import com.example.doctorg.R;
import com.example.doctorg.Utils.ApiClient;
import com.example.doctorg.Controllers.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doctor_List_Activity extends AppCompatActivity {
    private RecyclerView doctorListRecyclerView;
    private DoctorAdapter doctorAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        doctorListRecyclerView = findViewById(R.id.doctorlist);
        doctorListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = ApiClient.getInstance().getApiService();
        fetchDoctorData(); // Fetch doctor data from API
    }

    private void fetchDoctorData() {
        Call<List<Doctor_response_model>> call = apiService.getDoctorList();
        call.enqueue(new Callback<List<Doctor_response_model>>() {
            @Override
            public void onResponse(Call<List<Doctor_response_model>> call, Response<List<Doctor_response_model>> response) {
                if (response.isSuccessful()) {
                    List<Doctor_response_model> doctorList = response.body();
                    if (doctorList != null && !doctorList.isEmpty()) {
                        doctorAdapter = new DoctorAdapter(Doctor_List_Activity.this, doctorList);
                        doctorListRecyclerView.setAdapter(doctorAdapter);  // Set adapter after getting data
                        Log.d("DoctorList", "Adapter is being set with " + doctorList.size() + " doctors");
                    } else {
                        Toast.makeText(Doctor_List_Activity.this, "No data found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Doctor_List_Activity.this, "Failed to load data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Doctor_response_model>> call, Throwable t) {
                Toast.makeText(Doctor_List_Activity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("DoctorListError", "Failed to fetch doctor data", t);
            }
        });
    }
}
