package app.bus;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Maps extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Marker stops;
    private Marker bus;
    public static boolean updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MainActivity.context = this;

        // Below is Jason's code before the next comments
        this.onResume();

        Spinner mapComp = (Spinner)findViewById(R.id.mapCompanySel);
        ArrayAdapter<String> adaptMapCom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.routeCom);
        mapComp.setAdapter(adaptMapCom);

        Spinner mapRo = (Spinner)findViewById(R.id.mapRouteSel);
        ArrayAdapter<String> adaptMapRo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.routeNum);
        mapRo.setAdapter(adaptMapRo);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupLocationServices();
        String query = "UOIT";
        Address address = geocodeLookup(query);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if(this.updated) {
            this.updated = false;

            EditText addr = (EditText)findViewById(R.id.mapAddEnter);
            addr.setText(MainActivity.destination);
        }
    }


    private void setupLocationServices() {
        requestLocationPermissions();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // request that the user install the GPS provider
            String locationConfig = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGPS = new Intent(locationConfig);
            startActivity(enableGPS);
        } else {
            // determine the location
            updateLocation();
        }
    }

    private void updateLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

            // request an fine location provider
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setSpeedRequired(false);
            criteria.setCostAllowed(false);
            String recommended = locationManager.getBestProvider(criteria, true);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

            Location location = locationManager.getLastKnownLocation(recommended);
            if (location != null) {
                showLocationName(location);
            }
        } else {
            //Log.d("LocationSample", "Location provider permission denied, perms: " + ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION));
        }
    }

    final int PERMISSIONS_REQUEST_ACCESS_LOCATION = 410020;
    private void requestLocationPermissions() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_LOCATION);

            return;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLocation();
                } else {
                    // tell the user that the feature will not work
                }
                return;
            }
        }
    }

    public void onProviderEnabled(String provider) {
        Log.d("LocationSample", "onProviderEnabled(" + provider + ")");
    }

    public void onProviderDisabled(String provider) {
        Log.d("LocationSample", "onProviderDisabled(" + provider + ")");
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("LocationSample", "onStatusChanged(" + provider + ", " + status + ", extras)");
    }

    public void onLocationChanged(Location location) {
        Log.d("LocationSample", "onLocationChanged(" + location + ")");

        showLocationName(location);
    }

    private void showLocationName(Location location) {
        Log.d("LocationSample", "showLocationName("+location+")");
        // perform a reverse geocode to get the address
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                // reverse geocode from current GPS position
                List<Address> results = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                if (results.size() > 0) {
                    Address match = results.get(0);
                    String address = match.getAddressLine(0);
                    setLocation(address);
                } else {
                    Log.d("LocationSample", "No results found while reverse geocoding GPS location");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("LocationSample", "No geocoder present");
        }
    }
    private Address geocodeLookup(String address) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                // forward geocode from the provided address
                List<Address> results = geocoder.getFromLocationName(address, 1);

                if (results.size() > 0) {
                    return results.get(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void setLocation(String location) {
        Log.d("LocationSample", "setLocation("+location+")");

        //EditText locationField = (EditText)findViewById(R.id.txtLocation);
        //locationField.setText(location);
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

        String query = "UOIT";
        Address address = geocodeLookup(query);
        LatLng uoit = new LatLng(address.getLatitude(), address.getLongitude());

        stops = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.9433431, -78.895061))
                .title("Stop# 314159")
                .snippet("Founders Dr & Commencement Cir\nFacilities: Heating, Shelter")
        );

        bus = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.94454584, -78.89120489))
                .title("Bus# 900145")
                .snippet("Here at: 9:10 AM\nDestination at: 9:25 AM\nSeats: 5/65")
        );

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(MainActivity.context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(MainActivity.context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(MainActivity.context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(uoit));

        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}
