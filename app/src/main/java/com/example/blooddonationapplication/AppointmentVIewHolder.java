package com.example.blooddonationapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentVIewHolder extends RecyclerView.ViewHolder {
    TextView id;
    TextView username;
    TextView state;
    TextView centre;
    TextView date;
    TextView time;
    TextView bloodType;
    Button btnUpdate;
    Button btnDel;
    View v;

    public AppointmentVIewHolder(@NonNull View itemView, AppointmentClickListener listener) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.TV_id);
        username = (TextView)itemView.findViewById(R.id.UTV_Name) ;
        state= (TextView)itemView.findViewById(R.id.UTV_state) ;
        centre= (TextView)itemView.findViewById(R.id.UTV_Centre) ;
        date= (TextView)itemView.findViewById(R.id.UTV_Date) ;
        time= (TextView)itemView.findViewById(R.id.UTV_Time) ;
        bloodType= (TextView)itemView.findViewById(R.id.UTV_bloodType) ;

        btnUpdate = (Button) itemView.findViewById(R.id.bt_update);
        btnDel = (Button) itemView.findViewById(R.id.bt_adminDelete);

        btnDel.setOnClickListener(view -> {
            listener.onAppointDelClick(id.getText().toString());
        });

        btnUpdate.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), UpdateUserAppointment.class);
            i.putExtra(UpdateUserAppointment.ID, id.getText().toString());
            i.putExtra(UpdateUserAppointment.USERNAME, username.getText().toString());
            i.putExtra(UpdateUserAppointment.CENTRE, centre.getText().toString());
            i.putExtra(UpdateUserAppointment.DATE, date.getText().toString());
            i.putExtra(UpdateUserAppointment.TIME, time.getText().toString());
            view.getContext().startActivity(i);
        });

        v = itemView;
    }

}
