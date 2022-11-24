package com.example.blooddonationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adminAppointmentAdapter extends RecyclerView.Adapter<AppointmentVIewHolder>{
    List<Appointment> appointments;
    Context c;
    AppointmentClickListener listener;

    public adminAppointmentAdapter(List<Appointment> appointment, AppointmentClickListener listener) {
        this.appointments = appointment;
        this.c = c;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View v = inflater.inflate(R.layout.admin_check_appointment_list, parent, false);

       AppointmentVIewHolder viewHolder = new AppointmentVIewHolder(v, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentVIewHolder holder, int position) {
        holder.username.setText(appointments.get(position).username);
        holder.state.setText(appointments.get(position).state);
        holder.centre.setText(appointments.get(position).centre);
        holder.date.setText(appointments.get(position).date);
        holder.time.setText(appointments.get(position).time);
        holder.bloodType.setText(appointments.get(position).bloodType);
        holder.id.setText(appointments.get(position).id);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}


