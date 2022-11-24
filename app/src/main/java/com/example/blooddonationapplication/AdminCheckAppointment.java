package com.example.blooddonationapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminCheckAppointment extends AppCompatActivity implements AppointmentClickListener {
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    adminAppointmentAdapter adapter;
    Button btSearch;
    EditText etSearch;
    List<Appointment> appointment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_appointment);
        dbHelper = new DatabaseHelper(this);

        etSearch=findViewById(R.id.et_search);
        btSearch=findViewById(R.id.bt_search);
        recyclerView = findViewById(R.id.rv_appointment);

        appointment = dbHelper.getAllAppointment();

        if(appointment.isEmpty()){
            recyclerView.setVisibility(View.GONE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter = new adminAppointmentAdapter(appointment, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //searching
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = etSearch.getText().toString();

            }
        });
    }

    public void GoUpdate(View view) {
        Intent intent = new Intent(this,UpdateUserAppointment.class);
        startActivity(intent);
    }

    @Override
    public void onAppointDelClick(String id) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(AdminCheckAppointment.this);
        myAlertBuilder.setTitle("Do you sure to DELETE the Appointment??");
        myAlertBuilder.setMessage("Press CONFIRM To DELETE the Appointment");


        myAlertBuilder.setPositiveButton("CONFIRM", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked the CONFIRM button.
                        dbHelper.deleteAppointment(id);
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


        adapter = new adminAppointmentAdapter(appointment, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
