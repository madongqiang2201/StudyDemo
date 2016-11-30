package com.madqiang.studydemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * package：com.madqiang.studydemo.views
 * Date：16/11/20 22:24
 * Version：1.0
 * 功能简介：
 * 修改历史：
 * Author：马东强
 */
public class DrawView extends View{
    private float preX;
    private float preY;
    private Bitmap cacheBitMap;
    private Canvas cacheCanvas;
    private Path path;
    private Paint paint;
    final int VIEW_WIDTH = 1154;
    final int VIEW_HEIGHT = 1920;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cacheBitMap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        path = new Path();
        cacheCanvas.setBitmap(cacheBitMap);

        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Paint bmpPaint = new Paint();
        bmpPaint.setColor(Color.RED);
        canvas.drawBitmap(cacheBitMap,0,0,bmpPaint);
        canvas.drawPath(path,paint);
    }
}
