package com.codinginflow.smartalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import java.util.Objects;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context,Intent intent) {
        String state = null;
        if( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT ) {
            state = Objects.requireNonNull(intent.getExtras()).getString("extra");
        }
        Log.e("MyActivity","In the receiver with " + state);

        Intent serviceIntent = new Intent(context,Wakeupscreen.class);
        serviceIntent.putExtra("extra",state);
        Vibrator vib = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(2000);
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            context.startForegroundService(serviceIntent);
        }
        else {
            context.startService(serviceIntent);
        }
    }
}