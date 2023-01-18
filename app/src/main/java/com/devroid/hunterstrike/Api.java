package com.devroid.hunterstrike;

import com.devroid.hunterstrike.ModelResponse.loginResponse;
import com.devroid.hunterstrike.ModelResponse.registerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("/home/register")
    Call<registerResponse> register(
            @Field("name")  String name,
            @Field("email")  String email,
            @Field("number")  Integer number,
            @Field("password")  String password,
            @Field("confirmpassword")  String confirmpassword

    );
    @FormUrlEncoded
    @POST("/home/login")
    Call<loginResponse> login(
            @Field("email")  String email,
            @Field("password")  String password
    );
}
