package com.example.temre.appx.service;



import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ServiceGenerator {

    public static String BASE_URL = "http://10.0.2.2:8082/MobileService/web/";
    public static Retrofit retrofit;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    public static <S> S createService(Class<S> serviceClass) {
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }


}
