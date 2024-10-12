package com.example.doctorg.Model;

public class LoginResponse {
    private boolean success;
    private String message;
    private String patient_id;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getPatientId() {
        return patient_id;
    }
}
