package com.faramarz.tictacdev.mycustomcounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * The ValueSelector helps to get value easily
 *
 * @author Faramarz Afzali
 * @version 1.0.8
 * @since Nov 8, 2019
 */

public class TestValueSelector extends LinearLayout implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, PublicMethods {

    private static final String LOG_TAG = "LOG_TAG";
    private TypedArray typedArray;
    private View rootView;
    private TextView valueText;
    private LinearLayout parentView, itemsContainer;
    private ImageView plusImg;
    private ImageView minusImg;
    private GradientDrawable gd = new GradientDrawable();
    private Handler handler;
    private boolean isPlusButtonPressed = false;
    private boolean isMinusButtonPressed = false;
    private int minValue = Integer.MIN_VALUE;
    private int maxValue = Integer.MAX_VALUE;
    private int valueColor;
    private int plusColor;
    private int minusColor;
    private int borderThickness;
    private int borderRadius;
    private int gapValue;
    private int viewOrientation;
    private int valueTextSize;
    private int plusIconWidthSize;
    private int plusIconHeightSize;
    private int minusIconWidthSize;
    private int minusIconHeightSize;
    private int fontFamily;
    private int isIconInvert;
    private int intervalTime;
    private int actionDownColor;
    // for selection icon types
    private int iconTypes;


    public TestValueSelector(Context context) {
        super(context);
        init(context);

    }

    public TestValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setCustomValueColor(attrs);
        setCustomBorderColor(attrs);
        setCustomBorderThickness(attrs);
        setCustomBorderRadius(attrs);
        setCustomPlusColor(attrs);
        setCustomMinusColor(attrs);
        setCustomMaxValue(attrs);
        setCustomMinValue(attrs);
        setStartValue(attrs);
        updateInterval(attrs);
        setGapValue(attrs);
        setViewOrientation(attrs);
        setValueTextSize(attrs);
        setIconsSize(attrs);
        setFontFamily(attrs);
        setValueSelectorCustomFont(context, attrs);
        selectIconTypes(attrs);
        setActionDownColor(attrs);
        checkInvertIconColor();
    }

    public TestValueSelector(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        setCustomValueColor(attrs);
        setCustomBorderColor(attrs);
        setCustomBorderThickness(attrs);
        setCustomBorderRadius(attrs);
        setCustomPlusColor(attrs);
        setCustomMinusColor(attrs);
        setCustomMaxValue(attrs);
        setCustomMinValue(attrs);
        setStartValue(attrs);
        updateInterval(attrs);
        setGapValue(attrs);
        setViewOrientation(attrs);
        setValueTextSize(attrs);
        setIconsSize(attrs);
        setFontFamily(attrs);
        setValueSelectorCustomFont(context, attrs);
        selectIconTypes(attrs);
        setActionDownColor(attrs);
        checkInvertIconColor();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        setSaveEnabled(true);
        rootView = inflate(context, R.layout.value_selector, this);
        valueText = rootView.findViewById(R.id.value_number);
        minusImg = rootView.findViewById(R.id.btn_minus);
        parentView = rootView.findViewById(R.id.parentView);
        plusImg = rootView.findViewById(R.id.btn_plus);
        itemsContainer = rootView.findViewById(R.id.itemsContainer);
        handler = new Handler();
        plusImg.setOnClickListener(this);
        minusImg.setOnClickListener(this);
        plusImg.setOnLongClickListener(this);
        minusImg.setOnLongClickListener(this);
        minusImg.setOnTouchListener(this);
        plusImg.setOnTouchListener(this);
    }

    private void selectIconTypes(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        iconTypes = typedArray.getInt(R.styleable.TestValueSelector_testiconTypes, 0);
        isIconInvert = typedArray.getInt(R.styleable.TestValueSelector_testinvertIconsPlace, 0);
        if (isIconInvert == 1 && iconTypes == 0) {
            minusImg.setImageResource(R.drawable.ic_plus);
            plusImg.setImageResource(R.drawable.ic_minus);
        } else if (isIconInvert == 1 && iconTypes == 1) {
            minusImg.setImageResource(R.drawable.ic_arrow_up);
            plusImg.setImageResource(R.drawable.ic_arrow_down);
        } else if (isIconInvert == 1 && iconTypes == 2) {
            minusImg.setImageResource(R.drawable.ic_expand_up);
            plusImg.setImageResource(R.drawable.ic_expand_down);
        } else if (isIconInvert == 1 && iconTypes == 3) {
            minusImg.setImageResource(R.drawable.ic_add_circle);
            plusImg.setImageResource(R.drawable.ic_remove_circle);
        } else if (isIconInvert == 0 && iconTypes == 0) {
            minusImg.setImageResource(R.drawable.ic_minus);
            plusImg.setImageResource(R.drawable.ic_plus);
        } else if (isIconInvert == 0 && iconTypes == 1) {
            minusImg.setImageResource(R.drawable.ic_arrow_down);
            plusImg.setImageResource(R.drawable.ic_arrow_up);
        } else if (isIconInvert == 0 && iconTypes == 2) {
            minusImg.setImageResource(R.drawable.ic_expand_down);
            plusImg.setImageResource(R.drawable.ic_expand_up);
        } else if (isIconInvert == 0 && iconTypes == 3) {
            minusImg.setImageResource(R.drawable.ic_remove_circle);
            plusImg.setImageResource(R.drawable.ic_add_circle);
        }
        typedArray.recycle();
    }

    private void setCustomValueColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        valueColor = typedArray.getColor(R.styleable.TestValueSelector_testvalueColor, Color.BLACK);
        valueText.setTextColor(valueColor);
        typedArray.recycle();
    }

    private void setCustomBorderColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        valueColor = typedArray.getColor(R.styleable.TestValueSelector_testborderColor, Color.TRANSPARENT);

        final int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            parentView.setBackgroundDrawable(gd);
        } else {
            parentView.setBackground(gd);
        }
        gd.setStroke(1, valueColor);
        typedArray.recycle();
    }

    private void setCustomBorderThickness(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        borderThickness = typedArray.getInteger(R.styleable.TestValueSelector_testborderThickness, 0);
        gd.setStroke(borderThickness, valueColor);
        typedArray.recycle();
    }

    private void setCustomBorderRadius(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        borderRadius = typedArray.getInteger(R.styleable.TestValueSelector_testborderRadius, 0);
        gd.setCornerRadius(borderRadius);
        typedArray.recycle();
    }


    private void setCustomPlusColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        plusColor = typedArray.getColor(R.styleable.TestValueSelector_testplusBtnColor, Color.BLACK);
        plusImg.setColorFilter(plusColor);
        typedArray.recycle();
    }

    private void setCustomMinusColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        minusColor = typedArray.getColor(R.styleable.TestValueSelector_testminusBtnColor, Color.BLACK);
        minusImg.setColorFilter(minusColor);
        typedArray.recycle();
    }


    private void checkInvertIconColor() {
        if (isIconInvert == 1) {
            plusImg.setColorFilter(minusColor);
            minusImg.setColorFilter(plusColor);

        } else if (isIconInvert == 0) {
            plusImg.setColorFilter(plusColor);
            minusImg.setColorFilter(minusColor);
        }
    }

    private void setActionDownColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        actionDownColor = typedArray.getColor(R.styleable.TestValueSelector_testactionDownColor, plusColor);
        typedArray.recycle();
    }

    private void setCustomMaxValue(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        maxValue = typedArray.getInt(R.styleable.TestValueSelector_testmaxValue, maxValue);
        typedArray.recycle();
    }

    private void setCustomMinValue(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        minValue = typedArray.getInt(R.styleable.TestValueSelector_testminValue, minValue);
        typedArray.recycle();
    }

    private void setStartValue(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        ValueSelectorSavedState ss = new ValueSelectorSavedState(super.onSaveInstanceState());
        ss.currentValue = typedArray.getInt(R.styleable.TestValueSelector_teststartValue, 0);
        valueText.setText(String.valueOf(ss.currentValue));
        typedArray.recycle();
    }


    private void updateInterval(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        intervalTime = typedArray.getInt(R.styleable.TestValueSelector_testupdateInterval, 100);
        typedArray.recycle();
    }

    private void setGapValue(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        gapValue = typedArray.getInt(R.styleable.TestValueSelector_testgapValue, 1);
        typedArray.recycle();
    }

    private void setViewOrientation(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        viewOrientation = typedArray.getInt(R.styleable.TestValueSelector_testvalueSelectorOrientation, 0);
        if (viewOrientation == 0) {
            itemsContainer.setOrientation(HORIZONTAL);
        } else if (viewOrientation == 1) {
            itemsContainer.setOrientation(VERTICAL);
        }
        typedArray.recycle();
    }

    private void setValueTextSize(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        valueTextSize = typedArray.getDimensionPixelSize(R.styleable.TestValueSelector_testvalueTextSize, 20);
        valueText.setTextSize(valueTextSize);
        typedArray.recycle();
    }

    private void setIconsSize(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        plusIconWidthSize = typedArray.getDimensionPixelSize(R.styleable.TestValueSelector_testplusIconWidthSize, 45);
        plusIconHeightSize = typedArray.getDimensionPixelSize(R.styleable.TestValueSelector_testplusIconHeightSize, 45);
        minusIconWidthSize = typedArray.getDimensionPixelSize(R.styleable.TestValueSelector_testminusIconWidthSize, 45);
        minusIconHeightSize = typedArray.getDimensionPixelSize(R.styleable.TestValueSelector_testminusIconHeightSize, 45);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(plusIconWidthSize, plusIconHeightSize);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(minusIconWidthSize, minusIconHeightSize);
        plusImg.setLayoutParams(params);
        minusImg.setLayoutParams(params2);
        typedArray.recycle();
    }


    private void setFontFamily(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.TestValueSelector);
        fontFamily = typedArray.getInt(R.styleable.TestValueSelector_testvalueSelectorFontFamily, 0);
        if (fontFamily == 0) {
            valueText.setTypeface(Typeface.MONOSPACE);
        } else if (fontFamily == 1) {
            valueText.setTypeface(Typeface.SERIF);
        } else if (fontFamily == 2) {
            valueText.setTypeface(Typeface.SANS_SERIF);
        }
        typedArray.recycle();
    }

    private void setValueSelectorCustomFont(Context ctx, AttributeSet attrs) {
        checkNullSet(attrs);
        typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.TestValueSelector);
        String customFont = typedArray.getString(R.styleable.TestValueSelector_testcustomFontFamily);
        setCustomFont(ctx, customFont);
        typedArray.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Could not get typeface: " + e.getMessage() + "\n" + "Place your custom fonts in assets folder to access customFontFamily attribute.");
            return false;
        }
        valueText.setTypeface(tf);
        return true;
    }

    /**
     * ####################################
     * PUBLIC METHODS
     * ####################################
     */

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

    public int length() {
        return valueText.length();
    }

    public void setValueTextColor(int color) {
        valueText.setTextColor(color);
    }

    public void setPlusIconColor(int color) {
        plusImg.setColorFilter(color);
    }

    public void setMinusIconColor(int color) {
        minusImg.setColorFilter(color);

    }

    public void setPlusIconResource(int resource) {
        plusImg.setImageResource(resource);
    }

    public void setMinusIconResource(int resource) {
        minusImg.setImageResource(resource);
    }

    public void setValueTextSize(int unit, float size) {
        valueText.setTextSize(unit, size);
    }

    public void setBorderColor(int borderColor, int width) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            parentView.setBackgroundDrawable(gd);
        } else {
            parentView.setBackground(gd);
        }
        gd.setStroke(width, borderColor);
    }

    public void setBorderRadius(int radius) {
        gd.setCornerRadius(radius);
    }

    public void gapValue(int gap) {
        gapValue = gap;
    }

    public void setLayoutOrientation(int orientation) {
        itemsContainer.setOrientation(orientation);
    }

    public void setUpdateInterval(int time) {
        intervalTime = time;
    }

    public void initActionDownColor(View view, int color) {
        this.actionDownColor = color;
        switch (view.getId()) {
            case R.id.btn_plus:
                plusImg.setColorFilter(color);
                break;
            case R.id.btn_minus:
                minusImg.setColorFilter(color);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == minusImg.getId()) {
            decrementValue();
        } else if (view.getId() == plusImg.getId()) {
            incrementValue();
        }
    }

    private void decrementValue() {
        int value = getValue();
        if (value > minValue && isIconInvert == 0)
            setValue(value - gapValue);
        else if (isIconInvert == 1) {
            setValue(value + gapValue);
        }
    }

    private void incrementValue() {
        int value = getValue();
        if (value < maxValue && isIconInvert == 0)
            setValue(value + gapValue);
        else if (isIconInvert == 1) {
            setValue(value - gapValue);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
            isPlusButtonPressed = false;
            isMinusButtonPressed = false;
            initActionUpColor(view);
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            initActionDownColor(view, actionDownColor);
        }
        return false;
    }

    private void initActionUpColor(View view) {
        switch (view.getId()) {
            case R.id.btn_plus:
            case R.id.btn_minus:
                checkInvertIconColor();
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == minusImg.getId()) {
            isMinusButtonPressed = true;
            handler.postDelayed(new AutoDecrementer(), intervalTime);
        } else if (view.getId() == plusImg.getId()) {
            isPlusButtonPressed = true;
            handler.postDelayed(new AutoIncrementer(), intervalTime);
        }
        return false;
    }

    private class AutoDecrementer implements Runnable {

        @Override
        public void run() {
            if (isMinusButtonPressed) {
                decrementValue();
                handler.postDelayed(this, intervalTime);
            }
        }
    }

    private class AutoIncrementer implements Runnable {
        @Override
        public void run() {
            if (isPlusButtonPressed) {
                incrementValue();
                handler.postDelayed(this, intervalTime);
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

    private void checkNullSet(AttributeSet set) {
        if (set == null) {
            return;
        }
    }

}
