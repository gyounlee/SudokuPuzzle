package com.novemberstory.sudokupuzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class SudokuCell extends View {
    private int value;
    private boolean initValue = false;
    private Paint mPaint;
    private int backgroundColor;
    private int textColor;

    public SudokuCell(Context context) {

        super(context);
        mPaint = new Paint();
        backgroundColor = Color.WHITE;
        textColor = Color.BLACK;
    }

    public void setBackgroundColor(int color) {
        backgroundColor = color;
    }

    public void setTextColor(int color) {
        textColor = color;
    }

    public void setValue( int value ) {
        this.value = value;
        invalidate();
    }

    public void setInitValue( int value ) {
        if (value != 0) {
            this.initValue = true;
        } else {
            this.initValue = false;
        }
        textColor = Color.BLACK;
        setValue(value);
    }

    public int getValue(){
        return value;
    }

    public boolean isInitValue() {
        return initValue;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(backgroundColor);
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(2, 2, getWidth()-2, getHeight() - 2, mPaint );

        mPaint.setColor(textColor);
        mPaint.setTextSize(60);
        mPaint.setStyle(Paint.Style.FILL);
        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(getValue()), 0, String.valueOf(getValue()).length(), bounds);
        if( getValue() != 0 ){
            canvas.drawText(String.valueOf(getValue()), (getWidth() - bounds.width())/2, (getHeight() + bounds.height())/2	, mPaint);
        }

    }
}
