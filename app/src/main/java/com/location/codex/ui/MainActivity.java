package com.location.codex.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.harmes.engine.location.LocationEngine;
import com.location.codex.R;

public class MainActivity extends AppCompatActivity {

    private LocationEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        engine = new LocationEngine(this,101);
        engine.startEngine();
    }

    @Override
    protected void onStop() {
        super.onStop();
        engine.stopEngine();
    }
}