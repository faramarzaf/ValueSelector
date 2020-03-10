# Change Log 

- v1.0.8 **(March/10/2020)**  
  - ActionDownColor option added
  - Bug interval fixed
  - Bug invertIconsColor fixed. (When you've been selected the invertIcons --> Colors applied the opposite sides)


- v1.0.7 **(Feb/20/2020)**  
  - New Java public methods added
  ```java
  
   int length();
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
  ```

- v1.0.6 **(Jan/26/2020)**  
  - New styles added for plus and minus icon  
    - plus_minus
    - arrow
    - expand
    - circle

- v1.0.5 **(Jan/22/2020)**  
  - maxWidth and minWidth changed to `90dp` & `60dp`  

- v1.0.4 **(Jan/20/2020)**  
  - Default value of minValue & maxValue changed to: `Integer.MIN_VALUE` , `Integer.MAX_VALUE`  
  - Default valueTextSize changed to 20  
  - Default value of IconsSize changed to 45  
  - Default value of invertIconsPlace is 1  
    - "-" icon is at left side and "+" icon is at the right side of layout (In vertical mode)  And vice versa in Horizontal mode  

- v1.0.3 **(Jan/13/2020)**
  - Default borderColor changed to transparent mood. (It was black that was not very cool)  
  - Default gapValue changed to "1". (It was 0 and that's made a bug when client didn't set gapValue attr in XML layout)  
  - Default BorderThickness changed to "0".  
  - Default BorderRadius changed to "0".  
