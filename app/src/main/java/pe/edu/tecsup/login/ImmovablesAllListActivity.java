package pe.edu.tecsup.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.edu.tecsup.login.MainActivity.identificador;

public class ImmovablesAllListActivity extends AppCompatActivity {

    private static final String TAG = ImmovablesAllListActivity.class.getSimpleName();

    InmueblesAllAdapter inmueblesAllAdapter;
    private RecyclerView inmueblesList;
    private EditText editText;
    private List<Inmueble> inmuebles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immovables_all_list);


        inmueblesList = (RecyclerView) findViewById(R.id.recyclerviewall);
        inmueblesList.setLayoutManager(new LinearLayoutManager(this));


        inmueblesAllAdapter = new InmueblesAllAdapter(this);


        inmueblesList.setAdapter(inmueblesAllAdapter);
        editText = findViewById(R.id.busqueda);


        initialize();

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Inmueble>> call = service.getInmuebles();

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        inmuebles = response.body();
                        Log.d(TAG, "inmuebles: " + inmuebles);

                        InmueblesAllAdapter adapter = (InmueblesAllAdapter) inmueblesList.getAdapter();
                        adapter.setInmuebles(inmuebles);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(ImmovablesAllListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(ImmovablesAllListActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    private void filter(String s) {

        ArrayList<Inmueble> filteredList = new ArrayList<>();

        for (Inmueble item :  inmuebles) {
            if (item.getDireccion().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(item);
            }
        }

        inmueblesAllAdapter.filterList(filteredList);

    }

    private static final int REGISTER_FORM_REQUEST = 100;

    public void showRegister2(View view){
        startActivityForResult(new Intent(this, CreateInmuebleActivity.class), REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            initialize();
        }
    }

}
