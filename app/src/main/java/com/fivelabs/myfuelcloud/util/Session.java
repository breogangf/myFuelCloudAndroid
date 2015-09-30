package com.fivelabs.myfuelcloud.util;

import com.fivelabs.myfuelcloud.model.User;

/**
 * Created by breogangf on 30/9/15.
 */
public class Session {

    public static User sUser;

    public static User getsUser() {
        return sUser;
    }

    public static void setsUser(User sUser) {
        Session.sUser = sUser;
    }
}
