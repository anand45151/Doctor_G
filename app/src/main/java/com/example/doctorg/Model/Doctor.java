package com.example.doctorg.Model;

public class Doctor {
    private String name;
    private String specialty;
    private String imageUrl;

    public Doctor(String name, String specialty, String imageUrl) {
        this.name = name;
        this.specialty = specialty;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
