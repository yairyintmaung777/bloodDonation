package com.example.blooddonationapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateUserAppointment extends AppCompatActivity {
    static String ID = "id";
    static String USERNAME = "username";
    static String CENTRE = "centre";
    static String DATE = "date";
    static String TIME = "time";
    String name="";
    String time = "";
    String centre = "";
    String date = "";
    String id = "";
    TextView tvname;
    EditText edtCentre;
    EditText edtDate;
    EditText edtTime;
    Button btnUpdate;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_appointment);

        tvname = findViewById(R.id.tv_updatename);
        edtCentre = findViewById(R.id.et_updateCentre);
        edtDate = findViewById(R.id.et_updateDate);
        edtTime = findViewById(R.id.et_updateTime);
        btnUpdate = findViewById(R.id.btn_act_update2);

        dbHelper = new DatabaseHelper(this);
        Intent gettingIntent = getIntent();
        id = gettingIntent.getStringExtra(ID);
        name = gettingIntent.getStringExtra(USERNAME);
        centre = gettingIntent.getStringExtra(CENTRE);
        date = gettingIntent.getStringExtra(DATE);
        time = gettingIntent.getStringExtra(TIME);

        tvname.setText(name);
        edtCentre.setText(centre);
        edtDate.setText(date);
        edtTime.setText(time);

        btnUpdate.setOnClickListener(view -> {
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(UpdateUserAppointment.this);
            myAlertBuilder.setTitle("Confirm to Update?");
            myAlertBuilder.setMessage("Press Yes to Update the Appointment");
            myAlertBuilder.setPositiveButton("YES", new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked the OK button.
                            dbHelper.updateAppointment(id, edtCentre.getText().toString(), edtDate.getText().toString(), edtTime.getText().toString());

                            Toast.makeText(getApplicationContext(), "Update Successful!!",
                                    Toast.LENGTH_SHORT).show();

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

        });
    }


    public void OnBack(View view) {
        Intent intent = new Intent(this,AdminCheckAppointment.class);
        startActivity(intent);
    }
}