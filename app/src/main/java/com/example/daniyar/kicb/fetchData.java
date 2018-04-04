package com.example.daniyar.kicb;

import android.os.AsyncTask;
import android.util.Log;

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
import java.util.ArrayList;


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
                Atm atm = new Atm(JO.get("bank").toString(), JO.get("lat").toString(), JO.get("lon").toString(), JO.get("desc").toString());

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
            Log.v("Bank ", atmArrayList.get(i).bank);
        }
    }
}