package com.madqiang.studydemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.madqiang.studydemo.R;

/**
 * package：com.madqiang.studydemo.views
 * Date：16/11/21 22:13
 * Version：1.0
 * 功能简介：
 * 修改历史：
 * Author：马东强
 */
public class MatrixView extends View {
    private Bitmap bitmap;
    private Matrix matrix;
    private int width,height;
    // 倾斜角度
    private float sx = 0.0f;
    private float scale = 1.0f;
    private boolean isScale = false;

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.luffy);
        matrix = new Matrix();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        matrix.reset();
        if (!isScale){
            matrix.setSkew(sx,0);
        }else {
            matrix.setScale(scale,scale);
        }

        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        canvas.drawBitmap(bitmap1,matrix,null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_A:
                isScale = false;
                sx += 0.1;
                postInvalidate();
                break;
            case KeyEvent.KEYCODE_D:
                isScale = false;
                sx -= 0.1;
                postInvalidate();
                break;
            case KeyEvent.KEYCODE_W:
                isScale = true;
                if (scale < 2.0){
                    scale += 0.1;
                }
                postInvalidate();;
                break;
            case KeyEvent.KEYCODE_S:
                isScale = true;
                if (scale > 0.5){
                    scale -= 0.1;
                }
                postInvalidate();
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
