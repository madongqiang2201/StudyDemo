package com.madqiang.studydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    // 游戏是否失败
    private boolean isLose = false;
    // 小球当前坐标
    Random rand = new Random();
    private int ballX = rand.nextInt(200) + 20;
    private int ballY = rand.nextInt(10) + 20;
    // 球拍左上角坐标
    private int racketX = rand.nextInt(200);
    private int racketY;
    // 小球大小
    private final int BALL_SIZE = 12;
    // 球拍宽高
    private final int RACKET_HEIGHT = 100;
    private final int RACKET_WIDTH = 170;
    // 屏幕宽高
    private int tableWidth;
    private int tableHeight;
    // 小球在x方向和y方向的速度
    private int ySpeed = 10;
    private double xyRate = rand.nextDouble() - 0.5;
    private int xSpeed = (int) (ySpeed * xyRate * 2);

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gameView = new GameView(this);
        setContentView(gameView);

        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        tableHeight = metrics.heightPixels;
        tableWidth = metrics.widthPixels;

        racketY = tableHeight - 400;

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123){
                    gameView.invalidate();
                }
            }
        };
        gameView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_A:
                        if (racketX > 0){
                            racketX -= 10;
                        }
                        break;
                    case KeyEvent.KEYCODE_D:
                        if (racketX < tableWidth - RACKET_WIDTH){
                            racketX += 10;
                        }
                        break;
                    default:
                        break;
                }
                gameView.invalidate();
                return true;
            }
        });

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (ballX <=0 || ballX >= tableWidth - BALL_SIZE){
                    xSpeed = - xSpeed;
                }

                if (ballY >= racketY - BALL_SIZE && (ballX < racketX || ballX > racketX + RACKET_WIDTH)){
                    timer.cancel();
                    isLose = true;
                }else if (ballY <= 0 || (ballY >= racketY - BALL_SIZE && ballX > racketX && ballX <= racketX + RACKET_WIDTH)){
                    ySpeed = -ySpeed;
                }

                ballX += xSpeed;
                ballY += ySpeed;

                handler.sendEmptyMessage(0x123);
            }
        },0,100);
    }

    class GameView extends View{
        Paint paint;
        public GameView(Context context) {
            super(context);
            setFocusable(true);
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            if (isLose){
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                canvas.drawText("游戏结束,你失败了",50,200,paint);
            }else {
                paint.setColor(Color.RED);
                canvas.drawCircle(ballX,ballY,BALL_SIZE,paint);

                paint.setColor(Color.BLUE);
                canvas.drawRect(racketX,racketY,racketX + RACKET_WIDTH,racketY + RACKET_HEIGHT,paint);
            }
        }
    }
}
