package com.hlc.mapagoggle2;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CameraUpdate camUpd;
    private CameraPosition camPos;
    private int vistaActual=0;

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



        // Add a marker in Sydney and move the camera
        LatLng initLocation = new LatLng(36.840835, -2.471172);
        mMap.addMarker(new MarkerOptions().position(initLocation).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(initLocation));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.menu_alcazaba){
            // centra la Alcazaba de Almería en vista híbrida
            LatLng alcazaba = new LatLng(36.840835, -2.471172);
            camPos = new CameraPosition.Builder().target(alcazaba).zoom(19).bearing(45).tilt(80).build();
// con zoom x19         .zoom(19)
// orientación noreste (o giro de 45 grados sexagesimales
// este -ver la brújula arriba a la izquierda).bearing(45)
// punto de vista bajo para ver más suelo (70 grados
// sexagesimales con respecto a la vertical) .tilt(80).build();
            camUpd = CameraUpdateFactory.newCameraPosition(camPos);
            mMap.animateCamera(camUpd);
        }else if(item.getItemId()==R.id.menu_posicion){

        }else if(item.getItemId()==R.id.menu_spain){
            // centra España
            camUpd = CameraUpdateFactory.newLatLng(new LatLng(40.41, -3.69));
            mMap.moveCamera(camUpd);
            //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }else if(item.getItemId()==R.id.menu_spain_zoom){
            // centra España con zoom x5
            camUpd = CameraUpdateFactory.newLatLngZoom(new LatLng(40.41, -3.69), 5f);
            mMap.animateCamera(camUpd);
            //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }else if(item.getItemId()==R.id.menu_vista){
            /*
            MAP_TYPE_NONE : vista en blanco del mapa.
            MAP_TYPE_SATELLITE : vista por satélite.
            MAP_TYPE_NORMAL : vista por defecto, mapa básico con carreteras.
            MAP_TYPE_HYBRID : vista por satélite y normal.
            MAP_TYPE_TERRAIN : vista de campo sin carreteras.
            */
            vistaActual++;
            if(vistaActual>3)
                vistaActual=0;
            int [] vistas = {mMap.MAP_TYPE_SATELLITE, mMap.MAP_TYPE_NORMAL, mMap.MAP_TYPE_HYBRID, mMap.MAP_TYPE_TERRAIN};
            mMap.setMapType(vistas[vistaActual]);
        }

        return super.onOptionsItemSelected(item);
    }
}
