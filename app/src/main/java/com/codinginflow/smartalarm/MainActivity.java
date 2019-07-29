package com.codinginflow.smartalarm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AlarmManager alarmManager;
    private PendingIntent pending_intent;
    private TimePicker alarmTimePicker;
    TextView alarmTextView,Time;
    EditText Alarmname;
    MainActivity inst;
    Context context;
    CheckBox c1;
    CheckBox c2;
    CheckBox c3;
    CheckBox c4;
    CheckBox c5;
    CheckBox c6;
    CheckBox c11;
    CheckBox c12;
    Controllerdb db =new Controllerdb(this);
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        alarmTextView = (TextView) findViewById(R.id.tv1);
        Time= (TextView) findViewById(R.id.t);
        Alarmname = (EditText) findViewById(R.id.et1);
        final Intent myIntent = new Intent(this.context,AlarmReceiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        alarmTimePicker = (TimePicker) findViewById(R.id.tp);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit = prefs.edit();
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit1 = prefs.edit();
        c1 = (CheckBox) findViewById(R.id.c1);
        c2 = (CheckBox) findViewById(R.id.c2);
        c3 = (CheckBox) findViewById(R.id.c3);
        c4 = (CheckBox) findViewById(R.id.c4);
        c5 = (CheckBox) findViewById(R.id.c5);
        c6 = (CheckBox) findViewById(R.id.c6);

        Button start_alarm = (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND,3);
                //setAlarmText("You clicked a button");

                final int hour = alarmTimePicker.getCurrentHour();
                final int minute = alarmTimePicker.getCurrentMinute();

                Log.e("MyActivity","In the receiver with " + hour + " and " + minute);
                setAlarmText("You clicked a " + hour + " and " + minute);


                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,alarmTimePicker.getCurrentMinute());

                myIntent.putExtra("extra","yes");
                pending_intent = PendingIntent.getBroadcast(MainActivity.this,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);


                // now you should change the set Alarm text so it says something nice
                checkbox();

                setAlarmText("Alarm set to " + hour + ":" + minute);
                Time.setText(hour + ":" + minute);

                //Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
                database = db.getWritableDatabase();
                if( Time != null ) {
                    database.execSQL("INSERT INTO UserDetails(Alarmname,Time)VALUES('" + Alarmname.getText() + "','" + Time.getText() + "')");
                    Intent k = new Intent(MainActivity.this,b.class);
                    startActivity(k);
                }

            }

            private void checkbox() {

                if( ( c1.isChecked() ) ){
                    int c = 1;
                    edit.putInt("MID",c);
                    edit.commit();
                }

                if( ( c2.isChecked() ) ){
                    int c =2;
                    edit.putInt("MID",c);
                    edit.commit();
                }

                if( ( c3.isChecked() ) ){
                            int c = 3;
                            edit.putInt("MID",c);
                            edit.commit();
                        }
                if( ( c4.isChecked() ) ){
                    int c = 3;
                    edit.putInt("MID",c);
                    edit.commit();
                }

                if( ( c5.isChecked() ) ){
                    int c = 3;
                    edit.putInt("MID",c);
                    edit.commit();
                }

                if( ( c6.isChecked() ) ){
                    int c = 3;
                    edit.putInt("MID",c);
                    edit.commit();
                }
            }
        });


        Button stop_alarm = (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent.putExtra("extra","no");
                sendBroadcast(myIntent);
                alarmManager.cancel(pending_intent);
                setAlarmText("Alarm canceled");
                //setAlarmText("You clicked a " + " canceled");
            }
        });
        }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }


    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }


    @Override
    public void onClick(View v) {
        }
    }
