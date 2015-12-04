package com.fivelabs.myfuelcloud.util;

import android.util.Base64;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
        return (System.currentTimeMillis() + TimeZone.getDefault().getRawOffset()/1000);
    }

    public static String getDateTimeTextFromTimestamp(Long timestamp){
        Timestamp stamp = new Timestamp(timestamp);
        Date date = new Date(stamp.getTime());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd MMM yyyy HH:mm");
        s.setTimeZone(cal.getTimeZone());
        return s.format(date);
    }
}
