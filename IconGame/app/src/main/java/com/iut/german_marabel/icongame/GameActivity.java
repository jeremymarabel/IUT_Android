package com.iut.german_marabel.icongame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    final ArrayList<ImageView> images = new ArrayList<>();



    private float maxTime = 10;
    private long lastRefreshTime = 0;
    private float remainingTime = 10;
    private int NbIcon = 3;
    private float addingTime = 2;
    private double coefficientTime = 0.02;
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        NbIcon = Integer.parseInt(SP.getString("nbIcons","3"));
        boolean bHardMode = SP.getBoolean("difficulty",false);
        if (bHardMode)
        {
            coefficientTime = 0.05;
        }

        //Remove notification bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);


        progressBar.setProgress(100);
        scoreTextView.setText("0");

        getAllIcons(this);

        Calendar c = Calendar.getInstance();
        lastRefreshTime = c.getTimeInMillis();

        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {

                scoreTextView.setText(String.format("%d",score));

                Calendar c = Calendar.getInstance();
                float diff = (c.getTimeInMillis()-lastRefreshTime) / 1000f;
                remainingTime = remainingTime - diff;
                lastRefreshTime = c.getTimeInMillis();


                float progress;
                if (remainingTime <0)
                {
                    progressBar.setProgress(0);
                    Intent myIntent = new Intent(GameActivity.this, FinishActivity.class);
                    myIntent.putExtra("score", "" + score);
                    GameActivity.this.startActivity(myIntent);
                }
                else
                {
                    progress = (remainingTime * 100 / maxTime);
                    progressBar.setProgress((int)progress);
                    handler.postDelayed(this,100);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        remainingTime = -1;
    }

    public void getAllIcons(Context c)
    {
        Random r1 = new Random();

        Boolean firstIconIsTrue = r1.nextInt(2) == 0;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String IconSet = SP.getString("iconMode", "2");

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


        View.OnClickListener successClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remainingTime = remainingTime + addingTime;
                addingTime =(float) (addingTime - addingTime * coefficientTime);
                if (remainingTime > maxTime)
                {
                    maxTime = remainingTime;
                }
                score = score + 160;
                getAllIcons(getBaseContext());
            }
        };

        View.OnClickListener failClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
                score = score - 33;
                getAllIcons(getBaseContext());
            }
        };



        for (int i = 0 ; i<NbIcon ; i++)
        {
            ImageView v1 = getNewIcon(c, iconSize, screenSize);

            images.add(i,v1);
            if (i==0)
            {

                Drawable drw =  getDrawable(IconSet,firstIconIsTrue,r1.nextInt(7)+1);
                v1.setBackground(drw);
                v1.setOnClickListener(successClickListener);
            } else {

                Drawable drw =  getDrawable(IconSet,!firstIconIsTrue,r1.nextInt(7)+1);
                v1.setBackground(drw);
                v1.setOnClickListener(failClickListener);
            }
            switch (IconSet)
            {
                case "1":
                    v1.setScaleX(0.75f);
                    v1.setScaleY(0.75f);
                    break;
                case "2":
                    v1.setScaleX(2);
                    v1.setScaleY(2);
                    break;
                case "3":
                    v1.setScaleX(1);
                    v1.setScaleY(1);
                    break;
            }
            rl.addView(v1);
        }
    }

    public ImageView getNewIcon(Context c, int iconSize, Point screenSize)
    {
        ImageView v = new ImageView(c);
        int maxX = screenSize.x-iconSize;
        int maxY = screenSize.y-iconSize;
        boolean positionNotFound = true;

        int x = 0;
        int y = 0;
        int loop = 0;
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
            loop++;
            if(loop>10000)
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


    Drawable getDrawable(String setIcon, boolean isFirstSet, int number)
    {
        Drawable result = ContextCompat.getDrawable(this, R.drawable.pkm1);
        if (isFirstSet)
        {
            switch (setIcon)
            {
                case "1"://drWho
                    switch (number)
                    {
                        case 1:
                            result = ContextCompat.getDrawable(this, R.drawable.dw1);
                            break;
                        case 2:
                            result = ContextCompat.getDrawable(this, R.drawable.dw2);
                            break;
                        case 3:
                            result = ContextCompat.getDrawable(this, R.drawable.dw3);
                            break;
                        case 4:
                            result = ContextCompat.getDrawable(this, R.drawable.dw4);
                            break;
                        case 5:
                            result = ContextCompat.getDrawable(this, R.drawable.dw5);
                            break;
                        case 6:
                            result = ContextCompat.getDrawable(this, R.drawable.dw6);
                            break;
                        case 7:
                            result = ContextCompat.getDrawable(this, R.drawable.dw7);
                            break;
                    }
                    break;
                case "2"://pkm
                    switch (number)
                    {
                        case 1:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm1);
                            break;
                        case 2:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm2);
                            break;
                        case 3:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm3);
                            break;
                        case 4:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm4);
                            break;
                        case 5:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm5);
                            break;
                        case 6:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm6);
                            break;
                        case 7:
                            result = ContextCompat.getDrawable(this, R.drawable.pkm7);
                            break;
                    }
                    break;
                case "3"://mlp
                    switch (number)
                    {
                        case 1:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp1);
                            break;
                        case 2:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp2);
                            break;
                        case 3:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp3);
                            break;
                        case 4:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp4);
                            break;
                        case 5:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp5);
                            break;
                        case 6:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp6);
                            break;
                        case 7:
                            result = ContextCompat.getDrawable(this, R.drawable.mlp7);
                            break;
                    }
                    break;
            }
        }
        else
        {
            switch (setIcon)
            {
                case "1"://drWho
                    switch (number)
                    {
                        case 1:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb1);
                            break;
                        case 2:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb2);
                            break;
                        case 3:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb3);
                            break;
                        case 4:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb4);
                            break;
                        case 5:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb5);
                            break;
                        case 6:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb6);
                            break;
                        case 7:
                            result = ContextCompat.getDrawable(this, R.drawable.dwb7);
                            break;
                    }
                    break;
                case "2"://pkm
                    switch (number)
                    {
                        case 1:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm1);
                            break;
                        case 2:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm2);
                            break;
                        case 3:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm3);
                            break;
                        case 4:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm4);
                            break;
                        case 5:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm5);
                            break;
                        case 6:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm6);
                            break;
                        case 7:
                            result = ContextCompat.getDrawable(this, R.drawable.epkm7);
                            break;
                    }
                    break;
                case "3"://mlp
                    switch (number)
                    {
                        case 1:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp1);
                            break;
                        case 2:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp2);
                            break;
                        case 3:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp3);
                            break;
                        case 4:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp4);
                            break;
                        case 5:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp5);
                            break;
                        case 6:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp6);
                            break;
                        case 7:
                            result = ContextCompat.getDrawable(this, R.drawable.bmlp7);
                            break;
                    }
                    break;
            }
        }
        return result;
    }


}
