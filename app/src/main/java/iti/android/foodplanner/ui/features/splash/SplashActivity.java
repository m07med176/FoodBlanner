package iti.android.foodplanner.ui.features.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import iti.android.foodplanner.R;
import iti.android.foodplanner.SignUpOrLoginActivity;



public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        new Thread(() -> {
            try {
                Thread.sleep(4000);
                startActivity(new Intent(getApplicationContext(), SignUpOrLoginActivity.class));
                finish();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}