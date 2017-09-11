package com.example.aff02.expansemanager.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aff02.expansemanager.R;

public class SplashScreen extends AppCompatActivity {

    private ImageView logoWelcome;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().setTitle("Welcome");

        logoWelcome = (ImageView)findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, RecycleActivity.class);
                startActivity(i);

                finish();
            }
            }, SPLASH_TIME_OUT);
    }
}
