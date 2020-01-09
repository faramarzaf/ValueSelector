package com.faraaf.tictacdev.valueselector;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


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
        //valueSelector.setCustomFont(this, "faryekan.ttf");

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "" + valueSelector.getValue(), Toast.LENGTH_SHORT).show();
    }

}