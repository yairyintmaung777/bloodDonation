package com.example.blooddonationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView receiver;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);


        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lgEmail = loginEmail.getText().toString();
                String lgPass = loginPassword.getText().toString();

                String a = "admin";
                String b = "123";

                Boolean loginSuccessful = dbHelper.checkLogin(lgEmail, lgPass);
                if (lgEmail.equals(a)  && lgPass.equals(b) ){
                    Intent intent = new Intent(getApplicationContext(), adminHomepage.class);
                    startActivity(intent);
                }
                else {
                    if(loginSuccessful) {
                        displayToast("Login Successful!");
                        Intent intent = new Intent(getApplicationContext(), homePage.class);
                        intent.putExtra("uname",lgEmail);
                        startActivity(intent);
                    }
                    else{
                        displayToast("Invalid" );
                        loginEmail.setText("");
                        loginPassword.setText("");
                    }
                }

            }
        });
    }

    public void singUpChange(View view) {
        Intent intent = new Intent(this, signIn.class);
        startActivity(intent);
    }

    public void displayToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}