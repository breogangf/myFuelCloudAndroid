package com.fivelabs.myfuelcloud.api;

import com.fivelabs.myfuelcloud.model.Refuel;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by breogangf on 28/9/15.
 */
public interface refuel {

    @GET("/refuel/")
    void getRefuels(Callback<List<Refuel>> response);

    @FormUrlEncoded
    @POST("/refuel/")
    void addRefuel(@Field("date") Number date,
                   @Field("gas_price") Number gas_price,
                   @Field("gas_station") String gas_station,
                   @Field("price_amount") Number price_amount,
                   @Field("fuel_amount") Number fuel_amount,
                   @Field("previous_distance") Number previous_distance,
                   @Field("vehicle") String vehicle,
                   Callback<Refuel> response);

    @DELETE("/refuel/{id}")
    void deleteRefuel(@Path("id") String refuelId, Callback<Response> callback);

    @FormUrlEncoded
    @PUT("/refuel/{id}")
    void updateRefuel(@Path("id") String refuelId,
                      @Field("date") Number date,
                      @Field("gas_price") Number gas_price,
                      @Field("gas_station") String gas_station,
                      @Field("price_amount") Number price_amount,
                      @Field("fuel_amount") Number fuel_amount,
                      @Field("previous_distance") Number previous_distance,
                      @Field("userId") String userId,
                      @Field("vehicle") String vehicle,
                      Callback<Refuel> response);
}