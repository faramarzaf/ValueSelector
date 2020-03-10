[![](https://jitpack.io/v/faramarzaf/ValueSelector.svg)](https://jitpack.io/#faramarzaf/ValueSelector)
[![API](https://img.shields.io/badge/API-20%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=20)
# ValueSelector  


Value Selector is an Android library for value selection in any state that you want with easy usage. 

<p align="center">
<img src="https://raw.github.com/faramarzaf/ValueSelector/master/screenshots/Value Selector.png" />
</p>

- Customizable 
  - color
  - fonts 
    - Also supports not-latin fonts like *Persian*, *Arabic*,etc.
  - size
  - thickness
  - border, etc
- Horizontal and Vertical orientation are supported 
- Build in **AndroidX** 

 :heavy_exclamation_mark: **Note:** Place your custom fonts in `assets` folder to access `customFontFamily` attr. 

# Usage

Add it in your root `build.gradle` at the end of repositories: 

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
```
Step 2. Add the dependency 

```gradle
	dependencies {
	  implementation 'com.github.faramarzaf:ValueSelector:1.0.7'
	}
```

In your XML Layout 

```xml
<com.faramarz.tictacdev.mycustomcounter.ValueSelector
        android:id="@+id/value_selector"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:borderColor="#5C96CE"
        app:borderRadius="10"
        app:borderThickness="8"
        app:gapValue="1"
        app:invertIconsPlace="True"
        app:maxValue="100"
        app:minValue="-100"
        app:minusBtnColor="#1E88E5"
        app:minusIconHeightSize="25dp"
        app:minusIconWidthSize="25dp"
        app:plusBtnColor="#D81B60"
        app:plusIconHeightSize="25dp"
        app:plusIconWidthSize="25dp"
        app:startValue="5"
        app:updateInterval="100"
        app:valueColor="#FB8C00"
        app:valueSelectorOrientation="horizontal"
        app:valueTextSize="8sp"
        app:valueSelectorFontFamily="sans_serif"
        app:customFontFamily="your_font.ttf"
	app:iconTypes="circle"
        />
```


|N.o| Attributes | Description |N.o|Attributes|Description|
|-------------| ------------- | -------------|-------------|-------------|-------------|
|1|borderColor|The color of layout border|11|plusBtnColor|The color of `+` icon|
|2|borderRadius|The radius of layout border|12|plusIconHeightSize|The height of `+` icon|
|3|borderThickness|The thickness of layout border|13|plusIconWidthSize|The width of `+` icon|
|4|gapValue|The distance between the values|14|startValue|The number that value selector starts work from that|
|5|invertIconsPlace|The place of `+` & `-` will be change by True/False |15|updateInterval|The time that value changes by long press on plus or minus|
|6|maxValue|The max value that you can select|16|valueColor|The color of value that you choose|
|7|minValue|The min value that you can select|17|valueSelectorOrientation|Vertical or horizontal layout orientation|
|8|minusBtnColor|The color of `-` icon|18|valueTextSize|The text size of value that you select|
|9|minusIconHeightSize|The height of `-` icon |19|valueSelectorFontFamily|Three default fonts: sans_serif, sans and monospace(Without assets fonts) |
|10|minusIconWidthSize|The width of `-` icon |20|customFontFamily|The font that you have in `assets` folder|
|21|iconTypes|Choose between these types `plus_minus` , `arrow` , `expand` , `circle`||||  

**Java usage**  

```java
valueSelector.setCustomFont(this,"your_font.ttf");
valueSelector.setValue(25);
valueSelector.setMaxValue(40);
valueSelector.setMinValue(10);

valueSelector.getMaxValue();
valueSelector.getMinValue();
valueSelector.getValue(); //get current value

valueSelector.setValueTextColor(getResources().getColor(R.color.colorAccent));
valueSelector.setMinusIconColor(getResources().getColor(R.color.colorAccent));
valueSelector.setPlusIconColor(getResources().getColor(R.color.colorAccent));

valueSelector.setPlusIconResource(R.drawable.ic_plus);
valueSelector.setMinusIconResource(R.drawable.ic_remove_circle);
valueSelector.setValueTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

valueSelector.setBorderColor(Color.BLACK, 0);
valueSelector.setBorderRadius(2);
valueSelector.gapValue(5);
valueSelector.setLayoutOrientation(LinearLayout.VERTICAL);
       

```
