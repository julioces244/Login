package pe.edu.tecsup.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.tecsup.login.Interface.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private EditText user;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.userLogin);
        pass = (EditText)findViewById(R.id.passwordLogin);



    }




    public void card(View view) {

        final String username = user.getText().toString();
        String password = pass.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {

            Toast.makeText(LoginActivity.this, "Falta completar campos", Toast.LENGTH_LONG).show();

        }


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
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        finish();

                    } else {
                        // Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        //  Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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


    }

    public void viewregister(View view){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
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