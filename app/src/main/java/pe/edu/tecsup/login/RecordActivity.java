package pe.edu.tecsup.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pe.edu.tecsup.login.Class.Historial;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

//        productosList = (RecyclerView) findViewById(R.id.recyclerview);
//        productosList.setLayoutManager(new LinearLayoutManager(this));
//
//        productosList.setAdapter(new ProductosAdapter());
//
//        initialize();

    }


//    private void initialize() {
//
//        ApiService service = ApiServiceGenerator.createService(ApiService.class);
//
//        Call<List<Historial>> call = service.getHistoriales();
//
//        call.enqueue(new Callback<List<Historial>>() {
//            @Override
//            public void onResponse(Call<List<Historial>> call, Response<List<Historial>> response) {
//                try {
//
//                    int statusCode = response.code();
//                    Log.d(TAG, "HTTP status code: " + statusCode);
//
//                    if (response.isSuccessful()) {
//
//                        List<Historial> historiales = response.body();
//                        Log.d(TAG, "productos: " + historiales);
//
//                        HistorialesAdapter adapter = (HistorialesAdapter) historialesList.getAdapter();
//                        adapter.setHistoriales(historiales);
//                        adapter.notifyDataSetChanged();
//
//                    } else {
//                        Log.e(TAG, "onError: " + response.errorBody().string());
//                        throw new Exception("Error en el servicio");
//                    }
//
//                } catch (Throwable t) {
//                    try {
//                        Log.e(TAG, "onThrowable: " + t.toString(), t);
//                        Toast.makeText(RecordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                    }catch (Throwable x){}
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Producto>> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.toString());
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//        });
//    }




}
