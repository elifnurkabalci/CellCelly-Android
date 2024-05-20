package com.i2i.cellcelly.remote;

import com.i2i.cellcelly.model.PackageRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPackageApi {
    @GET("/api/package/get/android")
    Call<List<PackageRequest>> getPackageId();
}

