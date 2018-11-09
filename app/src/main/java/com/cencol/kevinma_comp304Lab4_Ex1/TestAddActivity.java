package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class TestAddActivity extends TestMutationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button addBtn = findViewById(R.id.test_mutation_action_btn);

        //change button text
        addBtn.setText(R.string.patient_mutation_add_btn);

        //change button event handler
        addBtn.setOnClickListener(new View.OnClickListener() {
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
                    databaseManager.addRow("Test", contentValues);
                    Toast.makeText(view.getContext(), "Added Test ID=" + testId, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(view.getContext(), TestListingActivity.class));
                } catch (Exception exception) {
                    //
                    Toast.makeText(view.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Error: ", exception.getMessage());
                }
            }
        });
    }
}
