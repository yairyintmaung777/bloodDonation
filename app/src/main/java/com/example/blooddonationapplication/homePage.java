package com.example.blooddonationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class homePage extends AppCompatActivity {

    TextView Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //Tv = findViewById(R.id.textView8);
        //Tv.setText(str1);
    }

    public void homeToUser(View view) {
        Intent intent = new Intent(getApplicationContext(), userProfile.class);
        Intent gettingIntent = getIntent();

        String strE = gettingIntent.getStringExtra("Email");
        String strPw = gettingIntent.getStringExtra("Password");
        String strPh = gettingIntent.getStringExtra("PhoneNumber");
        String strN = gettingIntent.getStringExtra("Name");
        String strAdd = gettingIntent.getStringExtra("Address");
        String strBT = gettingIntent.getStringExtra("BloodType");

        intent.putExtra("Email",strE);
        intent.putExtra("Password",strPw);
        intent.putExtra("PhoneNumber",strPh);
        intent.putExtra("Name",strN);
        intent.putExtra("Address",strAdd);
        intent.putExtra("BloodType",strBT);

        startActivity(intent);
    }


    public void OnAddAppointment(View view) {
        Intent intent = new Intent(this,AppointmentForm.class);
        startActivity(intent);
    }

    public void OnCentreListing(View view) {
        Intent intent = new Intent(this,CentreListing.class);
        startActivity(intent);
    }

    public void OnThingToDo(View view) {
        Intent intent = new Intent(this,ThingToDo.class);
        startActivity(intent);
    }
}
