package com.example.blooddonationapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class userAppointmentViewHolder extends RecyclerView.ViewHolder {
    TextView id;
    TextView username;
    TextView state;
    TextView centre;
    TextView date;
    TextView time;
    TextView bloodType;
    Button btnDel;
    View v;

    public userAppointmentViewHolder(@NonNull View itemView, AppointmentClickListener listener) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.UTV_ID);
        username = (TextView)itemView.findViewById(R.id.UTV_Name) ;
        state= (TextView)itemView.findViewById(R.id.UTV_state) ;
        centre= (TextView)itemView.findViewById(R.id.UTV_Centre) ;
        date= (TextView)itemView.findViewById(R.id.UTV_Date) ;
        time= (TextView)itemView.findViewById(R.id.UTV_Time) ;
        bloodType= (TextView)itemView.findViewById(R.id.UTV_bloodType) ;
        btnDel = (Button) itemView.findViewById(R.id.bt_adminDelete);

        btnDel.setOnClickListener(view -> {
            listener.onAppointDelClick(id.getText().toString());
        });
    }
}
