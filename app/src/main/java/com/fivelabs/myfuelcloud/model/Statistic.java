package com.fivelabs.myfuelcloud.model;

/**
 * Created by breogangf on 16/10/15.
 */
public class Statistic {

    Double price_amount_average;
    Double fuel_amount_average;
    Double fuel_consumption_average;
    Double cost_per_kilometer;
    Double previous_distance_average;
    Double gas_price_average;

    public Statistic(Double price_amount_average, Double fuel_amount_average, Double fuel_consumption_average, Double cost_per_kilometer, Double previous_distance_average, Double gas_price_average) {
        this.price_amount_average = price_amount_average;
        this.fuel_amount_average = fuel_amount_average;
        this.fuel_consumption_average = fuel_consumption_average;
        this.cost_per_kilometer = cost_per_kilometer;
        this.previous_distance_average = previous_distance_average;
        this.gas_price_average = gas_price_average;
    }

    public Double getPrice_amount_average() {
        return price_amount_average;
    }

    public void setPrice_amount_average(Double price_amount_average) {
        this.price_amount_average = price_amount_average;
    }

    public Double getFuel_amount_average() {
        return fuel_amount_average;
    }

    public void setFuel_amount_average(Double fuel_amount_average) {
        this.fuel_amount_average = fuel_amount_average;
    }

    public Double getFuel_consumption_average() {
        return fuel_consumption_average;
    }

    public void setFuel_consumption_average(Double fuel_consumption_average) {
        this.fuel_consumption_average = fuel_consumption_average;
    }

    public Double getCost_per_kilometer() {
        return cost_per_kilometer;
    }

    public void setCost_per_kilometer(Double cost_per_kilometer) {
        this.cost_per_kilometer = cost_per_kilometer;
    }

    public Double getPrevious_distance_average() {
        return previous_distance_average;
    }

    public void setPrevious_distance_average(Double previous_distance_average) {
        this.previous_distance_average = previous_distance_average;
    }

    public Double getGas_price_average() {
        return gas_price_average;
    }

    public void setGas_price_average(Double gas_price_average) {
        this.gas_price_average = gas_price_average;
    }
}
