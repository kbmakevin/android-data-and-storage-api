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

public class TestMutationActivity extends BaseNavigationActivity {

    //used for creating new tests or modifying old tests
    protected DatabaseManager databaseManager;
    protected int testId;
    protected int patientId;
    protected int nurseId;
    protected int systolicBPL;
    protected int diastolicBPL;
    protected boolean BPH;
    protected double temperature;
    protected double height;
    protected double weight;
    protected double BMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mutation);

        //both add/edit get test id passed in from previous activity
        Intent intent = getIntent();
        testId = Integer.parseInt(intent.getStringExtra("testId"));

        //get and fill in testId from previous activity
        TextView editText = findViewById(R.id.test_mutation_id_val);
        editText.setText(testId + "");

        //populate the patient spinner with existing patient & nurse spinner with nurses
        databaseManager = new DatabaseManager(this);

        Patient[] existingPatients = databaseManager.getAllPatients();
        if (existingPatients.length > 0) {
            Spinner patientSpinner = findViewById(R.id.test_mutation_patient_spinner);
            patientSpinner.setAdapter(new ArrayAdapter<Patient>(this, android.R.layout.simple_spinner_dropdown_item, existingPatients));
        }

        Nurse[] existingNurses = databaseManager.getAllNurses();
        if (existingNurses.length > 0) {
            Spinner patientSpinner = findViewById(R.id.test_mutation_nurse_spinner);
            patientSpinner.setAdapter(new ArrayAdapter<Nurse>(this, android.R.layout.simple_spinner_dropdown_item, existingNurses));
        }

        //both edit and add test forms should go back to test listing when "cancelled" transaction
        findViewById(R.id.test_mutation_cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TestListingActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //disable the current active menu item to prevent end user confusion
        menu.getItem(3).setEnabled(false);
        return true;
    }
}
