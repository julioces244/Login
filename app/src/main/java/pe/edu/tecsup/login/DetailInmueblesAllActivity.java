package pe.edu.tecsup.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInmueblesAllActivity extends AppCompatActivity {


    private static final String TAG = DetailInmueblesAllActivity.class.getSimpleName();

    private Integer id;
    private String stringusuarioid;
    private Integer integerusuarioid;
    //public static String hardcorreo;

    private Button correo,llamada,envalor;

    private ImageView fotoImage;
    private TextView nombreText;
    private TextView detallesText;
    private TextView precioText;
    private TextView publicadoText;
    private TextView telefonoText;
    private TextView correoText;
    private TextView tipocosto;
    private TextView tipoinmueble;


    RatingBar ratingBar;
    String valoracion;
    EditText edtextcomentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inmuebles_all);

        fotoImage = (ImageView)findViewById(R.id.foto_image2);
        nombreText = (TextView)findViewById(R.id.nombre_text2);
        detallesText = (TextView)findViewById(R.id.detalles_text2);
        precioText = (TextView)findViewById(R.id.precio_text2);
        tipocosto = findViewById(R.id.tipo_costo2);
        tipoinmueble = findViewById(R.id.tip_inmueble2);

        publicadoText = (TextView)findViewById(R.id.publicado);
        telefonoText = (TextView)findViewById(R.id.telefono);
        correoText = (TextView)findViewById(R.id.correo);

        correo = findViewById(R.id.sendemailuser);
        llamada = findViewById(R.id.calluser);

        ratingBar = findViewById(R.id.rbvalorar);
        envalor = findViewById(R.id.enviarvaloracion);

        id = getIntent().getExtras().getInt("ID");
        Log.e(TAG, "id:" + id);

        initialize();

        //initialize2();

        //correoText.setText(hardcorreo);


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
                        Picasso.with(DetailInmueblesAllActivity.this).load(url).into(fotoImage);

                        nombreText.setText("DIRECCION: "+inmueble.getDireccion());
                        detallesText.setText("DESCRIPCION: "+inmueble.getDescripcion());
                        precioText.setText("COSTO: "+inmueble.getCosto());
                        tipocosto.setText("TIPO DE COSTO: "+inmueble.getTipo_costo());
                        tipoinmueble.setText("TIPO DE INMUEBLE: "+inmueble.getTipo_inmueble());


                        stringusuarioid=String.valueOf(inmueble.getUsuarios_idusuarios());
                        integerusuarioid = inmueble.getUsuarios_idusuarios();

                        edtextcomentario = (findViewById(R.id.etcomentario));

                        Toast.makeText(DetailInmueblesAllActivity.this, stringusuarioid, Toast.LENGTH_SHORT).show();


                        //LLAMAR USUARIO POR ID
                        ApiService service = ApiServiceGenerator.createService(ApiService.class);

                        Call<Usuario> call2 = service.showUsuario(integerusuarioid);

                        call2.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call2, Response<Usuario> response) {
                                try {

                                    int statusCode = response.code();
                                    Log.d(TAG, "HTTP status code: " + statusCode);

                                    if (response.isSuccessful()) {

                                        final Usuario usuario2 = response.body();

                                        publicadoText.setText("Publicado por: "+usuario2.getNombre());
                                        telefonoText.setText("Teléfono: "+usuario2.getTelefono());
                                        correoText.setText("Correo: "+usuario2.getCorreo());


                                        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                            @Override
                                            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                                                if(v==5){
                                                    valoracion="Excelente";
                                                }else if (v==4){
                                                    valoracion="Muy Bueno";
                                                }else if(v==3){
                                                    valoracion="Regular";
                                                }else if(v==2){
                                                    valoracion="Malo";
                                                }else if(v==1){
                                                    valoracion="Pésimo";
                                                }

                                                Toast.makeText(DetailInmueblesAllActivity.this, valoracion, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        envalor.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {


                                                String comentariousuario = edtextcomentario.getText().toString();
                                                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                                Call<ResponseMessage> call3 = null;
                                                call3 = service.createRanking(valoracion,comentariousuario,integerusuarioid);

                                                call3.enqueue(new Callback<ResponseMessage>() {
                                                    @Override
                                                    public void onResponse(Call<ResponseMessage> call3, Response<ResponseMessage> response) {
                                                        try {

                                                            int statusCode = response.code();
                                                            //Log.d(TAG, "HTTP status code: " + statusCode);

                                                            if (response.isSuccessful()) {

                                                                ResponseMessage responseMessage = response.body();
                                                                // Log.d(TAG, "responseMessage: " + responseMessage);

                                                                Toast.makeText(DetailInmueblesAllActivity.this, "Valoración enviada", Toast.LENGTH_LONG).show();
                                                                finish();

                                                            } else {
                                                                // Log.e(TAG, "onError: " + response.errorBody().string());
                                                                throw new Exception("Error en el servicio");
                                                            }

                                                        } catch (Throwable t) {
                                                            try {
                                                                //  Log.e(TAG, "onThrowable: " + t.toString(), t);
                                                                Toast.makeText(DetailInmueblesAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                            } catch (Throwable x) {
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                                        // Log.e(TAG, "onFailure: " + t.toString());
                                                        Toast.makeText(DetailInmueblesAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }

                                                });

                                            }
                                        });


                                        correo.setOnClickListener(new View.OnClickListener() {

                                            public void onClick(View v) {
                                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                                Uri uri = Uri.parse("mailto:"+usuario2.getCorreo());
                                                intent.setData(uri);
                                                intent.putExtra("subject", "Rentar inmueble");
                                                intent.putExtra("body", "Hola, estoy interesado en alquilar tu inmueble");
                                                startActivity(intent);

                                            }
                                        });


                                        llamada.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intentcall = new Intent(Intent.ACTION_DIAL);
                                                intentcall.setData(Uri.parse("tel:" + usuario2.getTelefono()));
                                                if (intentcall.resolveActivity(getPackageManager()) != null) {
                                                    startActivity(intentcall);
                                            }}
                                        });
                                        // correoText.setText("Correo: "+usuario2.getCorreo());
                                        //  Log.d(TAG, "producto: " + inmueble);

                                        //  String url = ApiService.API_BASE_URL + "/images/" + inmueble.getImagen();
                                        //  Picasso.with(DetailInmueblesAllActivity.this).load(url).into(fotoImage);

                                        //  nombreText.setText("DIRECCION: "+inmueble.getDireccion());
                                        //  detallesText.setText("DESCRIPCION: "+inmueble.getDescripcion());
                                        //  precioText.setText("TIPO DE COSTO: "+inmueble.getTipo_costo());

                                        //  stringusuarioid=String.valueOf(inmueble.getUsuarios_idusuarios());
                                        //  integerusuarioid = inmueble.getUsuarios_idusuarios();

                                        //  Toast.makeText(DetailInmueblesAllActivity.this, "", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.e(TAG, "onError: " + response.errorBody().string());
                                        throw new Exception("Error en el servicio");
                                    }

                                } catch (Throwable t) {
                                    try {
                                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                                        Toast.makeText(DetailInmueblesAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }catch (Throwable x){}
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call2, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.toString());
                                Toast.makeText(DetailInmueblesAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        });



                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailInmueblesAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailInmueblesAllActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }



    private void initialize2() {


    }

}
