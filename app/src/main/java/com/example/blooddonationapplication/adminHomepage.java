package com.example.blooddonationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
    }

    public void ViewUser(View view) {
        Intent intent = new Intent(this,UserListActivity.class);
        startActivity(intent);
    }

    public void ViewAppontment(View view) {
        Intent intent = new Intent(this,AdminCheckAppointment.class);
        startActivity(intent);
    }
}