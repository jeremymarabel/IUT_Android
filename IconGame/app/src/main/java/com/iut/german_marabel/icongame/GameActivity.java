package com.iut.german_marabel.icongame;

import android.graphics.Color;
import android.os.Debug;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Console;
import java.util.HashMap;

public class GameActivity extends AppCompatActivity {

    final HashMap<Integer,View> images = new HashMap<Integer,View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);



        View v1 = new ImageView(this);
        v1.setMinimumWidth(100);
        v1.setMinimumHeight(100);
        v1.setBackgroundColor(Color.BLUE);
        images.put(0,v1);


        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
            }
        });



        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relLayout);

        rl.addView(v1);


        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                View v2 = images.get(0);
                v2.setLeft(v2.getLeft()+6);
                v2.setRight(v2.getRight()+6);
                handler.postDelayed(this,1);
            }
        });

    }




}
