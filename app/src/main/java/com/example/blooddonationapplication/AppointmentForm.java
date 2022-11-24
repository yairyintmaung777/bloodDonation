package com.example.blooddonationapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AppointmentForm extends AppCompatActivity {

    String name, StateSelect, centreSelect, bloodtype,Timemessage, DateMessage;
    private Spinner StateSpinner, centreSpinner,BloodTypeSpin;
    DatabaseHelper dbHelper;
    EditText ET_Date;
    EditText ET_Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_form);
        dbHelper= new DatabaseHelper(AppointmentForm.this);
        centreSpinner = findViewById(R.id.centreSpinner);
        centreSpinner.getResources().getStringArray(R.array.Negeri_Sembilan);
        BloodTypeSpin = findViewById(R.id.Bloodspinner);



        ArrayAdapter<CharSequence> SelangorAdapter = ArrayAdapter.createFromResource(this, R.array.Selangor,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> NegeriAdapter = ArrayAdapter.createFromResource(this, R.array.Negeri_Sembilan,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> JohorAdapter = ArrayAdapter.createFromResource(this, R.array.Johor,
                android.R.layout.simple_spinner_item);

        JohorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SelangorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NegeriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);


        StateSpinner = findViewById(R.id.StateSpinner);
        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StateSelect = StateSpinner.getSelectedItem().toString();

                SetSpinner(StateSelect);

            }

            private void SetSpinner(String stateSelect) {

                if (StateSelect.equals("Selangor"))
                    centreSpinner.setAdapter(SelangorAdapter);

                if (StateSelect.equals("Negeri Sembilan"))
                    centreSpinner.setAdapter(NegeriAdapter);

                if (StateSelect.equals("Johor"))
                    centreSpinner.setAdapter(JohorAdapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        centreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                centreSelect = centreSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        BloodTypeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bloodtype = BloodTypeSpin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.datepicker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string + "/" + year_string);

        ET_Date = (findViewById(R.id.et_Date));
        ET_Date.setText("Date:" + dateMessage);
        Toast.makeText(this, getString(R.string.date) + dateMessage,
                Toast.LENGTH_SHORT).show();
        DateMessage = dateMessage;
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), getString(R.string.datepicker));
    }

    public void processTimePickerResult(int hour, int minute) {
        String formatted_hour = String.format("%02d", hour);
        String formatted_minute = String.format("%02d", minute);
        String timeMessage = (formatted_hour + ":" + formatted_minute);

        ET_Time = (findViewById(R.id.et_time));
        ET_Time.setText("Time:" + timeMessage);
        Timemessage = timeMessage;
    }


    public void OnSubmit(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(AppointmentForm.this);
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage(R.string.alert_message);
        String message = getString(R.string.message);
        Intent intent = getIntent();
        name = intent.getStringExtra("uname");

        myAlertBuilder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked the OK button.

                        if (DateMessage == null || Timemessage == null) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        } else {
                            dbHelper.insertAppointment(name, StateSelect, centreSelect, DateMessage, Timemessage, bloodtype);
                            Toast.makeText(getApplicationContext(), "Successful to Submit the Appointment!!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), homePage.class);
                            intent.putExtra("uname",name);
                            startActivity(intent);

                        }
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

    }
}