package pe.edu.tecsup.login.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.DetailInmueblesAllActivity;
import pe.edu.tecsup.login.EditProfileActivity;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import pe.edu.tecsup.login.LoginActivity;
import pe.edu.tecsup.login.MainActivity;
import pe.edu.tecsup.login.R;
import pe.edu.tecsup.login.ResponseMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.edu.tecsup.login.MainActivity.identificador;

public class VerificationActivity extends AppCompatActivity {
    private static final String TAG = VerificationActivity.class.getSimpleName();
    private EditText usuariocodigo;
    private EditText verificacioncodigo;
    private CardView verificar;
    public static int ci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        usuariocodigo = findViewById(R.id.codeusername);
        verificacioncodigo = findViewById(R.id.codeverification);
        verificar = findViewById(R.id.cardViewVerification);


        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
            }
        });
    }




    private void initialize() {

        final String usercodigo = usuariocodigo.getText().toString();
        final String vercodigo = verificacioncodigo.getText().toString();

//        usernamen = getIntent().getStringExtra("username");
//        a = (TextView) findViewById(R.id.nuevo);
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Usuario>> call = service.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Usuario> usuarios = response.body();
                        String cod="";

                        for(int i=0; i < usuarios.size(); i++){
                            if(usercodigo.equals(usuarios.get(i).getUsername())){
                                // vercodigo.equals(usuarios.get(i).getCodigo())
                               final Usuario usuario24 = usuarios.get(i);
                                cod =usuario24.getCodigo();
                                ci =usuario24.getIdusuario();
                            }
                        }

                       // Toast.makeText(VerificationActivity.this, ""+cod, Toast.LENGTH_SHORT).show();



                        ApiService service = ApiServiceGenerator.createService(ApiService.class);

                        Call<Usuario> call2 = service.showUsuario(ci);

                        call2.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call2, Response<Usuario> response) {
                                try {

                                    int statusCode = response.code();
                                    Log.d(TAG, "HTTP status code: " + statusCode);

                                    if (response.isSuccessful()) {

                                        final Usuario usuarioverification = response.body();

                                        if(vercodigo.equals(usuarioverification.getCodigo())){



                                            Toast.makeText(VerificationActivity.this, "Correcto", Toast.LENGTH_SHORT).show();

                                            ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                            Call<Usuario> call = service.updateUsuarioEstado(ci,1);

                                            call.enqueue(new Callback<Usuario>() {
                                                @Override
                                                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                                    try {

                                                        int statusCode = response.code();


                                                        if (response.isSuccessful()) {

                                                            Usuario usuario = response.body();
                                                            Toast.makeText(VerificationActivity.this, "Completedo!", Toast.LENGTH_SHORT).show();
                                                            Intent i2= new Intent(VerificationActivity.this, LoginActivity.class);
                                                            startActivity(i2);

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


                                        }else {
                                            Toast.makeText(VerificationActivity.this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
                                        }



                                    } else {
                                        Log.e(TAG, "onError: " + response.errorBody().string());
                                        throw new Exception("Error en el servicio");
                                    }

                                } catch (Throwable t) {
                                    try {
                                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                                        Toast.makeText(VerificationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }catch (Throwable x){}
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call2, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.toString());
                                Toast.makeText(VerificationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        });




                        Log.d(TAG, "Usuarios: " + usuarios);

                    } else {

                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {

                        Toast.makeText(VerificationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(VerificationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
