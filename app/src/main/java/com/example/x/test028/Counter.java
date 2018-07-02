package com.example.x.test028;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Counter {

    private static final int START_VALUE = 21;

    public static final String APP_PREFS = "AppPrefs";
    public static final String KEY_COUNT = "key-count";


    public static void start(Context context) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putInt(KEY_COUNT, START_VALUE);
        editor.apply();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1001, new Intent(context, IncrementJob.class), PendingIntent.FLAG_CANCEL_CURRENT);


        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 2000, 2000, pendingIntent);


    }


}
