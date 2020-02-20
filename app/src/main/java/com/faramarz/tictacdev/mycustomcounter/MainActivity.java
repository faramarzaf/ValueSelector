package com.faramarz.tictacdev.mycustomcounter;

import android.graphics.Color;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
>>>>>>> develop

import androidx.appcompat.app.AppCompatActivity;


<<<<<<< HEAD
public class MainActivity extends AppCompatActivity {
=======
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ValueSelector valueSelector, value_selector2;
    Button get;
>>>>>>> develop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

    }

=======
        valueSelector = findViewById(R.id.value_selector);
        value_selector2 = findViewById(R.id.value_selector2);
        get = findViewById(R.id.get);
        get.setOnClickListener(this);


        valueSelector.setValueTextColor(getResources().getColor(R.color.colorAccent));
        valueSelector.setMinusIconColor(getResources().getColor(R.color.colorAccent));
        valueSelector.setPlusIconColor(getResources().getColor(R.color.colorAccent));
        valueSelector.setPlusIconResource(R.drawable.ic_plus);
        valueSelector.setMinusIconResource(R.drawable.ic_remove_circle);
        valueSelector.setValueTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        valueSelector.setBorderColor(Color.BLACK, 0);
        valueSelector.setBorderRadius(2);
        valueSelector.gapValue(5);
        value_selector2.gapValue(3);

        valueSelector.setLayoutOrientation(LinearLayout.VERTICAL);
        value_selector2.setLayoutOrientation(LinearLayout.VERTICAL);

    }

    @Override
    public void onClick(View v) {
        String value = String.valueOf(valueSelector.getValue());
        Toast.makeText(this, value + "/" + valueSelector.length(), Toast.LENGTH_SHORT).show();

    }
>>>>>>> develop

}