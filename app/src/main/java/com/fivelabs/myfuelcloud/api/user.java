package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by breogangf on 28/9/15.
 */
public interface user {
    @GET("/user/username/{username}")
    void getUser(@Path("username") String user, Callback<User> response);
}