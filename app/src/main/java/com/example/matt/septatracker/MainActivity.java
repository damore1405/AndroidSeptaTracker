package com.example.matt.septatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        Spinner sourceSpinner = (Spinner) findViewById(R.id.sourceSpinner);
        Spinner destSpinner   = (Spinner) findViewById(R.id.destSpinner);
        Button mapButton      = (Button) findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(MainActivity.this, TrainMapView.class);
                MainActivity.this.startActivity(mapIntent);
            }
        });

        ArrayList<String> stationNames = null;

        //If the activity has been given an intent with station data in it, then fill up the spinners
        if(intent.hasExtra("stationData"))
            stationNames = intent.getStringArrayListExtra("stationData");

        //Create the array adapter to send the station names onto the spinners for selection
        ArrayAdapter<String> adapter = new ArrayAdapter(this , android.R.layout.simple_spinner_item , stationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceSpinner.setAdapter(adapter);
        destSpinner.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
