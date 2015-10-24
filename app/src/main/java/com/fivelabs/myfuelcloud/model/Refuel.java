package com.fivelabs.myfuelcloud.model;

/**
 * Created by breogangf on 16/10/15.
 */
public class Refuel {

    String _id;
    String date;
    Double gas_price;
    String gas_station;
    Double price_amount;
    Double fuel_amount;
    Double previous_distance;
    String userId;
    Vehicle vehicle;

    public Refuel(String _id, String date, Double gas_price, String gas_station, Double price_amount, Double fuel_amount, Double previous_distance, String userId, Vehicle vehicle) {
        this._id = _id;
        this.date = date;
        this.gas_price = gas_price;
        this.gas_station = gas_station;
        this.price_amount = price_amount;
        this.fuel_amount = fuel_amount;
        this.previous_distance = previous_distance;
        this.userId = userId;
        this.vehicle = vehicle;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getGas_price() {
        return gas_price;
    }

    public void setGas_price(Double gas_price) {
        this.gas_price = gas_price;
    }

    public String getGas_station() {
        return gas_station;
    }

    public void setGas_station(String gas_station) {
        this.gas_station = gas_station;
    }

    public Double getPrice_amount() {
        return price_amount;
    }

    public void setPrice_amount(Double price_amount) {
        this.price_amount = price_amount;
    }

    public Double getFuel_amount() {
        return fuel_amount;
    }

    public void setFuel_amount(Double fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public Double getPrevious_distance() {
        return previous_distance;
    }

    public void setPrevious_distance(Double previous_distance) {
        this.previous_distance = previous_distance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Refuel refuel = (Refuel) o;

        if (_id != null ? !_id.equals(refuel._id) : refuel._id != null) return false;
        if (date != null ? !date.equals(refuel.date) : refuel.date != null) return false;
        if (gas_price != null ? !gas_price.equals(refuel.gas_price) : refuel.gas_price != null)
            return false;
        if (gas_station != null ? !gas_station.equals(refuel.gas_station) : refuel.gas_station != null)
            return false;
        if (price_amount != null ? !price_amount.equals(refuel.price_amount) : refuel.price_amount != null)
            return false;
        if (fuel_amount != null ? !fuel_amount.equals(refuel.fuel_amount) : refuel.fuel_amount != null)
            return false;
        if (previous_distance != null ? !previous_distance.equals(refuel.previous_distance) : refuel.previous_distance != null)
            return false;
        if (userId != null ? !userId.equals(refuel.userId) : refuel.userId != null) return false;
        return !(vehicle != null ? !vehicle.equals(refuel.vehicle) : refuel.vehicle != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (gas_price != null ? gas_price.hashCode() : 0);
        result = 31 * result + (gas_station != null ? gas_station.hashCode() : 0);
        result = 31 * result + (price_amount != null ? price_amount.hashCode() : 0);
        result = 31 * result + (fuel_amount != null ? fuel_amount.hashCode() : 0);
        result = 31 * result + (previous_distance != null ? previous_distance.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Refuel{" +
                "_id='" + _id + '\'' +
                ", date='" + date + '\'' +
                ", gas_price=" + gas_price +
                ", gas_station='" + gas_station + '\'' +
                ", price_amount=" + price_amount +
                ", fuel_amount=" + fuel_amount +
                ", previous_distance=" + previous_distance +
                ", userId='" + userId + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
