package pe.edu.tecsup.login.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.EditProfileActivity;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.LoginActivity;
import pe.edu.tecsup.login.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static pe.edu.tecsup.login.MainActivity.identificador;





public class ProfileFragment extends Fragment {
    private View parentView;
    private View view;
    private TextView nombre,apellido,correo,telefono,descripcion,genero,numdocumento,tipodocumento;
    private String cadena;
    private ProgressDialog progressDialog;
    private static final int PROFILE_ACTIVITY_REQUEST = 100;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
            nombre = (TextView) view.findViewById(R.id.tvNumber1);
            apellido = (TextView) view.findViewById(R.id.tvNumber2);
            correo = (TextView) view.findViewById(R.id.tvNumber3);
            telefono = (TextView) view.findViewById(R.id.tvNumber4);
            genero = (TextView) view.findViewById(R.id.tvNumber5);
            descripcion = (TextView) view.findViewById(R.id.tvNumber6);
          //  tipodocumento = (TextView) view.findViewById(R.id.tvNumber7);
          //  numdocumento = (TextView) view.findViewById(R.id.tvNumber8);
        initialize();

        //Toast.makeText(getActivity(), Integer.toString(identificador), Toast.LENGTH_SHORT).show();

        Button button = (Button) view.findViewById(R.id.buttonedit);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent editprofile = new Intent(ProfileFragment.this.getActivity(), EditProfileActivity.class);
                startActivity(editprofile);

              //  getActivity().startActivityForResult(new Intent(getActivity(), EditProfileActivity.class), PROFILE_ACTIVITY_REQUEST);


            }
        });

        return view;


     /*   FloatingActionButton fab = (FloatingActionButton) parentView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        */
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == PROFILE_ACTIVITY_REQUEST) {
//            if(resultCode == Activity.RESULT_OK){
//                Toast.makeText(getActivity(), " ", Toast.LENGTH_SHORT).show();
//                Intent refresh = new Intent(getActivity(), ProfileFragment.class);
//                startActivity(refresh);
//                this.finish(); //
//            }
//
//        }
//    }//onActivityResult



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


        private void initialize() {

            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();

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
                        nombre.setText(usuario.getNombre());
                        apellido.setText(usuario.getApellido());
                        correo.setText(usuario.getCorreo());
                        telefono.setText(usuario.getTelefono());
                        genero.setText(usuario.getGenero());
                        descripcion.setText(usuario.getDescripcion());


  //                      tipodocumento.setText(usuario.getTipo_documento());
//                        numdocumento.setText(usuario.getNum_documento());

//                        String url = ApiService.API_BASE_URL + "/images/" + producto.getImagen();
//                        Picasso.with(DetailActivity.this).load(url).into(fotoImage);

                        progressDialog.dismiss();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }

        });
    }

}
