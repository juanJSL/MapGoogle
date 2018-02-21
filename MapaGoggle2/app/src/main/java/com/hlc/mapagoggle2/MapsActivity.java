package com.hlc.mapagoggle2;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CameraUpdate camUpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // centra España en vista satélite
        camUpd = CameraUpdateFactory.newLatLng(new LatLng(40.41, -3.69));
        mMap.moveCamera(camUpd);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        // centra España con zoom x5 en vista normal animada
        camUpd = CameraUpdateFactory.newLatLngZoom(new LatLng(40.41, -3.69), 5f);
        mMap.animateCamera(camUpd);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        /*
        // Add a marker in Sydney and move the camera
        LatLng initLocation = new LatLng(36.840835, -2.471172);
        mMap.addMarker(new MarkerOptions().position(initLocation).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(initLocation));
        */
    }
}
