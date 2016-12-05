package com.iut.german_marabel.icongame;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by arnaudgerman on 05/12/2016.
 */

public class Score extends RealmObject {




    @PrimaryKey
    private int score;

    public int getScore() { return score; }
    public void setScore(int _score) { score = _score; }

}
