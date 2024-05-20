package com.i2i.cellcelly.remote;

import com.i2i.cellcelly.model.PackageBalanceRemaining;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPackageBalanceRemaining {

    @GET("/api/balance/getuserbalance/android/{MSISDN}")
    Call<PackageBalanceRemaining> getPackageBalanceRemaining(@Path("MSISDN") String msisdn);

}
