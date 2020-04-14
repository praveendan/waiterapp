package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private String serverName;
    private boolean isIpSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        isIpSet= intent.getExtras().getBoolean("isIpSet");
        serverName = intent.getStringExtra("ipAddress");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String userId = getIntent().getStringExtra("USER_ID");
        if(isIpSet && userId == null){
            Bundle bundle = new Bundle();
            bundle.putString("serverName", serverName);
            LoginFragment fragobj = new LoginFragment();
            fragobj.setArguments(bundle);

            fragmentTransaction.add(R.id.login_layout, fragobj);
        }
        else
            fragmentTransaction.add(R.id.login_layout, new RegisterFragment());
        fragmentTransaction.commit();
    }
}
