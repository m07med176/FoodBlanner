package iti.android.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class SplachScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}