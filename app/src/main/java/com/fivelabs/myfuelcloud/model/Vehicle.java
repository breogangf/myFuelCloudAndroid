package com.fivelabs.myfuelcloud.model;

/**
 * Created by breogangf on 16/10/15.
 */
public class Vehicle {

    String brand;
    String model;
    int year;
    String created_at;
    String userId;

    public Vehicle(String brand, String model, int year, String created_at, String userId) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.created_at = created_at;
        this.userId = userId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
