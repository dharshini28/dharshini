package com.codinginflow.smartalarm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class b extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Controllerdb controllerdb = new Controllerdb(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> alarmname = new ArrayList<String>();
    private ArrayList<String> time = new ArrayList<String>();
    ListView lv;
    Button add;
    Button watch;
    CustomAdapter ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        lv = (ListView) findViewById(R.id.l1);
        add = (Button) findViewById(R.id.b1);
        watch = (Button) findViewById(R.id.b2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(b.this,MainActivity.class);
                startActivity(i);
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(b.this,Stopwatch.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }
    private void displayData() {
        db = controllerdb.getReadableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM  UserDetails",null);
        Id.clear();
        alarmname.clear();
        time.clear();
        if( cursor != null && cursor.getCount() > 0 ) {
            if( cursor.moveToFirst() ) {
                do {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    alarmname.add(cursor.getString(cursor.getColumnIndex("alarmname")));
                    time.add(cursor.getString(cursor.getColumnIndex("time")));
                } while (cursor.moveToNext());
            }
            ca = new CustomAdapter(b.this,Id,alarmname,time);
            lv.setAdapter(ca);
            lv.setOnItemClickListener (this);
            cursor.close();
        }
    }
           boolean twice;
            @Override
    public  void onBackPressed(){
                if(twice==true){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    System.exit(0);
                }
        Toast.makeText(b.this,"Please press BACK again to exit",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        },3000);
        twice = true;
            }

    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
        TextView textView = (TextView) view.findViewById(R.id.alarmname);
        String name = textView.getText().toString();
        TextView textVie = (TextView) view.findViewById(R.id.time);
        String time = textVie.getText().toString();
        Toast.makeText ( b.this, "" + time, Toast.LENGTH_SHORT ).show ();
      Intent intent = new Intent(b.this,MainActivity.class);
      intent.putExtra("time",time);
      intent.putExtra("name",name);
      startActivity(intent);}
}





