package pe.edu.tecsup.login.Interface;

import pe.edu.tecsup.login.ResponseMessage;
import pe.edu.tecsup.login.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Julio Cesar on 21/03/2018.
 */

public interface ApiService {

    String API_BASE_URL = "https://proyecto-inmuebles-synms.cs50.io";

    //@GET("api/v1/productos")
    //Call<List<Producto>> getProductos();

    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<ResponseMessage> basicLogin(@Field("username") String username,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/usuarios")
    Call<ResponseMessage> createUsuario(@Field("username") String username,
                                         @Field("password") String password,
                                         @Field("email") String email);

}

