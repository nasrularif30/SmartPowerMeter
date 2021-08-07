package com.movtech.smartpowermeter.apihelper;

import com.movtech.smartpowermeter.model.LoginModel.LoginResponse;
import com.movtech.smartpowermeter.model.Mon1Phase.Mon1PhaseResponse;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableResponse;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseResponse;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.Mon3PhaseTableResponse;
import com.movtech.smartpowermeter.model.RegisterModel.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
                                          @Field("telf") long telfon,
                                          @Field("alamat") String alamat,
                                          @Field("level") String level);
    @GET("data.php")
    Call<Mon1PhaseResponse>getOnePhase(@Query("page")String page);
    @GET("data.php")
    Call<Mon3PhaseResponse>getThreePhase(@Query("page")String page);
    @GET("data.php")
    Call<Mon1PhaseTableResponse>getTable1Phase(@Query("page")String page);
    @GET("data.php")
    Call<Mon3PhaseTableResponse>getTable3Phase(@Query("page")String page,
                                               @Query("phase")String phase);
}
