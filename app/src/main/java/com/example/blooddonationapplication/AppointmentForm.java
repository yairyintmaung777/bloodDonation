package com.example.blooddonationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AppointmentForm extends AppCompatActivity {

    String StateSelect, centreSelect, Timemessage, DateMessage;
    private Spinner StateSpinner, centreSpinner;

    EditText ET_Date;
    EditText ET_Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_form);

        centreSpinner = findViewById(R.id.centreSpinner);
        centreSpinner.getResources().getStringArray(R.array.Negeri_Sembilan);

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
        String message = getString(R.string.message);
        if (DateMessage == null || Timemessage == null) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), ViewAppointment.class);

            intent.putExtra("centre", centreSelect);
            intent.putExtra("date", DateMessage);
            intent.putExtra("time", Timemessage);
            Toast.makeText(this, "Appointment Submitted!!", Toast.LENGTH_SHORT).show();
            startActivity(intent);


        }
    }
}