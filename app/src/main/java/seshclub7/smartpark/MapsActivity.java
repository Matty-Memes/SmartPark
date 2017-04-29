package seshclub7.smartpark;

import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.UiSettings;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    UiSettings mUiSettings;
    double allLongitude[] = {};
    double streetLongitude[] = {54.604224,54.604312,54.604337,54.604452};
    double streetLatitude[] = {-5.931062,-5.931134,-5.931166,-5.931220};


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
        //MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this,)
        mMap.setMinZoomPreference(16.5f);
        mMap.setMaxZoomPreference(20.0f);
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setZoomGesturesEnabled(false);
        mUiSettings.setMapToolbarEnabled(true);

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivityRaw", "Can't find style.", e);
        }


        LatLng Belfast = new LatLng(54.604339, -5.931164);
        mMap.addMarker(new MarkerOptions().position(Belfast).title("Donegal Street").icon((BitmapDescriptorFactory.fromResource(R.drawable.marker))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Belfast, 16.5f));

    }

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address>addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(54.597272, -5.931648);
            LatLng latLng1 = new LatLng(54.597250, -5.931128);
            LatLng latLng2 = new LatLng(554.597228, -5.932319);
            LatLng latLng3 = new LatLng(54.597244, -5.930828);
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
            mMap.addMarker(new MarkerOptions().position(latLng1).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
            mMap.addMarker(new MarkerOptions().position(latLng2).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
            mMap.addMarker(new MarkerOptions().position(latLng3).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        }
    }




}


