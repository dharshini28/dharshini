package com.codinginflow.smartalarm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class b extends AppCompatActivity {
    ListView l;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        l=(ListView)findViewById(R.id.l1);
        add=( Button )findViewById(R.id.b1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent i =new Intent(b.this,MainActivity.class);
             startActivity(i);
            }
        });
    }
}


