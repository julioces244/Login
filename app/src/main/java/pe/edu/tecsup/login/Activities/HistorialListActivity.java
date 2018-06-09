package pe.edu.tecsup.login.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pe.edu.tecsup.login.Adapters.HistorialAdapter;
import pe.edu.tecsup.login.Class.Historial;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import pe.edu.tecsup.login.MainActivity;
import pe.edu.tecsup.login.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialListActivity extends AppCompatActivity {

    private static final String TAG = HistorialListActivity.class.getSimpleName();

    private RecyclerView historialList;
    private static final int REGISTER_FORM_REQUEST = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostorial_list);

        historialList = findViewById(R.id.recyclerview);
        historialList.setLayoutManager(new LinearLayoutManager(this));

        historialList.setAdapter(new HistorialAdapter());

        initialitate();

    }

    private void initialitate() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<Usuario> listCall = service.showUsuario(MainActivity.identificador);

//        Log.d(TAG, "token is: " + token);

        listCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Usuario his = response.body();
                        Log.d(TAG, "his: " + his);

//                        for(Historial historial: his.getHistoriales()){
//                            Toast.makeText(HistorialListActivity.this, "USUARIOSSSSSSSSSS: " +historial.getDescripcion(), Toast.LENGTH_SHORT).show();
//                        }



                        List<Historial> his1 = his.getHistoriales();

//                        for ()


//                        for (Historial os : his1) {
//
//                            Log.i(TAG, "La descripcion es; "  + os.getDescripcion());
//                        }


//                        Toast.makeText(HistorialListActivity.this, "CANTIDAD: " + his1.get(0).getDescripcion(), Toast.LENGTH_SHORT).show();




                        HistorialAdapter adapter = (HistorialAdapter) historialList.getAdapter();
                        adapter.setHistorial(his1);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(HistorialListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(HistorialListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });

    }

}
