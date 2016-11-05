package com.iut.german_marabel.icongame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

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
}
