package com.movtech.smartpowermeter.apihelper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.movtech.smartpowermeter.apihelper.UtillsApi.BASE_URL_API;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
