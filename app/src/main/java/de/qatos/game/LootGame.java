package de.qatos.game;

import android.content.Context;

import de.qatos.minerush.GameActivity;

public class LootGame {

    private GameActivity activity;

    public LootGame(final GameActivity activity) {

        this.activity = activity;
        activity.gameFinish();
        activity.timerRight();


    }

}
