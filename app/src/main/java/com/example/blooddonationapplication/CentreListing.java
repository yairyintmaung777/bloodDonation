package com.example.blooddonationapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CentreListing extends AppCompatActivity {
    String StateSelect,centreSelect;
    TextView showCentreAddress,centreName;
    Spinner StateSpinner, centreSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre_listing);


        ArrayAdapter<CharSequence> SelangorAdapter = ArrayAdapter.createFromResource(this,R.array.Selangor,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> NegeriAdapter = ArrayAdapter.createFromResource(this,R.array.Negeri_Sembilan,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> JohorAdapter = ArrayAdapter.createFromResource(this,R.array.Johor,
                android.R.layout.simple_spinner_item);

        JohorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SelangorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        NegeriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //centreSpinner.setAdapter(JohorAdapter);

        StateSpinner= findViewById(R.id.StateSpinner);
        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StateSelect = StateSpinner.getSelectedItem().toString();
                showCentreAddress=findViewById(R.id.tvCentreAddress);


                SetSpinner(StateSelect);


            }

            private void SetSpinner(String StateSelect) {

                if(StateSelect.equals("Selangor"))
                    centreSpinner.setAdapter(SelangorAdapter);

                if(StateSelect.equals("Negeri Sembilan"))
                    centreSpinner.setAdapter(NegeriAdapter);

                if(StateSelect.equals("Johor"))
                    centreSpinner.setAdapter(JohorAdapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        centreSpinner = findViewById(R.id.centreSpinner);
        centreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                centreSelect = centreSpinner.getSelectedItem().toString();

                getCentre(centreSelect);

            }

            private void getCentre(String centreSelect) {
               centreName = findViewById(R.id.tvCentreName);
                centreName.setText(centreSelect);

                switch (centreSelect) {
                    case "Blood Donation Centre,Subang Jaya":
                        showCentreAddress.setText(R.string.S1);
                        break;
                    case "Pusat Darah Negara 1 Utama,Petaling Jaya":
                        showCentreAddress.setText(R.string.S2);
                        break;
                    case "Pusat Darah Negara Midvalley Donation Suite,Kuala Lumpur":
                        showCentreAddress.setText(R.string.S3);
                        break;
                    case "Pusat Derma Darah Hospital Tuanku Jaafar":
                        showCentreAddress.setText(R.string.N1);
                        break;
                    case "Kompleks Unit Rawatan Harian (Ambulatory Care Complex), Hospital Tuanku Ja'afa":
                        showCentreAddress.setText(R.string.N2);
                        break;
                    case "Pusat Pendermaan Darah Hospital Sultanah Aminah":
                        showCentreAddress.setText(R.string.J1);
                        break;
                    case "Department of Transfusion Medicine, Sultanah Aminah Hospital":
                        showCentreAddress.setText(R.string.J2);
                        break;
                    case "Sultanah Aminah Hospital, Johor Bahru":
                        showCentreAddress.setText(R.string.J3);
                        break;
               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}

