package com.codinginflow.smartalarm;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends AppCompatActivity {
    Button snoo;
    MediaPlayer mp1,mp2,mp3,mp4,mp5,mp6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakeup);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final int name = bundle.getInt("Ringtone");
        snoo=(Button)findViewById(R.id.b);
        mp6=MediaPlayer.create(this,R.raw.wakeup);
        mp1=MediaPlayer.create(this, R.raw.beautiful_morning_alarm_ringtone);
        mp2=MediaPlayer.create(this, R.raw.like_the_devil);
        mp3=MediaPlayer.create(this, R.raw.loneliness);
        mp4=MediaPlayer.create(this, R.raw.cuckoo_clock);
        mp5=MediaPlayer.create(this, R.raw.jump_start);
        if( name==6 ){
            mp6.start();
            snoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp6.stop();
                }
            });
        }
        if( name==1 ){
            mp1.start();
            snoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp1.stop();
                }
            });
        }
        if( name==2 ){
            mp2.start();
            snoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp2.stop();
                }
            });
        }
        if( name==3 ){
            mp3.start();
            snoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp3.stop();
                }
            });
        }

        if( name==4 ){
            mp4.start();
            snoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp4.stop();
                }
            });
        }
        if( name==5 ){
            mp5.start();
            snoo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mp5.stop();
                }
            });
        }


    }
}


