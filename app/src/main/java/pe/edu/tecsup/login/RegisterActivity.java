package pe.edu.tecsup.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import pe.edu.tecsup.login.Interface.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RegisterActivity extends AppCompatActivity {


    private EditText user;
    private EditText pass;
    private EditText ema;
    private TextView msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = (EditText)findViewById(R.id.userRegister);
        pass = (EditText)findViewById(R.id.passwordRegister);
        ema = (EditText)findViewById(R.id.emailRegister);
        msg = (TextView) findViewById(R.id.textView6);
    }

    public void UserRegister(View view){

        String username = user.getText().toString();
        String password = pass.getText().toString();
        String email = ema.getText().toString();
        String hash;
        if (username.isEmpty() || password.isEmpty()) {

            msg.setText("Complete fields");
        }

        //SecretKey secret1 = generateKey();
        //EncUtil.encryptMsg(password, secret1);

            ApiService service = ApiServiceGenerator.createService(ApiService.class);

            Call<ResponseMessage> call = null;
            call = service.createUsuario(username,password,email);

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                try {

                    int statusCode = response.code();
                    //Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        ResponseMessage responseMessage = response.body();
                       // Log.d(TAG, "responseMessage: " + responseMessage);
                        msg.setText("Already register!");
                        Toast.makeText(RegisterActivity.this, responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                       // Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                      //  Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
               // Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }



    public void viewLogin(View view){
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
    }


 /*
    public static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return ""; // Impossibru!
        }
    }
*/


  /*  public static SecretKey generateKey()
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {

        SecretKeySpec secret;
        return secret = new SecretKeySpec(password.getBytes(), "AES");
    }

    public static byte[] encryptMsg(String message, SecretKey secret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {

        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return cipherText;
    }


*/

  /*
     private static final String ALGORITHM = "AES";
     private static final String KEY = "1Hbfh667adfDEJ78";

    public static String encrypt(String value) throws Exception {
        String encryptedValue64="";
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
            return encryptedValue64;
        }catch (Exception e){
            return null;
        }

    }

    private static Key generateKey() {

            Key key = new SecretKeySpec(KEY.getBytes(),ALGORITHM);
            return key;

    }

*/
}
