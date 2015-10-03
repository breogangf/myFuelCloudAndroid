package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by breogangf on 28/9/15.
 */
public interface login {
    @GET("/user/")
    void login(Callback<List<User>> response);
}
