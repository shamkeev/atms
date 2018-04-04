package com.example.daniyar.kicb;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Atm> atmArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fetchData process = new fetchData();
        process.execute();

        atmArrayList = new ArrayList<>();

        //test data:
        Atm atm1 = new Atm("KICB", "42.861", "74.581", "ATM Of KICB");
        Atm atm2 = new Atm("KICB", "42.858", "74.59", "ATM Of KICB");
        Atm atm3 = new Atm("Aiyl Bank", "42.868", "74.57", "ATM Of Aiyl Bank");
        atmArrayList.add(atm1);
        atmArrayList.add(atm2);
        atmArrayList.add(atm3);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bishkek = new LatLng(42.866, 74.587);
        //mMap.addMarker(new MarkerOptions().position(bishkek).title("Marker in Bishkek").snippet("A very good bank \n Open 24/7"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bishkek, 12));

        for (int i=0; i < atmArrayList.size(); i++){
            LatLng latLng = new LatLng(Double.valueOf(atmArrayList.get(i).getLat()), Double.valueOf(atmArrayList.get(i).getLon()));
            mMap.addMarker(new MarkerOptions().position(latLng).title(atmArrayList.get(i).getBank()).snippet(atmArrayList.get(i).getDesc()));
        }

    }
}
