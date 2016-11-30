package com.madqiang.studydemo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * package：com.madqiang.studydemo
 * Date：16/11/20 20:32
 * Version：1.0
 * 功能简介：
 * 修改历史：
 * Author：马东强
 */
public class CustomDrawable extends Drawable {
    private Paint mPaint;
    public CustomDrawable(int color){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
    }
    @Override
    public void draw(Canvas canvas) {
        final Rect rect = getBounds();
        float cx = rect.exactCenterX();
        float cy = rect.exactCenterY();

        canvas.drawRect(0,0,cx*4/2,cy*4/2,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
