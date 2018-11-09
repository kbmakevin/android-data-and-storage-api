package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;


public class LoginActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseManager = new DatabaseManager(this);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get username from login form field "username"
                String username = ((TextView) findViewById(R.id.username_edittext)).getText().toString();
                String password = ((TextView) findViewById(R.id.password_edittext)).getText().toString();

                //get app shared prefs
                SharedPreferences shared_prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared_prefs.edit();

                //both username and password fields must be filled out
                if (username.length() > 0 && password.length() > 0) {

                    //auth success/failure feedback for user
                    try {
                        Nurse nurse = databaseManager.getNurseById(Integer.parseInt(((TextView) findViewById(R.id.username_edittext)).getText().toString()));

                        //found a nurse with matching id
                        if (nurse != null) {

                            if (password.equals(nurse.getPassword())) {

                                //store successfully authenticated username into application's shared preferences
                                editor.putString("auth_username", username);
                                editor.putString("auth_role", "Nurse");
                                editor.commit();

                                Toast.makeText(LoginActivity.this, "Logging in as Nurse: " + nurse.getFirstName() + " " + nurse.getLastName(), Toast.LENGTH_SHORT).show();
                                //upon successful authentication, open new activity
                                //display diff activities in menu depending on whether logged in as a nurse or a doctor
                                startActivity(new Intent(view.getContext(), HomeActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //if not a nurse, maybe a doctor
                            Doctor doctor = databaseManager.getDoctorById(Integer.parseInt(((TextView) findViewById(R.id.username_edittext)).getText().toString()));
//found a nurse with matching id
                            if (doctor != null) {

                                if (password.equals(doctor.getPassword())) {

                                    //store successfully authenticated username into application's shared preferences
                                    editor.putString("auth_username", username);
                                    editor.putString("auth_role", "Doctor");
                                    editor.commit();

                                    Toast.makeText(LoginActivity.this, "Logging in as Doctor: " + doctor.getFirstName() + " " + doctor.getLastName(), Toast.LENGTH_SHORT).show();
                                    //upon successful authentication, open new activity
                                    //display diff activities in menu depending on whether logged in as a nurse or a doctor
                                    startActivity(new Intent(view.getContext(), HomeActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //if not a doctor/nurse found, invalid credentials
                            else {
                                Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } catch (Exception e) {
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("Error: ", e.getMessage());
                    }

                    //clear login form
                    ((TextView) findViewById(R.id.username_edittext)).setText(null);
                    ((TextView) findViewById(R.id.password_edittext)).setText(null);


                } else {
                    Toast.makeText(LoginActivity.this, "Please fill out both username and password fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
