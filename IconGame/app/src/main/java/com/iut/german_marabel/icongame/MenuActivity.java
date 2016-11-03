package com.iut.german_marabel.icongame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        final Button play = (Button) findViewById(R.id.play);
        final Button option = (Button) findViewById(R.id.option);
        final Button score = (Button) findViewById(R.id.score);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, GameActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, MyPreferenceActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, GameActivity.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

        //recupération des data de setting exemple
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String strUserName = SP.getString("username","");
        boolean bHardMode = SP.getBoolean("difficulty",false);
        String NbIcon = SP.getString("nbIcons","1");

        Log.d("",strUserName);
        Log.d("",NbIcon);



    }


}
