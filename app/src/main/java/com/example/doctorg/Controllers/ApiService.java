package com.example.doctorg.Controllers;

import com.example.doctorg.Model.Doctor_response_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("fetch_Doctor.php")
    Call<List<Doctor_response_model>> getDoctorList();

}
