package com.example.matt.septatracker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.app.*;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.example.matt.septatracker.util.SystemUiHider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class SplashScreen extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        final ArrayList<String> stationData = collectStations();


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);

                // Send over the hashtable of station data to the main screen to be put into the scrollers
                mainIntent.putStringArrayListExtra("stationData", stationData);

                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    //Accesses the stations.csv asset as the app is opening to fill in the scrollers in the main activity
    private ArrayList<String> collectStations(){

        AssetManager assets;
        InputStream input;
        BufferedReader reader;
        ArrayList<String> stations = new ArrayList<>();
        /* Try to read from the asset csv to fill a hashtable with the static station data */
        try {
            assets = getAssets();
            input = assets.open("stations.csv");
            reader = new BufferedReader(new InputStreamReader(input));
            String line;

            while ((line=reader.readLine()) != null){
                String[] split = line.split(",");
                stations.add(split[1]);
                Log.d("Reading csv...", split[1]);
            }

        }catch (IOException e){
            Log.e("IO exception", "couldn't open stations.csv");
            System.exit(1);
        }

        return stations;
    }
}