package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.Statistic;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by breogangf on 28/9/15.
 */
public interface statistic {

    @GET("/statistics/")
    void getStatistics(Callback<Statistic> response);
}