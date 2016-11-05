package com.iut.german_marabel.icongame;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class mlpActivity extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor edt = SP.edit();
        edt.putString("iconMode", "3");
        edt.commit();

        View v = inflater.inflate(R.layout.fragment_mlp, container, false);
        return v;
    }
}
