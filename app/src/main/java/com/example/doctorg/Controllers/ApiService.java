package com.example.doctorg.Controllers;

import com.example.doctorg.Model.Doctor_response_model;
import com.example.doctorg.Response.User_Registration_Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("fetch_Doctor.php")
    Call<List<Doctor_response_model>> getDoctorList();

    @FormUrlEncoded
    @POST("register.php")
    Call<User_Registration_Response> registerUser(
            @Field("f_name") String f_name,
            @Field("l_name") String l_name,
            @Field("gender") String gender,
            @Field("address") String address,
            @Field("city") String city,
            @Field("state") String state,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("dob") String dob
    );

}
