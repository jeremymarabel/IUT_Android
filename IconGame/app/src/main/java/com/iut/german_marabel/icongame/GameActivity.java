package com.iut.german_marabel.icongame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Vibrator;
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

                scoreTextView.setText(""+score);

                Calendar c = Calendar.getInstance();
                float diff = (c.getTimeInMillis()-lastRefreshTime) / 1000f;
                remainingTime = remainingTime - diff;
                lastRefreshTime = c.getTimeInMillis();


                float progress;
                if (remainingTime <0)
                {
                    progressBar.setProgress(0);
                    Intent myIntent = new Intent(GameActivity.this, FinishActivity.class);
                    myIntent.putExtra("score", score);
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
                v.setBackgroundColor(Color.GREEN);
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
                v.setBackgroundColor(Color.RED);
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
                v1.setBackgroundColor(Color.YELLOW);
                v1.setOnClickListener(successClickListener);
            } else {
                v1.setBackgroundColor(Color.BLUE);
                v1.setOnClickListener(failClickListener);
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


}
