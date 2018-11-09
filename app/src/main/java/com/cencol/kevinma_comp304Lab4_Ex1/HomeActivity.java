package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;


public class HomeActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        TextView textView = findViewById(R.id.test_textview);
//
//        SharedPreferences shared_prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
//
//        textView.setText(shared_prefs.getString("auth_username", null));

        //logout button
        findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.menu_logout, Toast.LENGTH_LONG).show();
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });
    }
}
