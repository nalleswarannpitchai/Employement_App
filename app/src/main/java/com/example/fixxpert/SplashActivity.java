package com.example.fixxpert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        this is for hiding the action bar
        getSupportActionBar().hide();

        // using the thread to delay the mein thread so that the splash screen activity can be delayed for required seconds
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
//                    1000 is equal to one second
                        sleep(4000);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashActivity.this,Login_Activity.class);
                    startActivity(intent);
                }
            }
        };thread.start();

    }
}