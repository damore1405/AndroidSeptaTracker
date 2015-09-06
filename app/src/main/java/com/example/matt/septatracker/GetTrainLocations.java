package com.example.matt.septatracker;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;

/**
 * Created by Matt on 9/5/15.
 */
public class GetTrainLocations extends AsyncTask<GoogleMap, Void, GoogleMap> {
    // http://www3.septa.org/hackathon/TrainView/

    protected GoogleMap doInBackground(GoogleMap... mMaps) {
        if(mMaps.length > 1){
            Log.e("error", "invalid number of arguments!");
            return null;
        }

    try {
        URL septaApiUrl = new URL("http://www3.septa.org/hackathon/TrainView/");
        HttpURLConnection conn = (HttpURLConnection) septaApiUrl.openConnection();

        JsonParser jsonParser = new JsonParser();
        JsonArray result = (JsonArray) jsonParser.parse(new InputStreamReader(conn.getInputStream()));

        Log.d("Request Response:", result.toString());


    }catch (MalformedURLException e){
        Log.e("error", e.getMessage());
    }catch (IOException e){
        Log.e("error", e.getMessage());
    }
        return mMaps[0];
    }

    protected void onPostExecute(GoogleMap result) {
        result.addMarker(new MarkerOptions().position(new LatLng(40.01151, -75.19327)).title("Marker"));
    }

}