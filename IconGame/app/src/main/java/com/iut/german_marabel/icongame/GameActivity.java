package com.iut.german_marabel.icongame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    final ArrayList<ImageView> images = new ArrayList<ImageView>();



    final float[] maxTime = {10};
    //final long startTime = c.getTimeInMillis();
    final long[] lastRefreshTime = {0};
    final float[] remainingTime = {10};
    final int[] NbIcon = {3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        NbIcon[0] = Integer.parseInt(SP.getString("nbIcons","3"));
        String strUserName = SP.getString("username","");
        boolean bHardMode = SP.getBoolean("difficulty",false);

        //Remove notification bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setProgress(100);

        getAllIcons(this);

        Calendar c = Calendar.getInstance();
        lastRefreshTime[0] = c.getTimeInMillis();

        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {

                Calendar c = Calendar.getInstance();
                float diff = (c.getTimeInMillis()-lastRefreshTime[0]) / 1000f;
                remainingTime[0] = remainingTime[0] - diff;
                lastRefreshTime[0] = c.getTimeInMillis();


                float progress = 0;
                if (remainingTime[0] <0)
                {
                    progressBar.setProgress(0);
                }
                else
                {
                    progress = (remainingTime[0] * 100 / maxTime[0]);
                    progressBar.setProgress((int)progress);
                    handler.postDelayed(this,100);
                }
            }
        });

    }

    public void getAllIcons(Context c)
    {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relLayout);

        for (int i = 0 ; i < images.size() ; i++)
        {
            rl.removeView(images.get(i));
        }
        images.clear();

        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        screenSize.y = screenSize.y - statusBarHeight*4;

        int iconSize = (int)(Math.min(screenSize.x, screenSize.y) * 0.10);


        View.OnClickListener succesClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.GREEN);
                remainingTime[0] = remainingTime[0] + 2;
                if (remainingTime[0] > maxTime[0])
                {
                    maxTime[0] = remainingTime[0];
                }
                getAllIcons(getBaseContext());
            }
        };

        View.OnClickListener failClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.RED);
            }
        };



        for (int i = 0 ; i<NbIcon[0] ; i++)
        {
            ImageView v1 = getNewIcon(c,rl,iconSize,screenSize);

            images.add(i,v1);
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
    }

    public ImageView getNewIcon(Context c, RelativeLayout rl, int iconSize, Point screenSize)
    {
        ImageView v = new ImageView(c);
        int maxX = screenSize.x-iconSize;
        int maxY = screenSize.y-iconSize;
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
                if(Math.abs(oneImage.getX() - x) < iconSize || Math.abs(oneImage.getY() - y) < iconSize)
                {
                    positionNotFound = true;
                    break;
                }
            }
            boucle++;
            if(boucle>10000)
            {
                Log.d("debug","boucle infinie");
                break;
            }
        }
        v.setX(x);
        v.setY(y);
        v.setMinimumWidth(iconSize);
        v.setMaxWidth(iconSize);
        v.setMinimumHeight(iconSize);
        v.setMaxHeight(iconSize);
        return v;
    }


}
