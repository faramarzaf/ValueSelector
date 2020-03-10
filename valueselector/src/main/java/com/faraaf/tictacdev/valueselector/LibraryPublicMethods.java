package com.faraaf.tictacdev.valueselector;


import android.view.View;

/**
 * public methods for client usage by Java
 */

public interface LibraryPublicMethods {

    int getMinValue();

    void setMinValue(int minValue);

    int getMaxValue();

    void setMaxValue(int maxValue);

    int length();

    void setValue(int newValue);

    int getValue();

    void setValueTextColor(int color);

    void setPlusIconColor(int color);

    void setMinusIconColor(int color);

    void setPlusIconResource(int resource);

    void setMinusIconResource(int resource);

    void setValueTextSize(int unit, float size);

    void setBorderColor(int borderColor, int width);

    void setBorderRadius(int radius);

    void gapValue(int gap);

    void setLayoutOrientation(int orientation);

    void setUpdateInterval(int time);

    void initActionDownColor(View view, int color);
}
