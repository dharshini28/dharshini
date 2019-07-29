package com.codinginflow.smartalarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

import static android.support.v4.app.NotificationCompat.PRIORITY_MIN;

public class Wakeupscreen extends Service {
        private boolean isRunning;
        private Context context;
        MediaPlayer mMediaPlayer;
        private int startId;

        @Nullable
        @Override
        public IBinder onBind ( Intent intent){
            return null;
        }
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public int onStartCommand ( Intent intent,int flags, int startId)
        {
            String CHANNEL_ID = "my_service";
            String CHANNEL_NAME = "My Background Service";
            SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
            final int n = prefs.getInt("m",1);
            NotificationChannel channel = null;
            if( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ) {
                channel = new NotificationChannel(CHANNEL_ID,
                        CHANNEL_NAME,NotificationManager.IMPORTANCE_NONE);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setCategory(Notification.CATEGORY_SERVICE).setSmallIcon(R.drawable.ic_action_call).setPriority(PRIORITY_MIN).build();
            startForeground(101, notification);}

            final NotificationManager mNM = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this,0,intent1,0);

            Notification mNotify = new Notification.Builder(this)
                    .setContentTitle("Richard Dawkins is talking" + "!")
                    .setContentText("Click me!")
                    .setSmallIcon(R.drawable.ic_action_call)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true)
                    .build();

            String state = intent.getExtras().getString("extra");

            Log.e("what is going on here  ",state);

            assert state != null;
            switch (state) {
                case "no":
                    startId = 0;
                    break;
                case "yes":
                    startId = 1;
                    break;
                default:
                    startId = 0;
                    break;
            }


            if( !this.isRunning && startId == 1 ) {
               Intent i = new Intent(Wakeupscreen.this,Level1.class);
                startActivity(i);
                mNM.notify(0,mNotify);

                this.isRunning = true;
                this.startId = 0;

            }
            else if( !this.isRunning && startId == 0 ) {
                Log.e("if there was not sound "," and you want end");

                this.isRunning = false;
                this.startId = 0;

            }
            else if( this.isRunning && startId == 1 ) {
                Log.e("if there is sound "," and you want start");

                this.isRunning = true;
                this.startId = 0;

            }
            else {
                Log.e("if there is sound "," and you want end");
                this.isRunning = false;
                this.startId = 0;
            }


            Log.e("MyActivity","In the service");

            return START_NOT_STICKY;

        }


        @Override
        public void onDestroy ( ) {
            Log.e("JSLog","on destroy called");
            super.onDestroy();

            this.isRunning = false;
        }


    }
