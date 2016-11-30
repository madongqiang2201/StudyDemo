package com.madqiang.studydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class Android5AnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_android5_anim);
    }
}
