package pe.edu.tecsup.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInmuebleActivity extends AppCompatActivity {


    private static final String TAG = DetailInmuebleActivity.class.getSimpleName();

    private Integer id;

    private ImageView fotoImage;
    private TextView nombreText;
    private TextView detallesText;
    private TextView precioText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inmueble);

        fotoImage = (ImageView)findViewById(R.id.foto_image);
        nombreText = (TextView)findViewById(R.id.nombre_text);
        detallesText = (TextView)findViewById(R.id.detalles_text);
        precioText = (TextView)findViewById(R.id.precio_text);

        id = getIntent().getExtras().getInt("ID");
        Log.e(TAG, "id:" + id);

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Inmueble> call = service.showInmueble(id);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Inmueble inmueble = response.body();
                        Log.d(TAG, "producto: " + inmueble);

                        String url = ApiService.API_BASE_URL + "/images/" + inmueble.getImagen();
                        Picasso.with(DetailInmuebleActivity.this).load(url).into(fotoImage);

                        nombreText.setText("DIRECCION: "+inmueble.getDireccion());
                        detallesText.setText("DESCRIPCION: "+inmueble.getDescripcion());
                        precioText.setText("TIPO DE COSTO: "+inmueble.getTipo_costo());
                        inmueble.getUsuarios_idusuarios();
                        
                        Toast.makeText(DetailInmuebleActivity.this, "", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailInmuebleActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailInmuebleActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
