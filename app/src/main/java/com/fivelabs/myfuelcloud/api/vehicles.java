package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.Vehicle;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by breogangf on 28/9/15.
 */
public interface vehicles {
    @GET("/vehicle/")
    void getVehicles(Callback<List<Vehicle>> response);
}