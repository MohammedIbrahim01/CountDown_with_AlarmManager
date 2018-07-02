package com.example.x.test028;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class IncrementJob extends BroadcastReceiver {

    public static final String APP_PREFS = "AppPrefs";
    public static final String KEY_COUNT = "key-count";
    private static final int FINAL_VALUE = 0;
    private static final int START_VALUE = 21;

    @Override
    public void onReceive(Context context, Intent intent) {

        //get SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //get count and if first time get 0
        int count = sharedPreferences.getInt(KEY_COUNT, START_VALUE);


        editor.putInt(KEY_COUNT, --count);
        editor.apply();


        if (count == FINAL_VALUE) {
            unSchedule_IncrementJob(context);
        }

    }

    private void unSchedule_IncrementJob(Context context) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1001, new Intent(context, IncrementJob.class), PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.cancel(pendingIntent);

    }
}
