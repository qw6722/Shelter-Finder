package com.example.saimada.shelterfinder;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    private static boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras.getString("type").equals("blank map")) {
            clear = true;
        } else {
            clear = false;
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        addSheltersToMap((ShelterRecyclerAdapter) LoginPage.adapter);
        // Add a marker in Sydney and move the camera
        LatLng atlanta = new LatLng(33.7490, -84.3880);
        //mMap.addMarker(new MarkerOptions().position(atlanta).title("Marker in Atlanta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, 13.0f));
    }

    /**
     * This method adds all the Shelters in a list to the map itself.
     *
     * @param adapter is the list of spots to be added to the map
     */
    public static void addSheltersToMap(ShelterRecyclerAdapter adapter) {
        if (!clear) {
            int size = adapter.getItemCount();
            for (int i = 0; i < size; i++) {
                Log.e("lat", adapter.getItem(i).getLatitude());
                Log.e("long", adapter.getItem(i).getLongitude());
                String lat = adapter.getItem(i).getLatitude() + "";
                String longi = adapter.getItem(i).getLongitude() + "";
                double intLat = Double.parseDouble(lat);
                double intLong = Double.parseDouble(longi);
                LatLng location = new LatLng(intLat, intLong);
                mMap.addMarker(new MarkerOptions().position(location)
                        .title(adapter.getItem(i).getShelterName()
                                + " " + adapter.getItem(i).getCapacity()));
            }
        } else {
            Log.e("type of map", "Blank map");
        }
    }
}
