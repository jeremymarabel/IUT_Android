package com.iut.german_marabel.icongame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Debug;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Console;
import java.util.HashMap;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    final HashMap<Integer,ImageView> images = new HashMap<Integer,ImageView>();

    int nbIcons = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        size.y = size.y - statusBarHeight*4;


        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        String strUserName = SP.getString("username","");
//        boolean bHardMode = SP.getBoolean("difficulty",false);
        String NbIcon = SP.getString("nbIcons","1");


        View.OnClickListener succesClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.GREEN);
            }
        };

        View.OnClickListener failClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
            }
        };

        for (int i = 0 ; i<Integer.parseInt(NbIcon) ; i++)
        {
            ImageView v1 = getNewIcon(this,rl,100,size);

            images.put(i,v1);
            if (i==0)
            {
                v1.setBackgroundColor(Color.YELLOW);
                v1.setOnClickListener(succesClickListener);
            } else {
                v1.setBackgroundColor(Color.BLUE);
                v1.setOnClickListener(failClickListener);
            }

            rl.addView(v1);
        }




//        final Handler handler = new Handler();
//        handler.post(new Runnable(){
//            @Override
//            public void run() {
//                View v2 = images.get(0);
//                v2.setLeft(v2.getLeft()+6);
//                v2.setRight(v2.getRight()+6);
//                handler.postDelayed(this,1);
//            }
//        });

    }

    public ImageView getNewIcon(Context c, RelativeLayout rl, int size, Point screenSize)
    {
        ImageView v = new ImageView(c);
        int maxX = screenSize.x-size;
        int maxY = screenSize.y-size;
        boolean positionNotFound = true;

        int x = 0;
        int y = 0;
        int boucle = 0;
        while(positionNotFound)
        {
            Random r = new Random();
            positionNotFound = false;
            x = r.nextInt(maxX);
            y = r.nextInt(maxY);
            for (int i = 0 ; i < images.size() ; i++)
            {
                ImageView oneImage = images.get(i);
                float t = oneImage.getX();
                if(Math.abs(oneImage.getX() - x) < size || Math.abs(oneImage.getY() - y) < size)
                {
                    positionNotFound = true;
                    break;
                }
            }
            boucle++;
            if(boucle>100)
            {
                Log.d(null,"boucle infinie");
                break;
            }
        }
        v.setX(x);
        v.setY(y);
        v.setMinimumWidth(size);
        v.setMaxWidth(size);
        v.setMinimumHeight(size);
        v.setMaxHeight(size);
        return v;
    }


}
