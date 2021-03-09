package com.harmes.utils;

import android.content.Intent;
import android.os.BatteryManager;

public class Utils {


    public static float getBatteryStatus(Intent intent){
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return level * 100 / (float)scale;
    }
}
