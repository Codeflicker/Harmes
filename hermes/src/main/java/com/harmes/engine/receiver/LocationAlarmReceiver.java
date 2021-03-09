package com.harmes.engine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.harmes.engine.handler.AlarmHandler;

public class LocationAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("from alarm: ");
        Toast.makeText(context, "from alrm", Toast.LENGTH_SHORT).show();
        new AlarmHandler(context);
    }
}
