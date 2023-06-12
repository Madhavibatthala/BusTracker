package com.example.bustracker1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences=getSharedPreferences("SHARED_PREFERENCES",MODE_PRIVATE);
        uid=sharedPreferences.getString("UID","");

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if(uid.isEmpty())
                        {
                            startActivity(new Intent(SplashActivity.this,MainActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(SplashActivity.this,AfterLogin.class));
                            finish();
                        }
                    }
                },3000);
    }
}
