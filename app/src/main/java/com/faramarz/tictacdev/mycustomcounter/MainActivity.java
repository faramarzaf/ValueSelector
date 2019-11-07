package com.faramarz.tictacdev.mycustomcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ValueSelector valueSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        valueSelector = new ValueSelector(this);
        valueSelector = findViewById(R.id.value_selector);
        valueSelector.setMinValue(0);
        valueSelector.setMaxValue(10);


    }


}
