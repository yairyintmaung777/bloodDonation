package com.example.blooddonationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "com.example.blooddonationapplication.extra.Mesaage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void introSignIn(View view) {
        Intent intent = new Intent(this, signIn.class);
        startActivity(intent);
    }

    public void introLogin(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }


}

