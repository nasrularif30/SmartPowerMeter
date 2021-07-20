package com.movtech.smartpowermeter.apihelper;

import retrofit2.Retrofit;

public class UtillsApi {
    public static final String BASE_URL_API = "http://Iot4energy.id/api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
