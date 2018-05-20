package pe.edu.tecsup.login.Interface;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pe.edu.tecsup.login.Interface.ApiService;
import pe.edu.tecsup.login.Interface.AuthenticationInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static pe.edu.tecsup.login.Interface.ApiService.API_BASE_URL;

/**
 * Created by Julio Cesar on 21/03/2018.
 */

public final class ApiServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiService.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit;

    private ApiServiceGenerator() {
    }


/*
    public static <S> S createService(Class<S> serviceClass) {
        if(retrofit == null) {

            // Retrofit Token: https://futurestud.io/tutorials/retrofit-token-authentication-on-android
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    try {

                        Request originalRequest = chain.request();

                        // Load Token from SharedPreferences (firsttime is null)
                        String token = PreferencesManager.getInstance().get(PreferencesManager.PREF_TOKEN);
                        Log.d(TAG, "Loaded Token: " + token);

                        if(token == null){
                            // Firsttime assuming login
                            return chain.proceed(originalRequest);
                        }

                        // Request customization: add request headers
                        Request modifiedRequest = originalRequest.newBuilder()
                                .header("Authorization", token)
                                .build();

                        return chain.proceed(modifiedRequest); // Call request with token


                    }catch (Exception e){
                        Log.e(TAG, e.toString());
                        FirebaseCrash.report(e);
                        throw e;
                    }

                }
            });

            retrofit = builder.client(httpClient.build()).build();
        }
        return retrofit.create(serviceClass);
    }


*/








   public static <S> S createService(Class<S> serviceClass) {
        if(retrofit == null) {
            retrofit = builder.client(httpClient.build()).build();
        }
        return retrofit.create(serviceClass);
    }















 /*   private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }





    public static <S> S createService(
            Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        return retrofit.create(serviceClass);
    }
*/
}

