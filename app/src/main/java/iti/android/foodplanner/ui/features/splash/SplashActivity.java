package iti.android.foodplanner.ui.features.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import iti.android.foodplanner.ui.features.main.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.onBoarding.OnBoardingActivity;


public class SplashActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {
            try {
                Thread.sleep(4000);
                startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                finish();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public void reload() {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}