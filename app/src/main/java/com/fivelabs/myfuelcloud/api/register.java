package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.User;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by breogangf on 28/9/15.
 */
public interface register {
    @FormUrlEncoded
    @POST("/user/")
    void register(@Field("username") String username, @Field("email") String email, @Field("password") String password, @Field("created_at") Number created_at, Callback<User> response);
}