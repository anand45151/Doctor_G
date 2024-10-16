package com.example.doctorg.Controllers;

import com.example.doctorg.Model.AppointmentResponse;
import com.example.doctorg.Model.Doctor_response_model;
import com.example.doctorg.Model.LoginResponse;
import com.example.doctorg.Model.UserResponse;
import com.example.doctorg.Response.User_Registration_Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("Fetch_Dcotor_List_Api.php")
    Call<List<Doctor_response_model>> getDoctorList();

    @FormUrlEncoded
    @POST("Register_Patient_Api.php")
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
    @FormUrlEncoded
    @POST("Appointment_Fixing_Api.php")
    Call<AppointmentResponse> bookAppointment(
            @Field("patient_id") int patientId,
            @Field("doctor_id") int doctorId,
            @Field("disease_description") String diseaseDescription,
            @Field("appointment_date") String appointmentDate
    );


    @FormUrlEncoded
    @POST("Login_Patient_Api.php")
    Call<LoginResponse> loginUser(@Field("email") String email, @Field("f_name") String firstName);


    @GET("Fetch_User_Details_API.php")
    Call<UserResponse> fetchUserDetails(@Query("email") String email);



}
