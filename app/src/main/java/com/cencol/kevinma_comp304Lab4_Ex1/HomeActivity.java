package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;


public class HomeActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences shared_prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String authRole = shared_prefs.getString("auth_role", null);

        //change view depending on logged in as nurse or doctor
        ImageButton imageButton = findViewById(R.id.profile_imagebtn);

        if (authRole.equals("Doctor")) {
            imageButton.setImageResource(R.drawable.doctor_profile);
        } else {
            imageButton.setImageResource(R.drawable.nurse_profile);
        }

        //profile button
        findViewById(R.id.profile_imagebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.menu_profile, Toast.LENGTH_LONG).show();
                //TODO: ADD link to class
            }
        });

        //patient info btn
        findViewById(R.id.patient_imagebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.menu_patient_management, Toast.LENGTH_LONG).show();
                startActivity(new Intent(view.getContext(), PatientListingActivity.class));
            }
        });

        //medical tests btn
        findViewById(R.id.test_imagebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.menu_test_management, Toast.LENGTH_LONG).show();
                startActivity(new Intent(view.getContext(), TestListingActivity.class));
            }
        });

        //logout button
        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.menu_logout, Toast.LENGTH_LONG).show();
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //disable the current active menu item to prevent end user confusion
        menu.getItem(0).setEnabled(false);
        return true;
    }
}
