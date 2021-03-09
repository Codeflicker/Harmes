package com.harmes.engine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.harmes.engine.handler.AlarmHandler;
import com.harmes.engine.location.LocationEngine;
import com.harmes.utils.Utils;

public class LocationAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "from alrm", Toast.LENGTH_SHORT).show();
        float batteryStatus = Utils.getBatteryStatus(intent);
        LocationEngine engine = new LocationEngine();
        engine.startEngineWithContext(context);

        if(LocationEngine.isGPSOn(context)){
            System.out.println("from alarm: GPS IS ON : "+batteryStatus);
        }else {
            System.out.println("from alarm: GPS IS OFF :"+batteryStatus);
        }
        new AlarmHandler(context);
    }
}
