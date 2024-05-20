package com.i2i.cellcelly.remote;

import com.i2i.cellcelly.model.RegisterRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IRegisterApi {
    @FormUrlEncoded
    @POST("/api/customer/android/register")
    Call<RegisterRequest> register(@FieldMap Map<String, Object> fields);
}