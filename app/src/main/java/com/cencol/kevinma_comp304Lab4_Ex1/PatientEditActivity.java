package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class PatientEditActivity extends PatientMutationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button updateBtn = findViewById(R.id.patient_mutation_action_btn);

        //change button text
        updateBtn.setText(R.string.patient_mutation_update_btn);

        //change button event handler
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get all of form field data into a ContentValues obj to send to db manager to add to database
                firstName = ((TextView) findViewById(R.id.patient_mutation_fname_editTxt)).getText().toString();
                lastName = ((TextView) findViewById(R.id.patient_mutation_lname_editTxt)).getText().toString();
                department = ((TextView) findViewById(R.id.patient_mutation_dept_editTxt)).getText().toString();
                doctorId = ((Doctor) ((Spinner) findViewById(R.id.patient_mutation_doctor_spinner)).getSelectedItem()).getDoctorId();
                room = ((TextView) findViewById(R.id.patient_mutation_room_editTxt)).getText().toString();

                //initialize ContentValues object with the new nurse
                ContentValues contentValues = new ContentValues();
                contentValues.put("patientId", patientId);
                contentValues.put("firstName", firstName);
                contentValues.put("lastName", lastName);
                contentValues.put("department", department);
                contentValues.put("doctorId", doctorId);
                contentValues.put("room", room);
                try {
                    databaseManager.editRow("Patient",patientId,"patientId",contentValues);
                    Toast.makeText(view.getContext(), "Updated Patient: " + firstName + " " + lastName, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(), PatientListingActivity.class));
                } catch (Exception exception) {
                    //
                    Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Error: ", exception.getMessage());
                }
            }
        });


        //populate fields for display
        Patient selPatient = databaseManager.getPatientById(patientId);
        ((TextView) findViewById(R.id.patient_mutation_fname_editTxt)).setText(selPatient.getFirstName());
        ((TextView) findViewById(R.id.patient_mutation_lname_editTxt)).setText(selPatient.getLastName());
        ((TextView) findViewById(R.id.patient_mutation_dept_editTxt)).setText(selPatient.getDepartment());

        Doctor doctor = databaseManager.getDoctorById(selPatient.getDoctorId());
        Spinner docSpinner = findViewById(R.id.patient_mutation_doctor_spinner);
        docSpinner.setSelection(((ArrayAdapter) docSpinner.getAdapter()).getPosition(doctor));

        ((TextView) findViewById(R.id.patient_mutation_room_editTxt)).setText(selPatient.getRoom());
    }
}
