package com.example.blooddonationapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateUserActivity extends AppCompatActivity {


    static String ID = "id";
    static String USERNAME = "username";
    static String PHONE = "phoneNumber";
    static String ADDRESS = "address";

    String username = "";
    String phone = "";
    String address = "";
    String id = "";

    EditText edtUsername;
    EditText edtPhone;
    EditText edtAddress;
    Button btnUpdate;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        ActionBar actionBar = getSupportActionBar();

        edtUsername = findViewById(R.id.ed_username);
        edtAddress = findViewById(R.id.ed_address);
        edtPhone = findViewById(R.id.ed_phone);

        btnUpdate = findViewById(R.id.btn_act_update);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbHelper = new DatabaseHelper(this);

        id = getIntent().getStringExtra(ID);
        username = getIntent().getStringExtra(USERNAME);
        phone = getIntent().getStringExtra(PHONE);
        address = getIntent().getStringExtra(ADDRESS);

        edtUsername.setText(username);
        edtPhone.setText(phone);
        edtAddress.setText(address);

        btnUpdate.setOnClickListener(view -> {
            dbHelper.updateUser(id, edtUsername.getText().toString(), edtPhone.getText().toString(), edtAddress.getText().toString());
            Intent i = new Intent(this, UserListActivity.class);
            startActivity(i);
            finish();

        });

    }
}