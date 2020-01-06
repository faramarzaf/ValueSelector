[![](https://jitpack.io/v/faramarzaf/ValueSelector.svg)](https://jitpack.io/#faramarzaf/ValueSelector)

# ValueSelector  


Value Selector is a customizable Android library for value selection in any state that you want with easy usage.  

# Screenshots
<p align="center">
  <img src="https://raw.github.com/faramarzaf/ValueSelector/master/screenshots/1.png" height="400" width="200" />
  <img src="https://raw.github.com/faramarzaf/ValueSelector/master/screenshots/2.png"  height="400" width="200" /> 
  <img src="https://raw.github.com/faramarzaf/ValueSelector/master/screenshots/3.png"  height="400" width="200" /> 
  <img src="https://raw.github.com/faramarzaf/ValueSelector/master/screenshots/4.png"  height="400" width="200" /> 
</p>

- Customizable  
  - color
  - fonts 
    - Also supports not-latin fonts like *Persian*, *Arabic*,etc. (As you can see in the first screenshot) 
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
	        implementation 'com.github.faramarzaf:ValueSelector:1.0.2'
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
        app:customFontFamily="faryekan.ttf"
        />
```
|N.o| Attributes | Description |N.o|Attributes|Description|
|-------------| ------------- | -------------|-------------|-------------|-------------|
|1|borderColor|b|11|plusBtnColor|b|
|2|borderRadius|b|12|plusIconHeightSize|b|
|3|borderThickness|b|13|plusIconWidthSize|b|
|4|gapValue|b|14|startValue|b|
|5|invertIconsPlace|b|15|updateInterval|b|
|6|maxValue|b|16|valueColor|b|
|7|minValue|b|17|valueSelectorOrientation|b|
|8|minusBtnColor|b|18|valueTextSize|b|
|9|minusIconHeightSize|b|19|valueSelectorFontFamily|b|
|10|minusIconWidthSize|b|20|customFontFamily|b|

# Change Log

- V 1.0.2
  - FIRST STABLE VERSION
- V 1.0.1
  - SNAPSHOT(Recommend not to use)
- V 1.0.0
  - SNAPSHOT(Recommend not to use)
