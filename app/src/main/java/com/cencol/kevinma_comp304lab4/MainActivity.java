package com.cencol.kevinma_comp304lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cencol.kevinma_comp304Lab4_Ex1.LoginActivity;

public class MainActivity extends AppCompatActivity {

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
                        startActivity(new Intent(view.getContext(),LoginActivity.class));
                        break;
                }
            }
        });
    }
}
