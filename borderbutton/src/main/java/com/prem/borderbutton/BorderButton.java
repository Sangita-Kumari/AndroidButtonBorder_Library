package com.prem.borderbutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

public class BorderButton extends AppCompatButton {

    private boolean isStrokeEnabled;
    private int intButtonColor;
    private int intStrokeColor;

    private String mButtonColor;
    private String mStrokeColor;

    private int mStrokeWidth;
    private int mCornerRadius;

    private Drawable drawable;

    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    private int mPadding;

    public BorderButton(Context context) {
        super(context);
        init();
    }

    public BorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        parseAttrs(context, attrs);
    }

    public BorderButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        parseAttrs(context, attrs);
    }

    private void init() {
        isStrokeEnabled = false;
        Resources resources = getResources();
        if (resources == null) return;

        mButtonColor = "#3eadeb";
        intButtonColor = Color.parseColor(mButtonColor);
        mStrokeColor = "#dddddd";
        intStrokeColor = Color.parseColor(mStrokeColor);

        mStrokeWidth = resources.getDimensionPixelSize(R.dimen.strokeWidth);
        mCornerRadius = resources.getDimensionPixelSize(R.dimen.cornerRadius);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonStrokeAttrs);
        if (typedArray == null) return;
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.ButtonStrokeAttrs_borderColor) {
                mStrokeColor = typedArray.getString(attr);
                intStrokeColor = Color.parseColor(mStrokeColor);
                isStrokeEnabled = true;
            } else if (attr == R.styleable.ButtonStrokeAttrs_buttonColor) {
                mButtonColor = typedArray.getString(attr);
                intButtonColor = Color.parseColor(mButtonColor);
            } else if (attr == R.styleable.ButtonStrokeAttrs_borderWidth) {
                mStrokeWidth = typedArray.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.ButtonStrokeAttrs_cornerRadius) {
                mCornerRadius = typedArray.getDimensionPixelSize(attr, 0);
            }
        }
        typedArray.recycle();

        //Get paddingLeft, paddingRight
        int[] attrsArray = new int[]{
                android.R.attr.paddingLeft,  // 0
                android.R.attr.paddingRight, // 1
        };
        TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
        if (ta == null) return;
        mPaddingLeft = ta.getDimensionPixelSize(0, 0);
        mPaddingRight = ta.getDimensionPixelSize(1, 0);
        ta.recycle();

        //Get paddingTop, paddingBottom
        int[] attrsArray2 = new int[]{
                android.R.attr.paddingTop,   // 0
                android.R.attr.paddingBottom,// 1
        };
        TypedArray ta1 = context.obtainStyledAttributes(attrs, attrsArray2);
        if (ta1 == null) return;
        mPaddingTop = ta1.getDimensionPixelSize(0, 0);
        mPaddingBottom = ta1.getDimensionPixelSize(1, 0);
        ta1.recycle();

        int[] attrsArray3 = new int[]{
                android.R.attr.padding
        };
        TypedArray ta2 = context.obtainStyledAttributes(attrs, attrsArray3);
        if (ta2 == null) return;
        mPadding = ta2.getDimensionPixelSize(0, 0);
        ta2.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        refresh();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        refresh();
    }

    private void refresh() {
        int alpha = Color.alpha(intButtonColor);
        float[] hsv = new float[3];
        Color.colorToHSV(intButtonColor, hsv);
        hsv[2] *= 0.8f;

        if (this.isEnabled()) {
            if (isStrokeEnabled) {
                drawable = createDrawable(mCornerRadius, intButtonColor, intStrokeColor);
            } else {
                drawable = createDrawable(mCornerRadius, intButtonColor, Color.TRANSPARENT);
            }
        } else {
            Color.colorToHSV(intButtonColor, hsv);
            hsv[1] *= 0.25f; // saturation component
            int disabledColor = Color.HSVToColor(alpha, hsv);
            // Disabled button does not have shadow
            drawable = createDrawable(mCornerRadius, disabledColor, Color.TRANSPARENT);
        }

        if (drawable == null) return;
        //Set button background
        if (Build.VERSION.SDK_INT >= 16) {
            this.setBackground(drawable);
        } else {
            this.setBackgroundDrawable(drawable);
        }

        this.setPadding(  mPaddingLeft + mPadding + 12, mPaddingTop + mPadding + 12, mPaddingRight + mPadding + 12, mPaddingBottom + mPadding + 12);
    }

    private LayerDrawable createDrawable(int radius, int topColor, int backColor) {
        float[] outerRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        //Top
        RoundRectShape topRoundRect = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable topShapeDrawable = new ShapeDrawable(topRoundRect);
        topShapeDrawable.getPaint().setColor(topColor);
        //Bottom
        RoundRectShape roundRectShape = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
        bottomShapeDrawable.getPaint().setColor(backColor);
        //Create array
        Drawable[] drawArray = {bottomShapeDrawable, topShapeDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(drawArray);

        if (isStrokeEnabled) {
            layerDrawable.setLayerInset(1, mStrokeWidth, mStrokeWidth, mStrokeWidth, mStrokeWidth);
        }

        return layerDrawable;
    }


    public void build(){
        refresh();
    }
    //Java setter
    public BorderButton setButtonColor(String buttonColor){
        this.mButtonColor = buttonColor;
        intButtonColor = Color.parseColor(mButtonColor);
        return this;
    }

    public BorderButton setBorderColor(String borderColor){
        this.mStrokeColor = borderColor;
        intStrokeColor = Color.parseColor(mStrokeColor);
        return this;
    }

    public BorderButton setBorderWidth(int width){
        this.mStrokeWidth = width;
        this.isStrokeEnabled = true;
        return this;
    }

    public BorderButton setCornerRadius(int radius){
        this.mCornerRadius = radius;
        return this;
    }


    //Getter
    public String getButtonColor(){
        return mButtonColor;
    }

    public String getStrokeColor(){
        return mStrokeColor;
    }

    public int getStrokeWidth(){
        return mStrokeWidth;
    }

    public int getCornerRadius(){
        return mCornerRadius;
    }

}
