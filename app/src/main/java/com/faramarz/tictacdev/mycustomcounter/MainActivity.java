package com.faramarz.tictacdev.mycustomcounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.faraaf.tictacdev.valueselector.ValueSelector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button get;
    ValueSelector valueSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        init();
    }

    private void bind() {
        get = findViewById(R.id.get);
        valueSelector = findViewById(R.id.val);
        get.setOnClickListener(this);
    }

    private void init() {
        
    }


    @Override
    public void onClick(View v) {
    }

}