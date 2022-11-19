package com.example.blooddonationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class userProfile extends AppCompatActivity {

    DatabaseHelper dbHelper;
    TextView tvName, tvPhone, tvEmail, tvBloodType, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvName = findViewById(R.id.tvProfileName);
        tvEmail = findViewById(R.id.tvProfileEmail);
        tvPhone = findViewById(R.id.tvProfilePhone);
        tvBloodType = findViewById(R.id.tvProfileBloodType);
        tvAddress = findViewById(R.id.tvProfileAddress);
        Intent intent = getIntent();

        String strE = intent.getStringExtra("Email");
        String strPh = intent.getStringExtra("PhoneNumber");
        String strN = intent.getStringExtra("Name");
        String strAdd = intent.getStringExtra("Address");
        String strBT = intent.getStringExtra("BloodType");

        tvName.setText(strN);
        tvEmail.setText(strE);
        tvPhone.setText("Phone Number:  " + strPh);
        tvBloodType.setText("Blood Type:  " + strBT);
        tvAddress.setText("Address:      " + strAdd);


    }
}