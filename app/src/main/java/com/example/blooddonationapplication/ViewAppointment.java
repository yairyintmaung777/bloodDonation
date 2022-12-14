package com.example.blooddonationapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAppointment extends AppCompatActivity implements AppointmentClickListener{

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    userAppointmentAdapter adapter;
    List<Appointment> appointment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.RVuser_viewAppointment);
        Intent gettingIntent = getIntent();
        String name = gettingIntent.getStringExtra("uname");
        appointment = dbHelper.getSpecificAppointment(name);

        if(appointment.isEmpty()){
            recyclerView.setVisibility(View.GONE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter = new userAppointmentAdapter(appointment, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void onAppointDelClick(String ID) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(ViewAppointment.this);
        myAlertBuilder.setTitle("Do you sure to DELETE the Appointment??");
        myAlertBuilder.setMessage("Press CONFIRM To DELETE the Appointment");


        myAlertBuilder.setPositiveButton("CONFIRM", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked the CONFIRM button.
                        dbHelper.deleteAppointment(ID);
                        appointment = dbHelper.getAllAppointment();
                        Toast.makeText(getApplicationContext(), "Successful to Delete the appointment! ",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());

                    }

                });
        myAlertBuilder.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the dialog.
                        Toast.makeText(getApplicationContext(), "Pressed Cancel",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        // Create and show the AlertDialog.
        myAlertBuilder.show();


        adapter = new userAppointmentAdapter(appointment, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}