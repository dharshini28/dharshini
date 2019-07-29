package com.codinginflow.smartalarm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class b extends AppCompatActivity {

    Controllerdb controllerdb = new Controllerdb(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Alarmname = new ArrayList<String>();
    private ArrayList<String> Time = new ArrayList<String>();
    ListView lv;
    Button add;
    Button watch;

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
        Cursor cursor = db.rawQuery("SELECT * FROM  UserDetails",null);
        Alarmname.clear();
        Time.clear();
        if (cursor.moveToFirst()) {
            do {
                Alarmname.add(cursor.getString(cursor.getColumnIndex("Alarmname")));
                Time.add(cursor.getString(cursor.getColumnIndex("Time")));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(b.this,Id,Alarmname,Time);
        //code to set adapter to populate list
        cursor.close();
            }
    }





