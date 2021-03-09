package com.location.codex.config;

import android.app.Application;
import android.content.Context;

public class AppConfig extends Application {

    private static Context mCtx;
    @Override
    public void onCreate() {
        super.onCreate();
        mCtx = getApplicationContext();
    }

    public static Context getAppContext(){
        return mCtx;
    }
}
