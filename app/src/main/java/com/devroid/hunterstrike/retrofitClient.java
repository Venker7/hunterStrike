package com.devroid.hunterstrike;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient {
    private static String BASE_URL="http://192.168.190.248:3000/";
    private static  retrofitClient retrofitClient;
    private static Retrofit retrofit;

    private retrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized retrofitClient getInstance(){
        if(retrofitClient==null) {
            retrofitClient = new retrofitClient();
        }
        return retrofitClient;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }



}