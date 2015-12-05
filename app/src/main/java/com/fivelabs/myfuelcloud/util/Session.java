package com.fivelabs.myfuelcloud.util;

import com.fivelabs.myfuelcloud.model.Refuel;
import com.fivelabs.myfuelcloud.model.Statistic;
import com.fivelabs.myfuelcloud.model.User;
import com.fivelabs.myfuelcloud.model.Vehicle;

import java.util.List;

/**
 * Created by breogangf on 30/9/15.
 */
public class Session {

    public static User sUser;
    public static List<Vehicle> sVehicles;
    public static List<Refuel> sRefuels;
    public static Statistic sStatistic;

    public static Statistic getsStatistic() {
        return sStatistic;
    }

    public static void setsStatistic(Statistic sStatistic) {
        Session.sStatistic = sStatistic;
    }

    public static User getsUser() {
        return sUser;
    }

    public static void setsUser(User sUser) {
        Session.sUser = sUser;
    }

    public static List<Vehicle> getsVehicles() {
        return sVehicles;
    }

    public static void setsVehicles(List<Vehicle> sVehicles) {
        Session.sVehicles = sVehicles;
    }

    public static List<Refuel> getsRefuels() {
        return sRefuels;
    }

    public static void setsRefuels(List<Refuel> sRefuels) {
        Session.sRefuels = sRefuels;
    }
}
