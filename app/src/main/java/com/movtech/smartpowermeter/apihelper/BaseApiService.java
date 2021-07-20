package com.movtech.smartpowermeter.apihelper;

import com.movtech.smartpowermeter.model.LoginModel.LoginResponse;
import com.movtech.smartpowermeter.model.RegisterModel.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginRequest(@Field("username") String username,
                                     @Field("password") String password);
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse>registerRequest(@Field("username") String username,
                                          @Field("nama") String nama,
                                          @Field("email") String email,
                                          @Field("password") String password,
                                          @Field("telfon") String telfon,
                                          @Field("alamat") String alamat);
}
