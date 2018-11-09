package com.cencol.kevinma_comp304Lab4_Ex1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cencol.kevinma_comp304lab4.R;

public class TestListingActivity extends BaseNavigationActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listing);

        databaseManager = new DatabaseManager(this);

//populate listview with any existing patients
        Test[] existingTests = databaseManager.getAllTests();
        Toast.makeText(this, "# of tests: " + existingTests.length, Toast.LENGTH_SHORT).show();
        if (existingTests.length > 0) {
            ListView listView = findViewById(R.id.test_listing_listview);
            listView.setAdapter(new ArrayAdapter<Test>(this, android.R.layout.simple_list_item_1, existingTests));
        }

        //attach event handlers
        final ListView testListingListView = findViewById(R.id.test_listing_listview);
        testListingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Test testSelected = (Test) testListingListView.getItemAtPosition(position);
                int selectedTestId = testSelected.getTestId();
                startActivity(new Intent(view.getContext(), TestViewActivity.class).putExtra("selectedTestId", selectedTestId + ""));
            }
        });

        findViewById(R.id.test_listing_viewbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get as string first to check str length before converting to int
                String selectedTestId = ((TextView) findViewById(R.id.test_id_edittext)).getText().toString();
                //test id field must be filled in
                if (selectedTestId.length() > 0) {

                    //take id from test field as new test id
                    //test id must already exist in db - need to check
                    //select test by id and see if returned result
                    Test test = databaseManager.getTestById(Integer.parseInt(selectedTestId));
                    //did not find matching test with the given id
                    if (test != null) {
                        startActivity(new Intent(view.getContext(), TestViewActivity.class).putExtra("selectedTestId", selectedTestId));

                    } else {
                        Toast.makeText(view.getContext(), "Test with the ID: " + selectedTestId + " does not exist\nPlease enter a different ID if you want to view a test's details.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(view.getContext(), "Please fill in a test Id!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.test_listing_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get as string first to check str length before converting to int
                String newTestId = ((TextView) findViewById(R.id.test_id_edittext)).getText().toString();
                //test id field must be filled in
                if (newTestId.length() > 0) {

                    //take id from test field as new test id
                    //test id must not already exist in db - need to check
                    //new test by id and see if returned result
                    Test test = databaseManager.getTestById(Integer.parseInt(newTestId));
                    //did not find matching test with the given id
                    if (test == null) {
                        startActivity(new Intent(view.getContext(), TestAddActivity.class).putExtra("testId", newTestId));

                    } else {
                        Toast.makeText(view.getContext(), "Test with the ID: " + newTestId + " already exists!\nPlease enter a different ID if you want to add a new test.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(view.getContext(), "Please fill in a test Id!", Toast.LENGTH_SHORT).show();

                }
            }
        });

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
