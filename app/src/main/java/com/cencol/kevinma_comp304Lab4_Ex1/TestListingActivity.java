package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.cencol.kevinma_comp304lab4.R;

public class TestListingActivity extends BaseNavigationActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listing);

        SharedPreferences shared_prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String authRole = shared_prefs.getString("auth_role", null);

        //doctors can only view tests, no update or add
        if (authRole.equals("Doctor")) {
            findViewById(R.id.test_listing_addBtn).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.test_listing_addBtn).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //disable the current active menu item to prevent end user confusion
        menu.getItem(3).setEnabled(false);
        return true;
    }
}
