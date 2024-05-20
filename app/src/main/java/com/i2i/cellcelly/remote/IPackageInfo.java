package com.i2i.cellcelly.remote;

import com.i2i.cellcelly.model.PackageInfoRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPackageInfo {

    @GET("/api/balance/getuserbalance/android/{MSISDN}")
    Call<PackageInfoRequest> getPackageInfo(@Path("MSISDN") String msisdn);
}
