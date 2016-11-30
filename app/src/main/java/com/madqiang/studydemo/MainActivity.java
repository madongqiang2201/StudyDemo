package com.madqiang.studydemo;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll;
    private Button btn;
    private BaseAdapter adapter;
    private List<String> names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_1);
        btn.setOnClickListener(this);

        for (int i = 0;i < 5; i++){
            names.add("mdq" + i);
        }
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return names.size();
            }

            @Override
            public Object getItem(int position) {
                return names.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(MainActivity.this);
                tv.setWidth(ViewPager.LayoutParams.MATCH_PARENT);
                tv.setHeight(200);
                tv.setPadding(20,10,20,10);
                tv.setText(names.get(position));
                return tv;
            }
        };
        ListView lv = (ListView) findViewById(R.id.lv_test);
        lv.setAdapter(adapter);
//        ll = (LinearLayout) findViewById(R.id.ll_container);
//        AlphaAnimation animation = new AlphaAnimation(0,1);
//        animation.setDuration(5000);
//        LayoutAnimationController controller = new LayoutAnimationController(animation,1000);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        ll.setLayoutAnimation(controller);
//        LayoutTransition transition = new LayoutTransition();
//        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.obj_anim);
////        animator.setTarget(ll);
//        transition.setAnimator(LayoutTransition.CHANGE_APPEARING,animator);
//        ll.setLayoutTransition(transition);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent intent = new Intent(this,Android5AnimActivity.class);
                startActivity(intent,options.toBundle());
//                final Button btn = (Button) findViewById(R.id.btn_2);
//
//                int cx = (btn.getLeft() + btn.getRight()) / 2;
//                int cy = (btn.getTop() + btn.getBottom()) / 2;
//                final int radius = Math.max(cx,cy);
//
//                float endRadius = (float) Math.hypot(btn.getWidth(),btn.getHeight());//勾股定理得到斜边长度
//                Animator visible = ViewAnimationUtils.createCircularReveal(btn, btn.getWidth(), 0, 0, endRadius);
//                visible.setDuration(5000);
//                Animator gone = ViewAnimationUtils.createCircularReveal(btn,cx,cy,radius,0);
//                gone.setDuration(5000);
//                if (btn.getVisibility() == View.VISIBLE){
//                    gone.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            btn.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                    gone.start();
//                }else {
//                    btn.setVisibility(View.VISIBLE);
//                    visible.start();
//                }

//                Random random = new Random();
//                names.add("mdq" + random.nextInt(100));
//                adapter.notifyDataSetChanged();

//                startActivity(new Intent(this,Android5AnimActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//                findViewById(R.id.btn_2).setVisibility(View.VISIBLE);
//                Button btn = new Button(this);
//                btn.setWidth(ViewPager.LayoutParams.WRAP_CONTENT);
//                btn.setHeight(ViewPager.LayoutParams.WRAP_CONTENT);
//                btn.setPadding(20,20,20,20);
//                btn.setText("新按钮");
//
//                ll.addView(btn);

//                findViewById(R.id.btn_2).setVisibility(View.VISIBLE);
//                findViewById(R.id.btn_3).setVisibility(View.VISIBLE);
//                findViewById(R.id.btn_4).setVisibility(View.VISIBLE);
//                findViewById(R.id.btn_5).setVisibility(View.VISIBLE);
            default:
                break;
        }
    }
}
