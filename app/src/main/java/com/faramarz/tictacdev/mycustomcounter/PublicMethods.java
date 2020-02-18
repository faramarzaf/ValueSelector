package com.faramarz.tictacdev.mycustomcounter;


/**
 * public methods for client usage by Java
 */

public interface PublicMethods {

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

    void updateInterval(int time);

}
