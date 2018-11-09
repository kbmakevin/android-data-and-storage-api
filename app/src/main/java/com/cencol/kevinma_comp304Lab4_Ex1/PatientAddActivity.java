package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class PatientAddActivity extends PatientMutationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button addBtn = findViewById(R.id.patient_mutation_action_btn);

        //change button text
        addBtn.setText(R.string.patient_mutation_add_btn);

        //change button event handler
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "ADDING new patient!", Toast.LENGTH_SHORT).show();

                //get all of form field data into a ContentValues obj to send to db manager to add to database
                firstName = ((TextView) findViewById(R.id.patient_mutation_fname_editTxt)).getText().toString();
                lastName = ((TextView) findViewById(R.id.patient_mutation_lname_editTxt)).getText().toString();
                department = ((TextView) findViewById(R.id.patient_mutation_dept_editTxt)).getText().toString();
                doctorId = ((Doctor) ((Spinner) findViewById(R.id.patient_mutation_doctor_spinner)).getSelectedItem()).getDoctorId();
                room = ((TextView) findViewById(R.id.patient_mutation_dept_editTxt)).getText().toString();

                //initialize ContentValues object with the new nurse
                ContentValues contentValues = new ContentValues();
                contentValues.put("patientId", patientId);
                contentValues.put("firstName", firstName);
                contentValues.put("lastName", lastName);
                contentValues.put("department", department);
                contentValues.put("doctorId", doctorId);
                contentValues.put("room", room);
                try {
                    databaseManager.addRow("Patient", contentValues);
                    Toast.makeText(view.getContext(), "Added Patient: " + firstName + " " + lastName, Toast.LENGTH_SHORT).show();

                } catch (Exception exception) {
                    //
                    Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Error: ", exception.getMessage());
                }
            }
        });
    }
}
