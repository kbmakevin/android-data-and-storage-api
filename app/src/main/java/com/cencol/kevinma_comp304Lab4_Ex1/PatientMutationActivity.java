package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.cencol.kevinma_comp304lab4.R;

public class PatientMutationActivity extends BaseNavigationActivity {

    //used for creating new patients or modifying old patients
    protected DatabaseManager databaseManager;
    protected int patientId;
    protected String firstName;
    protected String lastName;
    protected String department;
    protected int doctorId;
    protected String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_mutation);

        //both add/edit get patient id passed in from previous activity
        Intent intent = getIntent();
        patientId = Integer.parseInt(intent.getStringExtra("patientId"));

        //get and fill in patientid from previous activity
        TextView editText = findViewById(R.id.patient_mutation_id_value);
        editText.setText(patientId + "");

        //populate the doctor spinner with existing doctors
        databaseManager = new DatabaseManager(this);

        Doctor[] existingDoctors = databaseManager.getAllDoctors();
        if (existingDoctors.length > 0) {
            Spinner doctorSpinner = findViewById(R.id.patient_mutation_doctor_spinner);
            doctorSpinner.setAdapter(new ArrayAdapter<Doctor>(this, android.R.layout.simple_spinner_dropdown_item, existingDoctors));
        }

        //both edit and add patient forms should go back to patient listing when "cancelled" transaction
        findViewById(R.id.patient_mutation_cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PatientListingActivity.class));
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
