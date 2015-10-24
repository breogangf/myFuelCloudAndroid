package com.fivelabs.myfuelcloud.util;

import android.util.Base64;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Long getCurrentTimestamp(){
        return System.currentTimeMillis();
    }

    public static String formatDateTime(String dateTime){

        SimpleDateFormat formatter, FORMATTER;
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = formatter.parse(dateTime.substring(0, 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FORMATTER = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return FORMATTER.format(date);

    }
}
