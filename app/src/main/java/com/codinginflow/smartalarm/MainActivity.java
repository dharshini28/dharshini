package com.codinginflow.smartalarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AlarmManager alarmManager;
    private PendingIntent pending_intent;
    private TimePicker alarmTimePicker;
    TextView alarmTextView,time;
    EditText alarmname;
    MainActivity inst;
    Context context;
    RadioButton c1;
    RadioButton c2;
    RadioButton c3;
    RadioButton c4;
    RadioButton c5;
    RadioButton c6,radiobutton;
    RadioGroup radioGroup;

    Controllerdb db =new Controllerdb(this);
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        alarmTextView = (TextView) findViewById(R.id.tv1);
        time= (TextView) findViewById(R.id.t);
        alarmname = (EditText) findViewById(R.id.et1);
        final Intent myIntent = new Intent(this.context,AlarmReceiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        alarmTimePicker = (TimePicker) findViewById(R.id.tp);
        alarmTimePicker.setIs24HourView(true);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit = prefs.edit();
        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit1 = prefs.edit();
        c1 = (RadioButton) findViewById(R.id.radioButton);
        c2 = (RadioButton) findViewById(R.id.radioButton2);
        c3 = (RadioButton) findViewById(R.id.radioButton3);
        c4 = (RadioButton) findViewById(R.id.radioButton4);
        c5 = (RadioButton) findViewById(R.id.radioButton5);
        c6 = (RadioButton) findViewById(R.id.radioButton6);
        radioGroup =(RadioGroup)findViewById(R.id.radioGroup);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            String name = intent.getExtras().getString("name","l");
            String tim = intent.getExtras().getString("time","0:0");
            alarmname.setText(name);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            Date date = null;
            try {
                date = sdf.parse(tim);
            } catch (ParseException e) {
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            alarmTimePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
            alarmTimePicker.setCurrentMinute(c.get(Calendar.MINUTE));
        }

        Button start_alarm = (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND,3);

                final int hour = alarmTimePicker.getCurrentHour();
                final int minute = alarmTimePicker.getCurrentMinute();
                setAlarmText("You clicked a " + hour + " and " + minute);

                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,alarmTimePicker.getCurrentMinute());

                myIntent.putExtra("extra","yes");
                pending_intent = PendingIntent.getBroadcast(MainActivity.this,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);
                int selected = radioGroup.getCheckedRadioButtonId();
                radiobutton = (RadioButton)findViewById(selected);
                edit.putString("c",radiobutton.getText().toString());
                edit.commit();

                setAlarmText("Alarm set to " + hour + ":" + minute);
                time.setText(hour + ":" + minute);

                database = db.getWritableDatabase();
                if( time != null ) {
                    database.execSQL("INSERT INTO UserDetails(alarmname,time)VALUES('" + alarmname.getText() + "','" + time.getText() + "')");
                    Intent k = new Intent(MainActivity.this,b.class);
                    startActivity(k);
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
    }

    @Override
    public void onClick(View v) {
        }
    }
