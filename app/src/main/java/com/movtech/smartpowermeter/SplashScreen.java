package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private int splashtime = 1500;
    SharedPreferences sharedPreferences;
    boolean sudahLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        sudahLogin = sharedPreferences.getBoolean("sudahLogin", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (sudahLogin) {
                    i = new Intent(SplashScreen.this, MainActivity.class);
                } else {
                    i = new Intent(SplashScreen.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, splashtime);
    }
}