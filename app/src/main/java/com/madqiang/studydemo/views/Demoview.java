package com.madqiang.studydemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * package：com.madqiang.studydemo.views
 * Date：16/11/20 21:53
 * Version：1.0
 * 功能简介：
 * 修改历史：
 * Author：马东强
 */
public class Demoview extends View {
    public Demoview(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    private Paint paint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(20,20,200,200);
        canvas.drawRoundRect(rectF,15,15,paint);

        Path path = new Path();
        path.moveTo(200,200);
        path.lineTo(300,300);
        path.lineTo(100,300);
        path.close();
        canvas.drawPath(path,paint);

        String str = "跟随path输出的一段话";

        Path path1 = new Path();
        RectF rectF1 = new RectF(500,500,100,800);
        path1.addOval(rectF1, Path.Direction.CCW);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawPath(path1,paint);
        canvas.drawTextOnPath(str,path1,20,20,paint);

    }
}
