package pe.edu.tecsup.login;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsAllActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
    private static final String TAG = MapsAllActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_all);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Inmueble>> call = service.getInmuebles();

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                try {

                    int statusCode = response.code();


                    if (response.isSuccessful()) {

                        List<Inmueble> coordenadas = response.body();
                        Log.d(TAG, "coordenadas " + coordenadas);



                        for(Inmueble nada : coordenadas){
                            String coord = nada.getCoordenadas();
                            String [] arraycoord = coord.split(" ");
                            double x = Double.parseDouble(arraycoord[0]);
                            double y = Double.parseDouble(arraycoord[1]);
                            double lat = Double.valueOf(x).doubleValue();
                            double lng = Double.valueOf(y).doubleValue();

                            double latitud = Double.parseDouble(nada.getLatitud());
                            double longitud = Double.parseDouble(nada.getLongitud());

                            LatLng sydney = new LatLng(lat, lng);
                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,11));
/*
                            String coord = objectToString(nada);

*/
                        }


                    } else {

                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Toast.makeText(MapsAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {

                Toast.makeText(MapsAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

        // Add a marker in Sydney and move the camera

    }


}
