package com.codinginflow.smartalarm;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class Stopwatch extends AppCompatActivity {
 Button alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        alarm=(Button)findViewById(R.id.b2) ;
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Stopwatch.this,b.class);
                startActivity(i);
            }
        });
        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometerExample);

        Button buttonStart = (Button)findViewById(R.id.buttonStartChronometer);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.start();
            }
        });

        Button buttonStop = (Button)findViewById(R.id.buttonStopChronometer);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
            }
        });

        Button buttonRestart = (Button)findViewById(R.id.buttonRestartChronometer);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long systemCurrTime = SystemClock.elapsedRealtime();
                chronometer.setBase(systemCurrTime);
            }
        });

        Button buttonClearChronometerFormat = (Button)findViewById(R.id.buttonClearChronometerFormat);
        buttonClearChronometerFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                    int counter = 10;
                    @Override
                    public void onChronometerTick(Chronometer chronometer) {
                        if(counter < 0)
                        {
                            counter = 10;
                        }
                        chronometer.setText(counter + "");
                        counter--;
                    }
                });
            }
        });
    }

}
