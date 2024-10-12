package com.example.doctorg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorg.Activities.Appointment_Activity;
import com.example.doctorg.Model.Doctor_response_model;
import com.example.doctorg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private Context context;
    String Image_URL = "http://192.168.0.103/Doctor_G_Main_WebSite/uploads/";
    private List<Doctor_response_model> doctorList;

    public DoctorAdapter(Context context, List<Doctor_response_model> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.doctor_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor_response_model doctor = doctorList.get(position);

        holder.nameTextView.setText(doctor.getDoctor_Name() != null ? doctor.getDoctor_Name() : "N/A");
        holder.specialtyTextView.setText(doctor.getDoctor_Specialty() != null ? doctor.getDoctor_Specialty() : "N/A");
        holder.experiencesTextView.setText(doctor.getDoctor_Experiences() != null ? doctor.getDoctor_Experiences() : "N/A");
        holder.locationTextView.setText(doctor.getDoctor_Location() != null ? doctor.getDoctor_Location() : "N/A");
        String imageUrl = Image_URL + doctor.getDoctor_Photo();
        Log.d("Image :", imageUrl);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.d5)
                .error(R.drawable.errr)
                .into(holder.photoImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Appointment_Activity.class);
            intent.putExtra("doctor_name", doctor.getDoctor_Name());
            intent.putExtra("doctor_specialty", doctor.getDoctor_Specialty());
            intent.putExtra("doctor_location", doctor.getDoctor_Location());
            intent.putExtra("doctor_experiences", doctor.getDoctor_Experiences());
            intent.putExtra("doctor_photo", doctor.getDoctor_Photo());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView specialtyTextView;
        TextView experiencesTextView;
        TextView locationTextView;
        ImageView photoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.doctor_name);
            specialtyTextView = itemView.findViewById(R.id.doctor_specialty);
            experiencesTextView = itemView.findViewById(R.id.doctor_experiences);
            locationTextView = itemView.findViewById(R.id.doctor_location);
            photoImageView = itemView.findViewById(R.id.doctor_photo);
        }
    }
}
