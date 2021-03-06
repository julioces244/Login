package pe.edu.tecsup.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import pe.edu.tecsup.login.Class.Historial;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.edu.tecsup.login.MainActivity.identificador;

public class EditProfileActivity extends AppCompatActivity {
    private static final String TAG = EditProfileActivity.class.getSimpleName();
private Button button;
private EditText nom, ape, img, desc,corr,telf;
private RadioButton fem,mal;
private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        cardView = (CardView) findViewById(R.id.cardViewSave);

         //button = (Button) findViewById(R.id.button2);
        initialize2();

        nom = (EditText) findViewById(R.id.nameProfile);
        ape = (EditText) findViewById(R.id.lastnameProfile);
        corr = (EditText) findViewById(R.id.emailProfile);
        telf = (EditText) findViewById(R.id.telephoneProfile) ;
        desc = (EditText) findViewById(R.id.descriptionProfile);

        fem = (RadioButton) findViewById(R.id.radioButton6);
        mal = (RadioButton) findViewById(R.id.radioButton7);
}

    private void initialize2() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Usuario> call = service.showUsuario(identificador);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Usuario usuario = response.body();
                        Log.d(TAG, "usuario: " +usuario);

                        usuario.getHistoriales();
                        for(Historial historial: usuario.getHistoriales()){
                            Log.d(TAG, "USUARIOSSSSSSSSSS: " +historial.getDescripcion());

                        }
                        //String url = ApiService.API_BASE_URL + "/images/" + producto.getImagen();
                        //Picasso.with(DetailActivity.this).load(url).into(fotoImage);

                        nom.setText(usuario.getNombre());
                        ape.setText(usuario.getApellido());
                        corr.setText(usuario.getCorreo());
                        telf.setText(usuario.getTelefono());
                        desc.setText(usuario.getDescripcion());
                        if(usuario.getGenero().equals("F")){
                            fem.setChecked(true);
                            mal.setChecked(false);
                        }else if(usuario.getGenero().equals("M")){
                            mal.setChecked(true);
                            fem.setChecked(false);
                        }else{
                            mal.setChecked(false);
                            fem.setChecked(false);
                        }


                        cardView.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                //initialize();

//                Intent returnIntent = getIntent();
//                setResult(Activity.RESULT_OK,returnIntent);
//                finish();
                                String nombre = nom.getText().toString();
                                String apellido = ape.getText().toString();
                                String correo = corr.getText().toString();
                                String telefono = telf.getText().toString();
                                String description = desc.getText().toString();
                                String gen="";
                                if (fem.isChecked()==true) {
                                    gen = "F";
                                } else
                                if (mal.isChecked()==true) {
                                    gen = "M";
                                }


                                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                Call<Usuario> call = service.updateUsuario(identificador,nombre,apellido,correo,telefono,"Nothing",description,gen,1);

                                call.enqueue(new Callback<Usuario>() {
                                    @Override
                                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                        try {

                                            int statusCode = response.code();


                                            if (response.isSuccessful()) {

                                                Usuario usuario = response.body();
                                                Toast.makeText(EditProfileActivity.this, "Completed!", Toast.LENGTH_SHORT).show();

//                        String url = ApiService.API_BASE_URL + "/images/" + producto.getImagen();
//                        Picasso.with(DetailActivity.this).load(url).into(fotoImage);
//
//                        nombreText.setText(producto.getNombre());
//                        detallesText.setText(producto.getDetalles());
//                        precioText.setText("Precio: S/. " + producto.getPrecio());


                                            } else {
                                                throw new Exception("Error en el servicio");
                                            }

                                        } catch (Throwable t) {
                                            try {

                                            }catch (Throwable x){}
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Usuario> call, Throwable t) {

                                    }

                                });
//
                                Intent start = new Intent(EditProfileActivity.this, MainActivity.class);
                                startActivity(start);
                            }
                        });

                        //Toast.makeText(EditProfileActivity.this, ""+usuario.getHistoriales(), Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                      //  Toast.makeText(EditProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                //Toast.makeText(EditProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


    private void initialize() {



        Intent start = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(start);
//        ProfileFragment fragment = new ProfileFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_frame_layout, fragment);
//                transaction.commit();
    }





}