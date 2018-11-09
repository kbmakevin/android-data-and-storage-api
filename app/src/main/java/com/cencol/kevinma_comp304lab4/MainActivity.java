package com.cencol.kevinma_comp304lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cencol.kevinma_comp304Lab4_Ex1.DatabaseManager;
import com.cencol.kevinma_comp304Lab4_Ex1.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private String[] TABLE_NAMES;
    //sql strings to create the tables
    private String[] TABLE_CREATOR_STRINGS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView exerciseListView = findViewById(R.id.exerciseListView);
        //    Used for allowing the user to navigate to the other exercises in this application
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // starts the exercise clicked on by user
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "Exercise 1":
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.main_menu_clicked_msg) + getResources().getString(R.string.ex1), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(view.getContext(), LoginActivity.class));
                        break;
                }
            }
        });

        //instantiate the DatabaseManager
        //instantiate the tables

        TABLE_NAMES = getResources().getStringArray(R.array.db_table_names);
        //sql strings to create the tables
        TABLE_CREATOR_STRINGS = getResources().getStringArray(R.array.db_table_create_strings);
        try {

            databaseManager = new DatabaseManager(this);
            //create the tables
            databaseManager.dbInitialize(TABLE_NAMES, TABLE_CREATOR_STRINGS);

            //add default rows for nurse/doctor, create accounts here for login authentication

            //default Nurse
            int nurseId = 1;
            String firstName = "Sue";
            String lastName = "Lee";
            String department = "Cardiology";
            String password = "p";
            //initialize ContentValues object with the new nurse
            ContentValues contentValues = new ContentValues();
            contentValues.put("nurseId", nurseId);
            contentValues.put("firstName", firstName);
            contentValues.put("lastName", lastName);
            contentValues.put("department", department);
            contentValues.put("password", password);
            try {
                databaseManager.addRow("Nurse", contentValues);
            } catch (Exception exception) {
                //
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Error: ", exception.getMessage());
            }
            //default Doctor
            int doctorId = 2;
            firstName = "George";
            lastName = "Brownie";
            department = "Gynaecology";
            password = "d";
            //initialize ContentValues object with the new nurse
            contentValues = new ContentValues();
            contentValues.put("doctorId", doctorId);
            contentValues.put("firstName", firstName);
            contentValues.put("lastName", lastName);
            contentValues.put("department", department);
            contentValues.put("password", password);
            try {
                databaseManager.addRow("Doctor", contentValues);
            } catch (Exception exception) {
                //
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Error: ", exception.getMessage());
            }

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", ex.getMessage());
        }
    }
}
