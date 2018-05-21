package pe.edu.tecsup.login;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.List;

import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Fragments.CalendarFragment;
import pe.edu.tecsup.login.Fragments.HomeFragment;
import pe.edu.tecsup.login.Fragments.ImmovablesAllFragment;
import pe.edu.tecsup.login.Fragments.ProfileFragment;
import pe.edu.tecsup.login.Fragments.MyImmovablesFragment;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.ApiServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ResideMenu resideMenu;
    private Context mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemAllinmuebles;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static String usernamen;
    private int id;
    private TextView a;
    public static int identificador;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());



        initialize();




    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
        itemProfile  = new ResideMenuItem(this, R.drawable.icon_profile,  "Profile");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Historial");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Inmuebles");
        itemAllinmuebles = new ResideMenuItem(this, R.drawable.icn_5, "Renta");

        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemAllinmuebles.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemAllinmuebles, ResideMenu.DIRECTION_RIGHT);
        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new HomeFragment());
        }else if (view == itemProfile){
            changeFragment(new ProfileFragment());
        }else if (view == itemCalendar){
            changeFragment(new CalendarFragment());
        }else if (view == itemSettings){
            changeFragment(new MyImmovablesFragment());
        }else if(view== itemAllinmuebles){
            changeFragment(new ImmovablesAllFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }


//    private void initialize() {
//        a = (TextView) findViewById(R.id.nuevo);
//        ApiService service = ApiServiceGenerator.createService(ApiService.class);
//
//        Call<Usuario> call = service.showUsuario(5);
//
//        call.enqueue(new Callback<Usuario>() {
//            @Override
//            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
//                try {
//
//                    int statusCode = response.code();
//                    Log.d(TAG, "HTTP status code: " + statusCode);
//
//                    if (response.isSuccessful()) {
//
//                        Usuario usuario = response.body();
//
//
////                        String url = ApiService.API_BASE_URL + "/images/" + producto.getImagen();
////                        Picasso.with(DetailActivity.this).load(url).into(fotoImage);
//
//                        a.setText(usuario.getNombre());
//
//                    } else {
//                        Log.e(TAG, "onError: " + response.errorBody().string());
//                        throw new Exception("Error en el servicio");
//                    }
//
//                } catch (Throwable t) {
//                    try {
//                        Log.e(TAG, "onThrowable: " + t.toString(), t);
//                    }catch (Throwable x){}
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Usuario> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.toString());
//            }
//
//        });
//    }


    private void initialize() {
        usernamen = getIntent().getStringExtra("username");
        a = (TextView) findViewById(R.id.nuevo);
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

                        for(int i=0; i < usuarios.size(); i++){
                            if(usernamen.equals(usuarios.get(i).getUsername())){
                                final Usuario usuario = usuarios.get(i);
                                    identificador = usuario.getIdusuario();
                                    a.setText(usuario.getNombre());
                            }


                        }






                        Log.d(TAG, "productos: " + usuarios);



                    } else {

                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {

                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
