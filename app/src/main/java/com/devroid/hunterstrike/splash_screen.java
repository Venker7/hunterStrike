package com.devroid.hunterstrike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {
    private static int SPLASHSCREEN=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        setTitle(" ");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(loginPage.PREFS_NAME,0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
                if(hasLoggedIn)
                {
                    Intent intent = new Intent(splash_screen.this,Registration.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(splash_screen.this,loginPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASHSCREEN);
    }
}