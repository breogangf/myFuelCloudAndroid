package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.User;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by breogangf on 28/9/15.
 */
public interface email {
    @FormUrlEncoded
    @POST("/email/")
    void sendEmail(@Field("name") String name, @Field("subject") String subject, @Field("from") String from, @Field("to") String to, Callback<User> response);
}