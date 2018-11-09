package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class TestEditActivity extends TestMutationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button updateBtn = findViewById(R.id.test_mut_action_btn);

        //change button text
        updateBtn.setText(R.string.patient_mutation_update_btn);

        ((Button) findViewById(R.id.test_mut_cancel_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TestListingActivity.class));
            }
        });

        //change button event handler
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get all of form field data into a ContentValues obj to send to db manager to add to database
                patientId = ((Patient) ((Spinner) findViewById(R.id.test_mutation_patient_spinner)).getSelectedItem()).getPatientId();
                nurseId = ((Nurse) ((Spinner) findViewById(R.id.test_mutation_nurse_spinner)).getSelectedItem()).getNurseId();
                systolicBPL = Integer.parseInt(((TextView) findViewById(R.id.test_mutation_sysbpl_val)).getText().toString());
                diastolicBPL = Integer.parseInt(((TextView) findViewById(R.id.test_mutation_diabpl_val)).getText().toString());
                //boolean but SQLite stores booleans True/False as 1/0
                BPH = ((CheckBox) findViewById(R.id.test_mutation_bph_val)).isChecked();
                temperature = Double.parseDouble(((TextView) findViewById(R.id.test_mutation_temp_val)).getText().toString());
                height = Double.parseDouble(((TextView) findViewById(R.id.test_mutation_height_val)).getText().toString());
                weight = Double.parseDouble(((TextView) findViewById(R.id.test_mutation_weight_val)).getText().toString());
                BMI = Double.parseDouble(((TextView) findViewById(R.id.test_mutation_bmi_val)).getText().toString());

                //initialize ContentValues object with the new nurse
                ContentValues contentValues = new ContentValues();
                contentValues.put("testId", testId);
                contentValues.put("patientId", patientId);
                contentValues.put("nurseId", nurseId);
                contentValues.put("systolicBPL", systolicBPL);
                contentValues.put("diastolicBPL", diastolicBPL);
                contentValues.put("BPH", BPH ? 1 : 0);
                contentValues.put("temperature", temperature);
                contentValues.put("height", height);
                contentValues.put("weight", weight);
                contentValues.put("BMI", BMI);

                try {
                    databaseManager.editRow("Test", testId, "testId", contentValues);
                    Toast.makeText(view.getContext(), "Updated Test: " + testId + " for Patient ID=" + patientId, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(), TestListingActivity.class));
                } catch (Exception exception) {
                    //
                    Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Error: ", exception.getMessage());
                }
            }
        });


        //populate fields for display
        Test selTest = databaseManager.getTestById(testId);

        Nurse nurse = databaseManager.getNurseById(selTest.getNurseId());
        Spinner nurseSpinner = findViewById(R.id.test_mutation_nurse_spinner);
        nurseSpinner.setSelection(((ArrayAdapter) nurseSpinner.getAdapter()).getPosition(nurse));

        Patient patient = databaseManager.getPatientById(selTest.getPatientId());
        Spinner patientSpinner = findViewById(R.id.test_mutation_patient_spinner);
        patientSpinner.setSelection(((ArrayAdapter) patientSpinner.getAdapter()).getPosition(patient));


        ((TextView) findViewById(R.id.test_mutation_sysbpl_val)).setText(selTest.getSystolicBPL() + "");
        ((TextView) findViewById(R.id.test_mutation_diabpl_val)).setText(selTest.getDiastolicBPL() + "");

        ((CheckBox) findViewById(R.id.test_mutation_bph_val)).setChecked(selTest.isBPH());



        ((TextView) findViewById(R.id.test_mutation_temp_val)).setText(selTest.getTemperature()+"");
        ((TextView) findViewById(R.id.test_mutation_height_val)).setText(selTest.getHeight()+"");
        ((TextView) findViewById(R.id.test_mutation_weight_val)).setText(selTest.getWeight()+"");
        ((TextView) findViewById(R.id.test_mutation_bmi_val)).setText(selTest.getBMI()+"");
    }
}
