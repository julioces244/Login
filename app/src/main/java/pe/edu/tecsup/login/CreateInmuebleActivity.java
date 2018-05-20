package pe.edu.tecsup.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.edu.tecsup.login.MainActivity.identificador;

public class CreateInmuebleActivity extends AppCompatActivity {

    private static final String TAG = CreateInmuebleActivity.class.getSimpleName();
    private static final int MAPS_ACTIVITY_REQUEST = 100;
    private ImageView imagePreview;

    private EditText direccionInput;
    private EditText descripcionInput;
    private EditText tipo_costoInput;
    private EditText costoInput;
    private ImageView imageView;
    private EditText latitudText;
    private EditText longitudText;
    private EditText direccionText;
    private Spinner tipo_pago_spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inmueble);

        imagePreview = (ImageView) findViewById(R.id.imagen_preview);
        //direccionInput = (EditText) findViewById(R.id.direccion_input);
        descripcionInput = (EditText) findViewById(R.id.descripcion_input);
        //tipo_costoInput = (EditText) findViewById(R.id.tipo_costo_input);
        costoInput = (EditText) findViewById(R.id.costo_input);
        imageView = findViewById(R.id.mapaButton);
        latitudText = findViewById(R.id.etlatitud);
        longitudText = findViewById(R.id.etlongitud);
        direccionText = findViewById(R.id.etdirection);
        tipo_pago_spn = (Spinner) findViewById(R.id.spinner_tipo_pago);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(CreateInmuebleActivity.this, MapsActivity.class), MAPS_ACTIVITY_REQUEST);
            }
        });

//        tipo_pago_spn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (tipo_pago_spn.getSelectedItem().toString().equals("Diario"));{
//
//                }
//            }
//        });


    }

    /**
     * Camera handler
     */

    private static final int CAPTURE_IMAGE_REQUEST = 300;

    private Uri mediaFileUri;

    public void takePicture(View view) {
        try {

            if (!permissionsGranted()) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_LIST, PERMISSIONS_REQUEST);
                return;
            }

            // Creando el directorio de imágenes (si no existe)
            File mediaStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    throw new Exception("Failed to create directory");
                }
            }

            // Definiendo la ruta destino de la captura (Uri)
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
            mediaFileUri = Uri.fromFile(mediaFile);

            // Iniciando la captura
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mediaFileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_REQUEST);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(this, "Error en captura: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_REQUEST) {
            // Resultado en la captura de la foto
            if (resultCode == RESULT_OK) {
                try {
                    Log.d(TAG, "ResultCode: RESULT_OK");
                    // Toast.makeText(this, "Image saved to: " + mediaFileUri.getPath(), Toast.LENGTH_LONG).show();

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mediaFileUri);

                    // Reducir la imagen a 800px solo si lo supera
                    bitmap = scaleBitmapDown(bitmap, 800);

                    imagePreview.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                    Toast.makeText(this, "Error al procesar imagen: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "ResultCode: RESULT_CANCELED");
            } else {
                Log.d(TAG, "ResultCode: " + resultCode);
            }
        }

        switch (requestCode){
            case MAPS_ACTIVITY_REQUEST:
                if(resultCode == Activity.RESULT_OK){

                    if(data.getExtras() != null) {

                        String latitud = data.getExtras().getString("latitud");
                        String longitud = data.getExtras().getString("longitud");
                        String address = data.getExtras().getString("address");

                        latitudText.setText(latitud);
                        longitudText.setText(longitud);
                        direccionText.setText(address);

                        if (CreateInmuebleActivity.this != null)
                            Toast.makeText(CreateInmuebleActivity.this, "Localización confirmada", Toast.LENGTH_LONG).show();

                    }

                }
                break;
        }

    }



    public void callRegister(View view) {

        //String direccion = direccionInput.getText().toString();
        String descripcion = descripcionInput.getText().toString();
        //String tipocosto = tipo_costoInput.getText().toString();
        double costo = Double.parseDouble(costoInput.getText().toString());
        String lat = latitudText.getText().toString();
        String lon = longitudText.getText().toString();
        String dir = direccionText.getText().toString();
        String tp = tipo_pago_spn.getSelectedItem().toString();
        String coord = lat+" "+lon;

//        if (nombre.isEmpty() || precio.isEmpty()) {
//            Toast.makeText(this, "Nombre y Precio son campos requeridos", Toast.LENGTH_SHORT).show();
//            return;
//        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<ResponseMessage> call = null;

        if (mediaFileUri == null) {
            // Si no se incluye imagen hacemos un envío POST simple
            call = service.createInmueble(dir,coord, descripcion, tp,lat,lon,costo,identificador);
        } else {
            // Si se incluye hacemos envió en multiparts

            int id= identificador;
            String ider = Integer.toString(id);

            File file = new File(mediaFileUri.getPath());
            Log.d(TAG, "File: " + file.getPath() + " - exists: " + file.exists());

            // Podemos enviar la imagen con el tamaño original, pero lo mejor será comprimila antes de subir (byteArray)
            // RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);

            Bitmap bitmap = BitmapFactory.decodeFile(mediaFileUri.getPath());

            // Reducir la imagen a 800px solo si lo supera
            bitmap = scaleBitmapDown(bitmap, 800);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), byteArray);
            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", file.getName(), requestFile);
            String costostring = String.valueOf(costo);
            RequestBody direccionPart = RequestBody.create(MultipartBody.FORM, dir);
            RequestBody descripcionPart = RequestBody.create(MultipartBody.FORM, descripcion);
            RequestBody tipocostoPart = RequestBody.create(MultipartBody.FORM, tp);
            RequestBody latitudPart = RequestBody.create(MultipartBody.FORM, lat);
            RequestBody longitudPart = RequestBody.create(MultipartBody.FORM, lon);
            RequestBody costoPart = RequestBody.create(MultipartBody.FORM, costostring);

            //RequestBody costoPart = RequestBody.create(MultipartBody.FORM, costo);
            RequestBody identificadorPart = RequestBody.create(MultipartBody.FORM, ider);

            call = service.createInmuebleWithImage(direccionPart, descripcionPart, tipocostoPart,latitudPart, longitudPart,costoPart,identificadorPart,imagenPart);
        }

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        ResponseMessage responseMessage = response.body();
                        Log.d(TAG, "responseMessage: " + responseMessage);

                        Toast.makeText(CreateInmuebleActivity.this, responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(CreateInmuebleActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(CreateInmuebleActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    /**
     * Permissions handler
     */

    private static final int PERMISSIONS_REQUEST = 200;

    private static String[] PERMISSIONS_LIST = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private boolean permissionsGranted() {
        for (String permission : PERMISSIONS_LIST) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST: {
                for (int i = 0; i < grantResults.length; i++) {
                    Log.d(TAG, "" + grantResults[i]);
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, PERMISSIONS_LIST[i] + " permiso rechazado!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Toast.makeText(this, "Permisos concedidos, intente nuevamente.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Redimensionar una imagen bitmap
    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }





}