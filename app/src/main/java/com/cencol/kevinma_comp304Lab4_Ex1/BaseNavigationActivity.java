package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

/**
 * This Activity will be used as a base/template for other classes and contains common reusable elements
 */
public class BaseNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, R.string.menu_profile, Toast.LENGTH_LONG).show();
//                startActivity(new Intent(this, VegFoodItemsActivity.class));
                break;
            case R.id.patient_management:
                Toast.makeText(this, R.string.menu_patient_management, Toast.LENGTH_LONG).show();
//                startActivity(new Intent(this, FruitFoodItemsActivity.class));
                break;
            case R.id.test_management:
                Toast.makeText(this, R.string.menu_test_management, Toast.LENGTH_LONG).show();
//                startActivity(new Intent(this, GrainFoodItemsActivity.class));
                break;
            case R.id.logout:
                Toast.makeText(this, R.string.menu_logout, Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }
}
