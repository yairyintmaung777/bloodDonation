package com.example.blooddonationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userAppointmentAdapter extends RecyclerView.Adapter<userAppointmentViewHolder> {
    List<Appointment> appointments;
    Context c;
    AppointmentClickListener listener;
    public userAppointmentAdapter(List<Appointment> appointment, AppointmentClickListener listener) {
        this.appointments = appointment;
        this.c = c;
        this.listener = listener;
    }
    @NonNull
    @Override
    public userAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View v = inflater.inflate(R.layout.view_appointment_item, parent, false);

        userAppointmentViewHolder viewHolder = new userAppointmentViewHolder(v, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userAppointmentViewHolder holder, int position) {
        holder.username.setText(appointments.get(position).username);
        holder.state.setText(appointments.get(position).state);;
        holder.centre.setText(appointments.get(position).centre);;
        holder.date.setText(appointments.get(position).date);;
        holder.time.setText(appointments.get(position).time);;
        holder.bloodType.setText(appointments.get(position).bloodType);;
        holder.id.setText(appointments.get(position).id);
    }


    @Override
    public int getItemCount() {
        return appointments.size();
    }
}
