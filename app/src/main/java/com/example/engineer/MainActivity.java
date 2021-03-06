//activity to run main game with view from TakeCareView
package com.example.engineer;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private TakeCareView gameView;
    private static final String TAG = "MainActivity";

    private Handler handler = new Handler();
    private final static long Interval = 30;


    private TakeCare engineer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        engineer = (TakeCare)getIntent().getSerializableExtra("Engineer");

        gameView = new TakeCareView(this, engineer);

        setContentView(gameView);

        //creates interval to continuously update the screen view
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });

            }}, 0, Interval);
    }
}

