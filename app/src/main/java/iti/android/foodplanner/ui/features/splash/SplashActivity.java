package iti.android.foodplanner.ui.features.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import iti.android.foodplanner.MainActivity;
import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignUpOrLoginActivity;



public class SplashActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
    public void reload() {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}