package com.codinginflow.smartalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Level1 extends Activity{

    List<Question> quesList;
    static int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    TextView res;
    static TextView textView;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext;
    MediaPlayer mp1,mp2,mp3,mp4,mp5,mp6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        final int n = prefs.getInt("MID",0);
        mp1=MediaPlayer.create(this, R.raw.beautiful_morning_alarm_ringtone);
        mp2=MediaPlayer.create(this, R.raw.like_the_devil);
        mp3=MediaPlayer.create(this, R.raw.loneliness);
        mp4=MediaPlayer.create(this, R.raw.cuckoo_clock);
        mp5=MediaPlayer.create(this, R.raw.jump_start);
        mp6=MediaPlayer.create(this, R.raw.wakeup);
        if(n==1){mp1.start();}
        if(n==2){mp2.start();}
        if(n==3){mp3.start();}
        if(n==4){mp4.start();}
        if(n==5){mp5.start();}
        if(n==6){mp6.start();}
        DatabaseCreate db = new DatabaseCreate(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView2);
        rda = (RadioButton) findViewById(R.id.radioButton);
        rdb = (RadioButton) findViewById(R.id.radioButton2);
        rdc = (RadioButton) findViewById(R.id.radioButton3);
        rdd = (RadioButton) findViewById(R.id.radioButton4);
        butNext = (Button) findViewById(R.id.next);
        setQuestionView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                try {
                    if (currentQ.getANSWER().equals(answer.getText())) {
                        if(n==1){mp1.stop();}
                        if(n==2){mp2.stop();}
                        if(n==3){mp3.stop();}
                        if(n==4){mp4.stop();}
                        if(n==5){mp5.stop();}
                        if(n==6){mp6.stop();}
                        return;
                    }

                    if (qid < 40) {
                        currentQ = quesList.get(qid);
                        setQuestionView();
                    }
                    else{
                        Bundle b=new Bundle();
                        Intent i1=new Intent(Level1.this, Level1.class);
                        startActivity(i1);
                    }
                } catch (Exception e) {
                    Toast.makeText(Level1.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(qid==40){
            qid=0;
        }
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        rdd.setText(currentQ.getOPTD());
        qid++;
    }
}