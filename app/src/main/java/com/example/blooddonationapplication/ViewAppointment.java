package com.example.blooddonationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAppointment extends AppCompatActivity {

    TextView ShowName,ShowCentre;
    TextView ShowDate,ShowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Intent getIntent = getIntent();
        String centre = getIntent.getStringExtra("centre");
        String date = getIntent.getStringExtra("date");
        String time = getIntent.getStringExtra("time");

        ShowName = findViewById(R.id.tvName);
        ShowCentre = findViewById(R.id.tvCentre);
        ShowDate = findViewById(R.id.tvDate);
        ShowTime = findViewById(R.id.tvTime);

        ShowName.setText(R.string.Uname);
        ShowCentre.setText(centre);
        ShowDate.setText(date);
        ShowTime.setText(time);

    }
}