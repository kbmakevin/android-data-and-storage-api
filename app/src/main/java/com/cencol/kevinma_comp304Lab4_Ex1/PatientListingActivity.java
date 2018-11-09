package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class PatientListingActivity extends BaseNavigationActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_listing);

        databaseManager = new DatabaseManager(this);

        //populate listview with any existing patients
        Patient[] existingPatients = databaseManager.getAllPatients();
        if (existingPatients.length > 0) {
            ListView listView = findViewById(R.id.patient_listing_listview);
            listView.setAdapter(new ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, existingPatients));
        }

        //attach event handlers
        final ListView patientListingListView = findViewById(R.id.patient_listing_listview);
        patientListingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Patient patientSelected = (Patient) patientListingListView.getItemAtPosition(position);
                int selectedPatientId = patientSelected.getPatientId();
                startActivity(new Intent(view.getContext(), PatientViewActivity.class).putExtra("selectedPatientId", selectedPatientId + ""));
            }
        });

        findViewById(R.id.patient_listing_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get as string first to check str length before converting to int
                String newPatientId = ((TextView) findViewById(R.id.patient_id_edittext)).getText().toString();
                //patient id field must be filled in
                if (newPatientId.length() > 0) {

                    //take id from patient field as new patient id
                    //patient id must not already exist in db - need to check
                    //select patient by id and see if returned result
                    Patient patient = databaseManager.getPatientById(Integer.parseInt(newPatientId));
                    //did not find matching patient with the given id
                    if (patient == null) {
                        startActivity(new Intent(view.getContext(), PatientAddActivity.class).putExtra("patientId", newPatientId));

                    } else {
                        Toast.makeText(view.getContext(), "Patient with the ID: " + newPatientId + " already exists!\nPlease enter a different ID if you want to add a new patient.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(view.getContext(), "Please fill in a patient Id!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //disable the current active menu item to prevent end user confusion
        menu.getItem(2).setEnabled(false);
        return true;
    }
}
