package pe.edu.tecsup.login;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity {

    private static final String TAG = MapsActivity.class.getSimpleName();

    private static final int PERMISSIONS_REQUEST = 100;
    private static final int REQUEST_CHECK_SETTINGS = 200;
    private static final int GPS_ENABLE_REQUEST = 300;

    private GoogleMap mMap;

    private ProgressDialog progressDialog;
    private LocationManager locationManager;

    private Double latitud;
    private Double longitud;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        progressDialog = new ProgressDialog(MapsActivity.this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setMessage("Cargando...");
//        progressDialog.show();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                try{


                    mMap = googleMap;

                    // setMyLocationEnabled
                    if(ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
                        return;
                    }
                    mMap.setMyLocationEnabled(true);

                    // setOnMapClickListener
                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            Log.d(TAG, "LatLng: " + latLng);
                            try{

                                mMap.clear();

                                mMap.addMarker(new MarkerOptions().position(latLng).title("Grupo familiar establecido"));

                                MapsActivity.this.latitud = latLng.latitude;
                                MapsActivity.this.longitud = latLng.longitude;

                                Log.d(TAG, "LatLng: " + MapsActivity.this.latitud + ", "+ MapsActivity.this.longitud);


                                // Get Address by GeoCode: https://developer.android.com/training/location/display-address.html?hl=es-419
                                Geocoder geocoder = new Geocoder(getApplicationContext());

                                List<Address> addresses = geocoder.getFromLocation(
                                        latLng.latitude,
                                        latLng.longitude,
                                        // In this sample, get just a single address.
                                        1);

                                if(addresses != null && addresses.size() > 0){
                                    Address address = addresses.get(0);
                                    for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                                        MapsActivity.this.address = address.getAddressLine(0);
                                        return; // Only first line
                                    }
                                }

                                Log.d(TAG, "Address: " + MapsActivity.this.address);

                            }catch (Throwable t){
                                Log.e(TAG, t.toString());
                                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                t.printStackTrace();
                            }
                        }
                    });

                    // setOnMapLongClickListener
                    mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                        @Override
                        public void onMapLongClick(LatLng latLng) {

                        }
                    });

                    // Custom UiSettings
                    UiSettings uiSettings = mMap.getUiSettings();
                    uiSettings.setZoomControlsEnabled(true);    // Controles de zoom
                    uiSettings.setCompassEnabled(true); // Brújula


                    // Add a marker in Sydney and move the camera
//                    LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
//                    float zoomlevel = 16;
//
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomlevel));
                    setCurrentPositionOnMap();


                }catch (Throwable t){
                    Log.e(TAG, t.toString());
                    Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            }
        });
    }

    private void setCurrentPositionOnMap(){


        LatLng current = new LatLng(-12.044105255412472, -76.95296287536621);
        float zoomlevel = 16;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomlevel));
//

//        // Check GPS enabled
//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getApplication()).addApi(LocationServices.API).build();
//        googleApiClient.connect();
//
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(10000 / 2);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//            @Override
//            public void onResult(LocationSettingsResult result) {
//                Log.d(TAG, "onResult: " + result);
//                try{
//
//                    final Status status = result.getStatus();
//
//                    switch (status.getStatusCode()) {
//                        case LocationSettingsStatusCodes.SUCCESS:
//                            Log.i(TAG, "All location settings are satisfied.");
//
//
//                            if(ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST);
//                                return;
//                            }
//
//                            // Get Current Location
//                            locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);
//                            if (locationManager != null) {
//
//                                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
//                                    builder.setTitle("GPS Deshabilitado");
//                                    builder.setMessage("Para verificar su ubicación se requiere activar el GPS.");
//                                    builder.setPositiveButton("Habilitar GPS", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_ENABLE_REQUEST);
//                                        }
//                                    });
//                                    AlertDialog dialog = builder.create();
//                                    dialog.show();
//
//                                }else{
//
//                                    locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
//                                        @Override
//                                        public void onLocationChanged(Location location) {
//                                            Log.d(TAG, "onLocationChanged: " + location);
//                                            try {
//
//                                                //double latitude = location.getLatitude();
//                                                //double longitude = location.getLongitude();
//                                                //Log.d(TAG, "lat:" + latitude + "|lon:" + longitude);
//
//                                                // Add a marker in Sydney and move the camera
//                                                LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
//                                                float zoomlevel = 16;
//
//                                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current, zoomlevel));
//
//                                            }catch (Throwable t){
//                                                Log.e(TAG, t.toString());
//                                                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                                                t.printStackTrace();
//                                            }finally {
//                                                progressDialog.dismiss();
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//                                        }
//
//                                        @Override
//                                        public void onProviderEnabled(String s) {
//
//                                        }
//
//                                        @Override
//                                        public void onProviderDisabled(String s) {
//
//                                        }
//                                    }, null);
//
//                                }
//
//                            }
//
//
//                            break;
//                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                            Log.d(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
//                            // Show the dialog by calling startResolutionForResult(), and check the result in onActivityResult().
//                            status.startResolutionForResult(MapsActivity.this, REQUEST_CHECK_SETTINGS);
//                            break;
//                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                            Log.e(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
//                            throw new Exception("Location settings are inadequate, and cannot be fixed here.");
//                    }
//
//                }catch (Throwable t){
//                    Log.e(TAG, t.toString());
//                    Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                    t.printStackTrace();
//                    progressDialog.dismiss();
//                }
//            }
//        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case PERMISSIONS_REQUEST: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Debe conceder todos los permisos", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Toast.makeText(this, "Permisos concedidos, intente de nuevo", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_CHECK_SETTINGS:
                if(resultCode == RESULT_OK){
                    Toast.makeText(this, "GPS habilitado", Toast.LENGTH_LONG).show();
                    setCurrentPositionOnMap();
                }else{
                    Toast.makeText(this, "Debe habilitar el GPS", Toast.LENGTH_LONG).show();
                }
                break;
            case GPS_ENABLE_REQUEST:
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    Toast.makeText(this, "GPS habilitado", Toast.LENGTH_LONG).show();
                    setCurrentPositionOnMap();
                }else{
                    Toast.makeText(this, "Debe habilitar el GPS", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void Save(View view){

        if(this.latitud == null || this.longitud == null){
            Toast.makeText(getApplicationContext(),"Debe seleccionar una ubicación", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("latitud", String.valueOf(latitud));
        intent.putExtra("longitud", String.valueOf(longitud));
        intent.putExtra("address", address);

        setResult(RESULT_OK, intent);   // Set ResultCode and DataIntent

        finish();

    }


//    private GoogleMap mMap;
//    private static final String TAG = MapsActivity.class.getSimpleName();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//
//        ApiService service = ApiServiceGenerator.createService(ApiService.class);
//
//        Call<List<Inmueble>> call = service.getInmuebles();
//
//        call.enqueue(new Callback<List<Inmueble>>() {
//            @Override
//            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
//                try {
//
//                    int statusCode = response.code();
//
//
//                    if (response.isSuccessful()) {
//
//                        List<Inmueble> coordenadas = response.body();
//                        Log.d(TAG, "coordenadas " + coordenadas);
//
//
//
//                        for(Inmueble nada : coordenadas){
//                            String coord = nada.getCoordenadas();
//                            String [] arraycoord = coord.split(" ");
//                            double x = Double.parseDouble(arraycoord[0]);
//                            double y = Double.parseDouble(arraycoord[1]);
//                            double lat = Double.valueOf(x).doubleValue();
//                            double lng = Double.valueOf(y).doubleValue();
//
//                            LatLng sydney = new LatLng(lat, lng);
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,11));
///*
//                            String coord = objectToString(nada);
//
//*/
//                        }
//
//
//                    } else {
//
//                        throw new Exception("Error en el servicio");
//                    }
//
//                } catch (Throwable t) {
//                    try {
//                        Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                    } catch (Throwable x) {
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
//
//                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//        });
//
//        // Add a marker in Sydney and move the camera
//
//    }
//
//


    }

