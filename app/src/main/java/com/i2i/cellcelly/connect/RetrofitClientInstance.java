package com.i2i.cellcelly.connect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i2i.cellcelly.model.LoginRequest;
import com.i2i.cellcelly.remote.ILoginApi;
import com.i2i.cellcelly.remote.IPackageApi;
import com.i2i.cellcelly.remote.IPackageBalanceRemaining;
import com.i2i.cellcelly.remote.IPackageInfo;
import com.i2i.cellcelly.remote.IRegisterApi;

import org.eazegraph.lib.models.BaseModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        LoginRequest temp = new LoginRequest();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://35.194.5.106:8080")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static IRegisterApi getPackageId(){

        IRegisterApi iRegisterApi = getRetrofit().create(IRegisterApi.class);
        return iRegisterApi;
    }

    //Put retrofit update password
    public static IPackageApi getPackageIdName(){

        IPackageApi iPackageApi = getRetrofit().create(IPackageApi.class);

        return iPackageApi;
    }
    public static ILoginApi getUserService(){

        ILoginApi iLoginApi = getRetrofit().create(ILoginApi.class);

        return iLoginApi;
    }
    public static IPackageInfo getPackageInfo(){

        IPackageInfo iPackageInfo = getRetrofit().create(IPackageInfo.class);

        return  iPackageInfo;
    }
    public static IPackageBalanceRemaining getBalanceRemaining(){
        IPackageBalanceRemaining iPackageBalanceRemaining = getRetrofit().create(IPackageBalanceRemaining.class);

        return iPackageBalanceRemaining;
    }
}
