package com.faramarz.tictacdev.mycustomcounter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**

 * The ValueSelector helps to get value easily

 * @version 1.0

 * @author Faramarz Afzali

 */


public class ValueSelector extends LinearLayout implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    TypedArray ta;
    View rootView;
    TextView valueText;
    View buttonPlus;
    View buttonMinus;
    LinearLayout parentView;
    GradientDrawable gd = new GradientDrawable();

    private boolean isPlusButtonPressed = false;
    private boolean isMinusButtonPressed = false;
    private Handler handler;

    private static final int TIME_INTERVAL = 100;
    int minValue = Integer.MIN_VALUE;
    int maxValue = Integer.MAX_VALUE;
    int valueColor;
    int borderThickness;
    int borderRadius;

    public ValueSelector(Context context) {
        super(context);
        init(context);
    }

    public ValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setCustomValueColor(attrs);
        setCustomBorderColor(attrs);
        setCustomBorderThickness(attrs);
        setCustomBorderRadius(attrs);
    }

    public ValueSelector(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        setCustomValueColor(attrs);
        setCustomBorderColor(attrs);
        setCustomBorderThickness(attrs);
        setCustomBorderRadius(attrs);
    }


    private void setCustomValueColor(AttributeSet set) {
        if (set == null) {
            return;
        }
        ta = getContext().obtainStyledAttributes(set, R.styleable.ValueSelector);
        valueColor = ta.getColor(R.styleable.ValueSelector_value_color, Color.BLACK);
        valueText.setTextColor(valueColor);
        ta.recycle();
    }


    private void setCustomBorderColor(AttributeSet set) {
        if (set == null) {
            return;
        }
        ta = getContext().obtainStyledAttributes(set, R.styleable.ValueSelector);
        valueColor = ta.getColor(R.styleable.ValueSelector_border_color, Color.BLACK);

        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            parentView.setBackgroundDrawable(gd);
        } else {
            parentView.setBackground(gd);
        }
        gd.setStroke(1, valueColor);
        ta.recycle();
    }


    private void setCustomBorderThickness(AttributeSet set) {
        if (set == null) {
            return;
        }
        ta = getContext().obtainStyledAttributes(set, R.styleable.ValueSelector);
        borderThickness = ta.getInteger(R.styleable.ValueSelector_border_thickness, 1);
        gd.setStroke(borderThickness, valueColor);
        ta.recycle();
    }


    private void setCustomBorderRadius(AttributeSet set) {
        if (set == null) {
            return;
        }
        ta = getContext().obtainStyledAttributes(set, R.styleable.ValueSelector);
        borderRadius = ta.getInteger(R.styleable.ValueSelector_border_radius, 1);
        gd.setCornerRadius(borderRadius);
        ta.recycle();
    }


    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private void init(Context context) {
        setSaveEnabled(true);

        rootView = inflate(context, R.layout.value_selector, this);
        valueText = rootView.findViewById(R.id.value_number);
        buttonMinus = rootView.findViewById(R.id.btn_minus);
        buttonPlus = rootView.findViewById(R.id.btn_plus);
        parentView = rootView.findViewById(R.id.parentView);
        handler = new Handler();
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnLongClickListener(this);
        buttonMinus.setOnLongClickListener(this);
        buttonMinus.setOnTouchListener(this);
        buttonPlus.setOnTouchListener(this);
    }

    public int getValue() {

        String text = valueText.getText().toString();
        if (text.isEmpty()) {
            valueText.setText("0");
            return 0;
        }
        return Integer.valueOf(text);
    }

    public void setValue(int newValue) {
        if (newValue > maxValue) {
            valueText.setText(String.valueOf(maxValue));
        } else if (newValue < minValue) {
            valueText.setText(String.valueOf(minValue));
        } else {
            valueText.setText(String.valueOf(newValue));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonMinus.getId()) {
            decrementValue();
        } else if (view.getId() == buttonPlus.getId()) {
            incrementValue();
        }
    }

    private void decrementValue() {

        int value = getValue();
        setValue(value - 1);
    }

    private void incrementValue() {

        int value = getValue();
        setValue(value + 1);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
            isPlusButtonPressed = false;
            isMinusButtonPressed = false;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == buttonMinus.getId()) {
            isMinusButtonPressed = true;
            handler.postDelayed(new AutoDecrementer(), TIME_INTERVAL);
        } else if (view.getId() == buttonPlus.getId()) {
            isPlusButtonPressed = true;
            handler.postDelayed(new AutoIncrementer(), TIME_INTERVAL);
        }
        return false;
    }

    private class AutoDecrementer implements Runnable {

        @Override
        public void run() {
            if (isMinusButtonPressed) {
                decrementValue();
                handler.postDelayed(this, TIME_INTERVAL);
            }
        }
    }

    private class AutoIncrementer implements Runnable {
        @Override
        public void run() {
            if (isPlusButtonPressed) {
                incrementValue();
                handler.postDelayed(this, TIME_INTERVAL);
            }
        }
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        ValueSelectorSavedState ss = new ValueSelectorSavedState(super.onSaveInstanceState());
        ss.minValue = this.minValue;
        ss.maxValue = this.maxValue;
        ss.currentValue = getValue();
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        ValueSelectorSavedState ss = (ValueSelectorSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setValue(ss.currentValue);
        setMinValue(ss.minValue);
        setMaxValue(ss.maxValue);
    }

    public static class ValueSelectorSavedState extends BaseSavedState {
        int currentValue;
        int minValue;
        int maxValue;

        public ValueSelectorSavedState(Parcel in) {
            super(in);
            currentValue = in.readInt();
            minValue = in.readInt();
            maxValue = in.readInt();
        }

        public ValueSelectorSavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(currentValue);
            out.writeInt(minValue);
            out.writeInt(maxValue);
        }


        public static final Parcelable.Creator<ValueSelectorSavedState> CREATOR = new Creator<ValueSelectorSavedState>() {
            @Override
            public ValueSelectorSavedState createFromParcel(Parcel parcel) {
                return new ValueSelectorSavedState(parcel);
            }

            @Override
            public ValueSelectorSavedState[] newArray(int i) {
                return new ValueSelectorSavedState[i];
            }
        };

    }

}




