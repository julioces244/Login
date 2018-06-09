package pe.edu.tecsup.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.edu.tecsup.login.Activities.VerificationActivity;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText user;
    private EditText pass;
    private ProgressDialog progressDialog;
    public static int co;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user = (EditText)findViewById(R.id.userLogin);
        pass = (EditText)findViewById(R.id.passwordLogin);



    }




    public void card(View view) {

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        final String username = user.getText().toString();

        //final String idusuario = user.getText().toString();
        final String password = pass.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {

            Toast.makeText(LoginActivity.this, "Falta completar campos", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

        }else{






            //OBTENIENDO LOS USUARIOS

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
                            //BUSCAR AL USUARIO CON EL USERNAME INGRESADO Y OBTENER SU ID
                            for(int i=0; i < usuarios.size(); i++){
                                if(username.equals(usuarios.get(i).getUsername())){
                                    final Usuario usuario23 = usuarios.get(i);
                                    co = usuario23.getIdusuario();

                                }
                            }

                            //ACCEDE A LOS CAMPOS DEL USUARIO POR ID

                            ApiService service = ApiServiceGenerator.createService(ApiService.class);

                            Call<Usuario> call2 = service.showUsuario(co);

                            call2.enqueue(new Callback<Usuario>() {
                                @Override
                                public void onResponse(Call<Usuario> call2, Response<Usuario> response) {
                                    try {

                                        int statusCode = response.code();
                                        Log.d(TAG, "HTTP status code: " + statusCode);

                                        if (response.isSuccessful()) {

                                            //SI EL ESTADO DEL USUARIO INGRESADO ES 1, INGRESA, CASO QUE SEA 0, NO INGRESA
                                            final Usuario usuariologin = response.body();
                                            int est= usuariologin.getEstado();
                                            if(est==1){



                                                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                                Call<ResponseMessage> call = null;
                                                call = service.basicLogin(username,password);

                                                call.enqueue(new Callback<ResponseMessage>() {
                                                    @Override
                                                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                                        try {

                                                            int statusCode = response.code();
                                                            //Log.d(TAG, "HTTP status code: " + statusCode);

                                                            if (response.isSuccessful()) {

                                                                ResponseMessage responseMessage = response.body();
                                                                // Log.d(TAG, "responseMessage: " + responseMessage);
                                                                //msg.setText("Already register!");
                                                                //Toast.makeText(LoginActivity.this, responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                                intent.putExtra("username", username);
                                                                startActivity(intent);
                                                                progressDialog.dismiss();
                                                                finish();

                                                            } else {
                                                                // Log.e(TAG, "onError: " + response.errorBody().string());
                                                                throw new Exception("Error en el servicio");

                                                            }

                                                        } catch (Throwable t) {
                                                            try {
                                                                //  Log.e(TAG, "onThrowable: " + t.toString(), t);
                                                                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                                progressDialog.dismiss();
                                                            } catch (Throwable x) {
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                                        // Log.e(TAG, "onFailure: " + t.toString());
                                                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }

                                                });


                                            }else{
                                                Toast.makeText(LoginActivity.this, "Cuenta no verificada", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }


                                        } else {
                                            progressDialog.dismiss();
                                            Log.e(TAG, "onError: " + response.errorBody().string());
                                            throw new Exception("Credenciales incorrectas");

                                        }

                                    } catch (Throwable t) {
                                        try {
                                            Log.e(TAG, "onThrowable: " + t.toString(), t);
                                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                        }catch (Throwable x){}
                                    }
                                }

                                @Override
                                public void onFailure(Call<Usuario> call2, Throwable t) {
                                    Log.e(TAG, "onFailure: " + t.toString());
                                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                }

                            });






                            Log.d(TAG, "Usuarios: " + usuarios);

                        } else {

                            throw new Exception("Error en el servicio");
                        }

                    } catch (Throwable t) {
                        try {

                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }catch (Throwable x){}
                    }
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.toString());
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }

            });





        }






















    }

    public void viewregister(View view){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    public void viewverificacion(View view){
        Intent i2= new Intent(LoginActivity.this, VerificationActivity.class);
        startActivity(i2);
    }

//    ApiService apiService =
//            ApiServiceGenerator.createService(ApiService.class, "user", "password");


   /* Call<Usuario> call = apiService.basicLogin();
    call.enqueue(new Callback<Usuario >() {
        @Override
        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
            if (response.isSuccessful()) {
                // user object available
            } else {
                // error response, no access to resource?
            }
        }

        @Override
        public void onFailure(Call<Usuario> call, Throwable t) {
            // something went completely south (like no internet connection)
            Log.d("Error", t.getMessage());
        }
    }
*/

}