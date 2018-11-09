package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get username from login form field "username"
                String username = ((TextView) findViewById(R.id.username_edittext)).getText().toString();

                //store successfully authenticated username into application's shared preferences
                SharedPreferences shared_prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared_prefs.edit();
                editor.putString("auth_username", username);
                editor.commit();

                //clear login form, in case of auth failure
                ((TextView) findViewById(R.id.username_edittext)).setText(null);
                ((TextView) findViewById(R.id.password_edittext)).setText(null);

                //auth success/failure feedback for user
                Toast.makeText(LoginActivity.this, "Logging in with username: " + shared_prefs.getString("auth_username", null), Toast.LENGTH_SHORT).show();

                //upon successful authentication, open new activity
                //display diff activities in menu depending on whether logged in as a nurse or a doctor
                startActivity(new Intent(view.getContext(), HomeActivity.class));
            }
        });
    }
}
