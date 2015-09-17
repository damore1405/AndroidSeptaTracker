package com.example.matt.septatracker;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



/**
 * Created by Matt on 9/5/15.
 */
public class GetTrainLocations extends AsyncTask<Void, Void, JsonArray> {
    // http://www3.septa.org/hackathon/TrainView/

    private GoogleMap mMap;

    public GetTrainLocations(GoogleMap mMap){
        this.mMap = mMap;
    }

    protected JsonArray doInBackground(Void... params) {

    try {
        URL septaApiUrl = new URL("http://www3.septa.org/hackathon/TrainView/");
        HttpURLConnection conn = (HttpURLConnection) septaApiUrl.openConnection();

        JsonParser jsonParser = new JsonParser();
        JsonArray result = (JsonArray) jsonParser.parse(new InputStreamReader(conn.getInputStream()));

        Log.d("Request Response:", result.toString());
        return result;


    }catch (MalformedURLException e){
        Log.e("error", e.getMessage());
    }catch (IOException e){
        Log.e("error", e.getMessage());
    }
        return null;
    }

    protected void onPostExecute(JsonArray result) {
        for(JsonElement train : result){
            JsonObject trainObject = (JsonObject) train;
            float lat = trainObject.get("lat").getAsFloat();
            float lon = trainObject.get("lon").getAsFloat();
            String trainName = trainObject.get("trainno").getAsString();

            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title(trainName));

        }


    }

}