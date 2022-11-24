package com.example.blooddonationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class homePage extends AppCompatActivity {

    TextView Tv;
    DatabaseHelper dbHelper;

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
        Intent gettingIntent = getIntent();
        final String name = gettingIntent.getStringExtra("uname");
        intent.putExtra("uname",name);
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

    public void OnGoViewAppointment(View view) {
        Intent gettingIntent = getIntent();
        String name = gettingIntent.getStringExtra("uname");
        dbHelper = new DatabaseHelper(this);
        Boolean gotAppointment = dbHelper.checkAppointment(name);

        if(gotAppointment){
        Intent intent = new Intent(this,ViewAppointment.class);
        intent.putExtra("uname",name);
        startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"There is no Appointment for You!",Toast.LENGTH_SHORT).show();
        }

    }
}
