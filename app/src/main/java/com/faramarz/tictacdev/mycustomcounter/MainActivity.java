package com.faramarz.tictacdev.mycustomcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ValueSelector valueSelector;
    Button get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueSelector = new ValueSelector(this);
        valueSelector = findViewById(R.id.value_selector);
        get = findViewById(R.id.get);
        get.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, ""+valueSelector.getValue(), Toast.LENGTH_SHORT).show();
    }
    
}