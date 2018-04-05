package com.example.daniyar.kicb;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;


public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    public static ArrayList<Atm> atmArrayList;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://mysterious-citadel-58638.herokuapp.com/atms.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            atmArrayList = new ArrayList<>();
            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                Atm atm = new Atm(JO.get("bank").toString(),
                        JO.get("lat").toString(),
                        JO.get("lon").toString(),
                        JO.get("hour_start").toString(),
                        JO.get("hour_end").toString(),
                        JO.get("desc").toString(),
                        new URL(JO.get("url").toString()));

                atmArrayList.add(atm);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

       // just to test if fetching works
        for(int i=0; i<atmArrayList.size(); i++){
            Atm atm = atmArrayList.get(i);
            Log.v("Bank ", atm.bank);
            LatLng latLng = new LatLng(Double.valueOf(atm.getLat()), Double.valueOf(atm.getLon()));
            String snippet = "Working hours: "
                    + atm.getHour_start()
                    + "-"
                    + atm.getHour_end()
                    + "\n"
                    + "Description: " + atm.getDesc();

            Marker marker = MapsActivity.mMap.addMarker(new MarkerOptions().position(latLng));
            marker.setTitle(atm.getBank());
            marker.setSnippet(snippet);
            switch (atm.getBank()){
                case "KICB":
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    break;
                case "Aiyl Bank":
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(Float.valueOf("0.345")));
                    break;
                default:
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker());
            }
        }
    }
}