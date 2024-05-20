package com.i2i.cellcelly.remote;

import com.i2i.cellcelly.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ILoginApi {

    @GET("/api/customer/login/android/{MSISDN}/{password}")
    Call<LoginRequest> login(@Path("MSISDN") String msisdn, @Path("password") String password);
}
