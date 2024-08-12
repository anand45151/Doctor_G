package com.example.doctorg.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorg.Model.Doctor_response_model;
import com.example.doctorg.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context context;
    private List<Doctor_response_model> doctorList;

    public DoctorAdapter(Context context, List<Doctor_response_model> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
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

        String base64Image = doctor.getDoctor_Photo();
        if (base64Image != null && !base64Image.isEmpty()) {
            try {
                byte[] decodedString = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                if (decodedByte != null) {
                    holder.photoImageView.setImageBitmap(decodedByte);
                } else {
                    holder.photoImageView.setImageResource(R.drawable.d5); // Placeholder
                }
            } catch (Exception e) {
                Log.e("ImageError", "Error decoding base64 image", e);
                holder.photoImageView.setImageResource(R.drawable.errr);
            }
        } else {
            holder.photoImageView.setImageResource(R.drawable.d5);
        }
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
