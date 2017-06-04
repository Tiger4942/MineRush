package de.qatos.game;

import android.content.Context;

import de.qatos.minerush.GameActivity;

public class FightGame {

    private GameActivity activity;

    public FightGame(final GameActivity activity) {

        this.activity = activity;
        activity.gameFinish();
        activity.timerRight();


    }

}
