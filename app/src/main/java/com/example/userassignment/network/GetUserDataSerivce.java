package com.example.userassignment.network;

import com.example.userassignment.model.LoginResponse;
import com.example.userassignment.model.Registor;
import com.example.userassignment.model.UserList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetUserDataSerivce {


    @FormUrlEncoded
    @POST("api/register")
    Call<Registor> registorUser(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("/api/users?page=2")
    Call<UserList> getUserData();

    @FormUrlEncoded
    @POST("/api/users")
    Call<ResponseBody> createUser(
            @Field("name") String name,
            @Field("job") String job
    );

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
