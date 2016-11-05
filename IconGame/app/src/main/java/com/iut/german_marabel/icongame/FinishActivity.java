package com.iut.german_marabel.icongame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        String score = "0";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getString("score");
        }

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edt = SP.edit();

        String strUserName = SP.getString("username","");


        TextView top = (TextView)findViewById(R.id.displayNewRecord);
        if (Integer.parseInt(score) > SP.getInt("highScore", 0)){
            top.setText("Bravo " + strUserName + " ! Nouveau reccord !");
            edt.putInt("highScore",  Integer.parseInt(score));
            edt.commit();
        }
        else{
            top.setText("Pas mal " + strUserName + "... Mais il va falloir s'entrainer !");
        }

        TextView tscore = (TextView)findViewById(R.id.Score);
        tscore.setText(Integer.parseInt(score) + " Points !");

        final Button playAgain = (Button) findViewById(R.id.playAgain);
        final Button home = (Button) findViewById(R.id.backHome);

        playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FinishActivity.this, GameActivity.class);
                FinishActivity.this.startActivity(myIntent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FinishActivity.this, MenuActivity.class);
                FinishActivity.this.startActivity(myIntent);
            }
        });
    }
    @Override
    public void onBackPressed() {}

}
