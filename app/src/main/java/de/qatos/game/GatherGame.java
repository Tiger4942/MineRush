package de.qatos.game;

import android.content.Context;

import de.qatos.minerush.GameActivity;

public class GatherGame {

    private GameActivity activity;

    public GatherGame(final GameActivity activity) {

        this.activity = activity;
        activity.gameFinish();
        activity.timerRight();


    }

}
