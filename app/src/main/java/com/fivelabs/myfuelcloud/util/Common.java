package com.fivelabs.myfuelcloud.util;

import android.util.Base64;

/**
 * Created by breogangf on 28/9/15.
 */
public class Common {

    public static String generateToken(String username, String password){
        String credentials = username + ":" + password;
        String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        return "Basic " + base64EncodedCredentials;
    }

    public static String capitalizeWord(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
