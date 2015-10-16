package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.Vehicle;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by breogangf on 28/9/15.
 */
public interface vehicle {
    @FormUrlEncoded
    @POST("/vehicle/")
    void addVehicle(@Field("brand") String brand, @Field("model") String model, @Field("year") Number year, @Field("created_at") Number created_at, @Field("created_by") String created_by, Callback<Vehicle> response);

    @DELETE("/vehicle/{id}")
    void deleteVehicle(@Path("id") String vehicleId, Callback<Response> callback);

    @FormUrlEncoded
    @PUT("/vehicle/{id}")
    void updateVehicle(@Path("id") String vehicleId, @Field("brand") String brand, @Field("model") String model, @Field("year") Number year, @Field("created_at") Number created_at, @Field("created_by") String created_by, Callback<Vehicle> response);

}