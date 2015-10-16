package com.fivelabs.myfuelcloud.util;

import com.fivelabs.myfuelcloud.model.User;
import com.fivelabs.myfuelcloud.model.Vehicle;

import java.util.List;

/**
 * Created by breogangf on 30/9/15.
 */
public class Session {

    public static User sUser;
    public static List<Vehicle> sVehicles;

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
}
