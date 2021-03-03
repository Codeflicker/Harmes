package com.harmes.codex.config;

import android.app.Application;
import android.content.Context;

public class Config extends Application {

    private static Context mCtx;
    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize(){
        mCtx = getApplicationContext();
    }

    public Context getAppContext(){
        return mCtx;
    }
}
