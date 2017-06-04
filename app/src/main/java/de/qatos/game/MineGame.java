package de.qatos.game;

import android.app.Activity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import de.qatos.minerush.GameActivity;

public class MineGame {

    private int ores;
    private TextView text;

    private GameActivity activity;

    private RotateAnimation oreLeft, oreRight, oreCenter;
    private AnimationSet oreSet;

    public MineGame(final GameActivity activity) {
        this.activity = activity;

        ores = 5;
        text = new TextView(activity);
        text.setX(0);
        text.setY(0);
        text.setWidth(100);
        text.setHeight(100);
        activity.getLayout().addView(text);

        //activity.mineRight(activity.getpImageV(), activity.geteImageV());
        setupGame();
        setupAnimation();

        activity.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.geteImageV().startAnimation(oreSet);

                ores--;

                text.setText("Ores: " + ores);

                if(ores == 0) {
                    activity.gameFinish();
                    activity.getLayout().removeView(text);
                    activity.geteImageV().setAnimation(null);
                    activity.timerRight();
                }
            }
        });
    }

    private void setupGame(){
        activity.geteImageV().setVisibility(View.VISIBLE);
        activity.geteImageV().setMinimumWidth(300);
        activity.geteImageV().setMinimumHeight(300);
        activity.geteImageV().setMaxWidth(300);
        activity.geteImageV().setMaxHeight(300);
        activity.geteImageV().setX(activity.WIDTH / 2 - 300 / 2);
        activity.geteImageV().setY(activity.HEIGHT / 2 - 300 / 2);
        activity.geteImageV().setVisibility(View.VISIBLE);
    }

    private void setupAnimation(){
        oreLeft = new RotateAnimation(0f, 10.0f, RotateAnimation.RELATIVE_TO_SELF, 1.0f, RotateAnimation.RELATIVE_TO_SELF, 1.0f);
        oreLeft.setInterpolator(new LinearInterpolator());
        oreLeft.setRepeatCount(0);
        oreLeft.setDuration(100);
        oreLeft.setFillAfter(true);

        oreRight = new RotateAnimation(10.0f, -10.0f, RotateAnimation.RELATIVE_TO_SELF, 1.0f, RotateAnimation.RELATIVE_TO_SELF, 1.0f);
        oreRight.setInterpolator(new LinearInterpolator());
        oreRight.setRepeatCount(0);
        oreRight.setStartOffset(100);
        oreRight.setDuration(100);
        oreRight.setFillAfter(true);

        oreCenter = new RotateAnimation(-10.0f, 0.0f, RotateAnimation.RELATIVE_TO_SELF, 1.0f, RotateAnimation.RELATIVE_TO_SELF, 1.0f);
        oreCenter.setInterpolator(new LinearInterpolator());
        oreCenter.setRepeatCount(0);
        oreCenter.setStartOffset(200);
        oreCenter.setDuration(100);
        oreCenter.setFillAfter(true);

        oreSet = new AnimationSet(true);

        oreSet.addAnimation(oreLeft);
        oreSet.addAnimation(oreRight);
        oreSet.addAnimation(oreCenter);
        oreSet.setDuration(300);
    }

}
