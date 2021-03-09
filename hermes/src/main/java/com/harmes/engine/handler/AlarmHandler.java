package com.harmes.engine.handler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.harmes.engine.receiver.LocationAlarmReceiver;
import com.harmes.utils.LocationConstant;

public class AlarmHandler {

    public AlarmHandler(Context mCtx){
        setupLocationAlarm(mCtx);
    }

    private void setupLocationAlarm(Context mCtx){
       AlarmManager alarmManager = (AlarmManager)mCtx.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mCtx, LocationAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mCtx, LocationConstant.LOCATION_PENDING_INTENT_CODE,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        long timeInMillis = System.currentTimeMillis()+60*500;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent);
    }
}
