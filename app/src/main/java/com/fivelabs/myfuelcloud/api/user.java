package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by breogangf on 28/9/15.
 */
public interface user {
    @GET("/user/username/{username}")
    void getUser(@Path("username") String user, Callback<User> response);

    @GET("/user/")
    void login(Callback<List<User>> response);

    @FormUrlEncoded
    @POST("/user/")
    void register(@Field("username") String username, @Field("email") String email, @Field("password") String password, @Field("created_at") Number created_at, Callback<User> response);

}