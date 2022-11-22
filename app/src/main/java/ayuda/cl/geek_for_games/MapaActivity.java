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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import ayuda.cl.geek_for_games.databinding.ActivityMapaBinding;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapaBinding LatLng;

    private FusedLocationProviderClient mFusedLocationClient;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.e("Latitud: ", + location.getLatitude() + "Longitud: " + location.getLongitude());

                            Map<String,Object> latlong = new HashMap<>();
                            latlong.put("latitud", location.getLatitude());
                            latlong.put("longitud", location.getLongitude());
                            mDatabase.child("ubicacion_usuarios").push().setValue(latlong);

                        }
                    }
                });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng chillan = new LatLng(-36.60787975863577, -72.10238905258322);
        mMap.addMarker(new MarkerOptions().position(chillan).title("Chillán"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chillan));

        LatLng microplay = new LatLng(-36.60961692116919, -72.10067318817647);
        mMap.addMarker(new MarkerOptions().position(chillan).title("Microplay"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(microplay));


        LatLng ripley = new LatLng(-36.60877701231661, -72.10166896092169);
        mMap.addMarker(new MarkerOptions().position(chillan).title("Ripley"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ripley));

        LatLng njoystick = new LatLng(-36.608411755746545, -72.1028380581252);
        mMap.addMarker(new MarkerOptions().position(chillan).title("Njoystick"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(njoystick));


        LatLng junix = new LatLng(-36.60901461842825, -72.10129310581279);
        mMap.addMarker(new MarkerOptions().position(chillan).title("Junix Games"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(junix));


        LatLng next = new LatLng(-36.60501841217301, -72.10558464001394);
        mMap.addMarker(new MarkerOptions().position(chillan).title("Next Game Cyber"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(next));




    }
}