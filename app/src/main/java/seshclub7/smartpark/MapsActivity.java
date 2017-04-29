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


        // Add a marker in Sydney and move the camera
        LatLng Belfast = new LatLng(54.604339, -5.931164);
        try {
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(Belfast).title(getMyLocationAddress()));
        }
        catch (NullPointerException e)
        {
            System.out.print("Could not load in image");
        }
        //mMap.addMarker(new MarkerOptions().position(Belfast).title(getMyLocationAddress()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Belfast, 16.5f));

    }


    public String getMyLocationAddress() {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String address = "";

        try {

            //Place your latitude and longitude
            List<Address> addresses = geocoder.getFromLocation(54.595976, -5.930908, 1);

            if (addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();


                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }

                address = strAddress.toString();

            } else

                return ("No location found..!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not get address..!", Toast.LENGTH_LONG).show();
        }
        return address;

    }
    private void loadStreetMarkers()
    {
        try
        {
            for (int i = 0; i < 10; i++)
            {
                LatLng street = new LatLng(streetLongitude[i],streetLatitude[i]);
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).position(street).title(getMyLocationAddress()));
            }
        }
        catch (NullPointerException e)
        {
            System.out.print("Could not load in image");
        }
    }

}


