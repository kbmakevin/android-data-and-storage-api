package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class PatientViewActivity extends BaseNavigationActivity {

    private DatabaseManager databaseManager;
    private int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        //both add/edit get patient id passed in from previous activity
        Intent intent = getIntent();
        patientId = Integer.parseInt(intent.getStringExtra("selectedPatientId"));

        //get and fill in patientid from previous activity
        TextView editText = findViewById(R.id.patient_view_id_val);
        editText.setText(patientId + "");

        //get selected patient details and populate web form fields
        databaseManager = new DatabaseManager(this);

        //don't need check, if got to this page patient has to exist
        //populate fields for display
        Patient selPatient = databaseManager.getPatientById(patientId);
        ((TextView) findViewById(R.id.patient_view_fname_val)).setText(selPatient.getFirstName());
        ((TextView) findViewById(R.id.patient_view_lname_val)).setText(selPatient.getLastName());
        ((TextView) findViewById(R.id.patient_view_dept_val)).setText(selPatient.getDepartment());

        Doctor doctor = databaseManager.getDoctorById(selPatient.getDoctorId());
        ((TextView) findViewById(R.id.patient_view_doc_val)).setText(doctor.toString());

        ((TextView) findViewById(R.id.patient_view_room_val)).setText(selPatient.getRoom());

        //add event handlers
        ((Button) findViewById(R.id.patient_view_update_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PatientEditActivity.class).putExtra("patientId", patientId + ""));
            }
        });

        ((Button) findViewById(R.id.patient_view_cancel_btn)).setOnClickListener(new View.OnClickListener() {
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
