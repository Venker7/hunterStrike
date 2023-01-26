package com.devroid.hunterstrike;

import com.devroid.hunterstrike.ModelResponse.FetchDonorUserResponse;
import com.devroid.hunterstrike.ModelResponse.createDonorResponse;
import com.devroid.hunterstrike.ModelResponse.loginResponse;
import com.devroid.hunterstrike.ModelResponse.registerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    @GET("/home/displaydonor")
    Call<FetchDonorUserResponse> fetchdonors();

    @FormUrlEncoded
    @POST("/home/postdonor")
    Call<createDonorResponse> createdonor(
            @Field("name") String name,
            @Field("bloodgroup") String bloodgroup,
            @Field("address") String address,
            @Field("number") String number,
            @Field("message") String message
    );
}
