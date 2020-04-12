package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    Handler handler;
    private boolean isIpSet = false;
    private String ip = "some-ip";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                intent.putExtra("isIpSet", isIpSet);
                intent.putExtra("ipAddress", ip);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
