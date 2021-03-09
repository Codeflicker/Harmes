package com.location.codex.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.harmes.engine.handler.AlarmHandler;
import com.harmes.engine.location.LocationEngine;
import com.location.codex.R;
import com.location.codex.config.AppConfig;

public class MainActivity extends AppCompatActivity {

    private LocationEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //engine = new LocationEngine(this,101);
       // engine.startEngine();

        new AlarmHandler(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
     //   engine.stopEngine();
    }
}