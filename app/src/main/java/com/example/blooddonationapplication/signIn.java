package com.example.blooddonationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signIn extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "com.example.blooddonationapplication.extra.Mesaage";

    DatabaseHelper dbHelper;
    EditText signName, signEmail, signPhoneNumber, signPassword, signAddress;
    String signBloodType;
    Button signButton;
    Spinner BloodTypeSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dbHelper = new DatabaseHelper(this);
        signName = findViewById(R.id.signName);
        signEmail = findViewById(R.id.signEmail);
        signPhoneNumber = findViewById(R.id.signPhoneNumber);
        BloodTypeSpin = findViewById(R.id.blood_spinner);
        signPassword = findViewById(R.id.signPassword);
        signAddress = findViewById(R.id.signAddress);

        BloodTypeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                signBloodType = BloodTypeSpin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        signButton = findViewById(R.id.signButton);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkDataEntered()) {
                    String SgEmail = signEmail.getText().toString();
                    String SgName = signName.getText().toString();
                    String SgPhone = signPhoneNumber.getText().toString();
                    String SgBlood = BloodTypeSpin.getSelectedItem().toString();
                    String SgPassword = signPassword.getText().toString();
                    String SgAddress = signAddress.getText().toString();

                    Boolean taken = dbHelper.checkUsernameAndEmail(SgName, SgEmail);
                    if (!taken) {
                        Boolean insertSuccessfully = dbHelper.insert(SgName, SgPassword, SgPhone, SgEmail, SgBlood, SgAddress);
                        if (insertSuccessfully) {
                            displayToast("Registered Successfully!");
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        }else{
                            displayToast("Username or Email has been taken!");
                        }
                    }
                }
            }
        });
    }

    public void loginChange(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public Boolean checkDataEntered() {
        if (isEmpty(signName)) {
            Toast T = Toast.makeText(this, "You need enter Name to register!", Toast.LENGTH_SHORT);
            T.show();
            return false;
        }
        if (isEmpty(signEmail)) {
            Toast T = Toast.makeText(this, "You need enter Email to register!", Toast.LENGTH_SHORT);
            T.show();
            return false;
        }
        if (isEmpty(signPhoneNumber)) {
            Toast T = Toast.makeText(this, "You need enter Phone Number to register!", Toast.LENGTH_SHORT);
            T.show();
            return false;
        } else if (isEmpty(signPassword)) {
            Toast T = Toast.makeText(this, "You need enter Password to register!", Toast.LENGTH_SHORT);
            T.show();
            return false;
        } else if (isEmail(signEmail) == false) {
            signEmail.setError("Enter Valid Email!");
            return false;
        }

        return true;
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}