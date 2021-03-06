package pe.edu.tecsup.login.Interface;

import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pe.edu.tecsup.login.Class.Historial;
import pe.edu.tecsup.login.Class.Inmueble;
import pe.edu.tecsup.login.Class.Usuario;
import pe.edu.tecsup.login.Coordenada;
import pe.edu.tecsup.login.ResponseMessage;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Julio Cesar on 21/03/2018.
 */

public interface ApiService {

    String API_BASE_URL = "https://proyecto-inmuebles-synms.cs50.io";

    @GET("api/v1/usuarios")
    Call<List<Usuario>> getUsuarios();

    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<ResponseMessage> basicLogin(@Field("username") String username,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/usuarios")
    Call<ResponseMessage> createUsuario(@Field("username") String username,
                                         @Field("password") String password,
                                         @Field("correo") String correo,
                                         @Field("telefono") String telefono);


    @FormUrlEncoded
    @POST("/api/v1/rankings")
    Call<ResponseMessage> createRanking(@Field("puntaje") Integer puntaje,
                                        @Field("comentario") String comentario,
                                        @Field("usuarios_idusuarios") Integer usuarios_idusuarios,
                                        @Field("idinmueble") Integer idinmueble);

    @GET("api/v1/coordenadas")
    Call<List<Coordenada>> getCoordenadas();

    @FormUrlEncoded
    @PUT("api/v1/usuarios/{idusuario}")
    Call<Usuario> updateUsuario(@Path("idusuario") Integer idusuario,
                                        @Field("nombre") String nombre,
                                        @Field("apellido") String apellido,
                                        @Field("correo") String correo,
                                        @Field("telefono") String telefono,
                                        @Field("imagen") String imagen,
                                        @Field("descripcion") String descripcion,
                                        @Field("genero") String genero,
                                        @Field("estado") Integer estado);

    @FormUrlEncoded
    @PUT("api/v1/usuarios/{idusuario}")
    Call<Usuario> updateUsuarioEstado(@Path("idusuario") Integer idusuario,
                                @Field("estado") Integer estado);


    @GET("api/v1/usuarios/{idusuario}")
    Call<Usuario> showUsuario(@Path("idusuario") Integer idusuario);


    @GET("api/v1/inmuebles")
    Call<List<Inmueble>> getInmuebles();


    @FormUrlEncoded
    @POST("/api/v1/inmuebles")
    Call<ResponseMessage> createInmueble(@Field("direccion") String direccion,
                                         @Field("coordenadas") String coordenadas,
                                         @Field("descripcion") String descripcion,
                                         @Field("tipo_costo") String tipo_costo,
                                         @Field("latitud") String latitud,
                                         @Field("longitud") String longitud,
                                         @Field("costo") double costo,
                                         @Field("usuarios_idusuarios") int usuarios_idusuarios);
    @Multipart
    @POST("/api/v1/inmuebles")
    Call<ResponseMessage> createInmuebleWithImage(
            @Part("direccion") RequestBody direccion,
            @Part("descripcion") RequestBody descripcion,
            @Part("tipo_costo") RequestBody tipo_costo,
            @Part("latitud") RequestBody latitud,
            @Part("longitud") RequestBody longitud,
            @Part("costo") RequestBody costo,
            @Part("usuarios_idusuarios") RequestBody usuarios_idusuarios,
            @Part MultipartBody.Part imagen
    );

    @DELETE("/api/v1/inmuebles/{idinmueble}")
    Call<ResponseMessage> destroyInmueble(@Path("idinmueble") Integer id);


    @GET("api/v1/inmuebles/{idinmueble}")
    Call<Inmueble> showInmueble(@Path("idinmueble") Integer idinmueble);

    @GET("api/v1/misinmuebles/{usuarios_idusuarios}")
    Call<List<Inmueble>> showMyInmueble(@Path("usuarios_idusuarios") Integer usuarios_idusuarios);

    @FormUrlEncoded
    @POST("/api/v1/historiales")
    Call<ResponseMessage> createHistorial(@Field("usuarios_idusuarios") int usuarios_idusuarios,
                                         @Field("inmueble_idinmueble") int inmueble_idinmueble,
                                         @Field("fecha_usuario") Date fecha_usuario);

    @GET("api/v1/historiales")
    Call<List<Historial>> getHistoriales();

    @GET("api/v1/historiales/{usuarios_idusuarios}")
    Call<Historial> showMyHistorial(@Path("usuarios_idusuarios") Integer usuarios_idusuarios);


}

