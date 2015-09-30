package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by breogangf on 28/9/15.
 */
public interface user {
    @GET("/user/username/{username}")      //here is the other url part.best way is to start using /
    public void getUser(@Path("username") String user,Callback<User> response);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}