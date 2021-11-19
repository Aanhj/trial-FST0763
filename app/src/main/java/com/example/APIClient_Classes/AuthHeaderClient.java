package com.example.APIClient_Classes;

import android.view.ViewDebug;

import com.example.fst_t0763.BuildConfig;

import java.security.PublicKey;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AuthHeaderClient {

    public static Retrofit retrofit=null;
    public static  AuthHeaderClient authHeaderClient;

    public static AuthHeaderClient getAuthHeaderClient(){
        if (authHeaderClient==null){
            authHeaderClient=new AuthHeaderClient();

        }return authHeaderClient;

    }

    public Retrofit AuthheaderClient(String URL){

        OkHttpClient.Builder client=new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG){
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        }else{
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }
        client.addInterceptor(interceptor);
        retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

     return retrofit;
    }

}
