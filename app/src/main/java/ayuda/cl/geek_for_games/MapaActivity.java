package ayuda.cl.geek_for_games;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS ;
    private GoogleMap mMap;


    private FusedLocationProviderClient mFusedLocationClient;
    DatabaseReference mDatabase;
    private ArrayList<Marker> tmpUbicacionMarker = new ArrayList<>();
    private ArrayList<Marker>  ubicacionMarker = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        LatLongFirebase();


    }


    private void LatLongFirebase() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            return;
        }
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                Log.e("Latitud: ", location.getLatitude() + " Longitud: " + location.getLongitude());

                                Map<String, Object> latlng = new HashMap<>();
                                latlng.put("latitud", location.getLatitude());
                                latlng.put("longitud", location.getLongitude());
                                mDatabase.child("ubicacion_usuarios").push().setValue(latlng);
                            }
                        }
                    });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng chillan = new LatLng(-36.60787975863577, -72.10238905258322);
        //mMap.addMarker(new MarkerOptions().position(chillan).title("Chillán"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chillan,15));


        LatLng microplay = new LatLng(-36.60961692116919, -72.10067318817647);
        mMap.addMarker(new MarkerOptions().position(microplay).title("Microplay"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(microplay));


        LatLng ripley = new LatLng(-36.60877701231661, -72.10166896092169);
        mMap.addMarker(new MarkerOptions().position(ripley).title("Ripley"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ripley));

        LatLng njoystick = new LatLng(-36.608411755746545, -72.1028380581252);
        mMap.addMarker(new MarkerOptions().position(njoystick).title("Njoystick"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(njoystick));


        LatLng junix = new LatLng(-36.60901461842825, -72.10129310581279);
        mMap.addMarker(new MarkerOptions().position(junix).title("Junix Games"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(junix));


        LatLng next = new LatLng(-36.60501841217301, -72.10558464001394);
        mMap.addMarker(new MarkerOptions().position(next).title("Next Game Cyber"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(next));


        // ubicacion de usuarios al momento de usar el mapa
        mDatabase.child("ubicacion_usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(Marker marker:ubicacionMarker){
                    marker.remove();
                }

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Mapa_ubicacion mu = snapshot.getValue(Mapa_ubicacion.class);
                    Double latitud = mu.getLatitud();
                    Double longitud = mu.getLongitud();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud,longitud)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("Ubicación Actual");


                    tmpUbicacionMarker.add(mMap.addMarker(markerOptions));
                }

                ubicacionMarker.clear();
                ubicacionMarker.addAll(tmpUbicacionMarker);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}