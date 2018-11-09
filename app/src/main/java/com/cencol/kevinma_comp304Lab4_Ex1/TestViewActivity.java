package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cencol.kevinma_comp304lab4.R;

public class TestViewActivity extends BaseNavigationActivity {

    private DatabaseManager databaseManager;
    private int testId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        Intent intent = getIntent();
        testId = Integer.parseInt(intent.getStringExtra("selectedTestId"));

        ((Button) findViewById(R.id.test_view_action_btn)).setText(R.string.patient_mutation_update_btn);

        //get and fill in patientid from previous activity
        TextView editText = findViewById(R.id.test_view_id_val);
        editText.setText(testId + "");

        //get selected patient details and populate web form fields
        databaseManager = new DatabaseManager(this);

        //don't need check, if got to this page patient has to exist
        //populate fields for display
        Test selTest = databaseManager.getTestById(testId);

        Patient patient = databaseManager.getPatientById(selTest.getPatientId());
        ((TextView) findViewById(R.id.test_view_patient_val)).setText(patient.toString());


        Nurse nurse = databaseManager.getNurseById(selTest.getNurseId());
        ((TextView) findViewById(R.id.test_view_nurse_val)).setText(nurse.toString());


        ((TextView) findViewById(R.id.testViewSysBp)).setText(selTest.getSystolicBPL() + "");
        ((TextView) findViewById(R.id.testViewDiaBp)).setText(selTest.getDiastolicBPL() + "");

        ((TextView) findViewById(R.id.test_view_bph_val)).setText(selTest.isBPH() ? "Y" : "N");

        ((TextView) findViewById(R.id.test_view_temp_val)).setText(selTest.getTemperature() + "");
        ((TextView) findViewById(R.id.test_view_height_val)).setText(selTest.getHeight() + "");
        ((TextView) findViewById(R.id.test_view_weight_val)).setText(selTest.getWeight() + "");
        ((TextView) findViewById(R.id.test_view_bmi_val)).setText(selTest.getBMI() + "");

        SharedPreferences shared_prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String authRole = shared_prefs.getString("auth_role", null);

        //doctors can only view tests, no update or add
        if (authRole.equals("Doctor")) {
            findViewById(R.id.test_view_action_btn).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.test_view_action_btn).setVisibility(View.VISIBLE);
        }


        //add event handlers
        ((Button) findViewById(R.id.test_view_action_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TestEditActivity.class).putExtra("testId", testId + ""));
            }
        });

        ((Button) findViewById(R.id.test_view_cancel_btn)).setOnClickListener(new View.OnClickListener() {
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
