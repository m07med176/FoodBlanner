package iti.android.foodplanner.ui.features.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    SplashPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new SplashPresenter(this);
        getSupportActionBar().hide();


        new Thread(() -> {
            try {
                Thread.sleep(1000);
                // TODO Check From Shared Preference Manager Class
                //  if first entrance of user to navigate to on Boarding

                // TODO Check Authentication of User
                //  if null navigate to login else go ahead
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}