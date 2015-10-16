package com.fivelabs.myfuelcloud.model;

/**
 * Created by breogangf on 16/10/15.
 */
public class Vehicle {

    String brand;
    String model;
    int year;
    int created_at;
    String userId;

    public Vehicle(String brand, String model, int year, int created_at, String userId) {
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

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (year != vehicle.year) return false;
        if (created_at != vehicle.created_at) return false;
        if (brand != null ? !brand.equals(vehicle.brand) : vehicle.brand != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        return !(userId != null ? !userId.equals(vehicle.userId) : vehicle.userId != null);

    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + created_at;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", created_at=" + created_at +
                ", userId='" + userId + '\'' +
                '}';
    }

}
