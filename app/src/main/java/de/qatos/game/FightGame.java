package de.qatos.game;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.Choreographer;
import android.view.View;
import android.widget.ImageView;

import de.qatos.minerush.GameActivity;
import de.qatos.minerush.R;

public class FightGame {

    private GameActivity activity;
    private CountDownTimer timer;
    private float speed = 7f;

    private ImageView imagePf;
    private ImageView imageR;
    private ImageView imageG;
    private int lifes;

    public FightGame(final GameActivity activity) {

        this.activity = activity;
        //activity.gameFinish();
        //activity.timerRight();
        imagePf = new ImageView(activity);
        imageG = new ImageView(activity);
        imageR = new ImageView(activity);

        activity.getLayout().addView(imagePf);
        activity.getLayout().addView(imageR);
        activity.getLayout().addView(imageG);
        imagePf.setImageResource(R.drawable.pf);
        imagePf.setX(490);
        imagePf.setMinimumWidth(10);
        imagePf.setMinimumHeight(10);

        imageR.setImageResource(R.drawable.red);
        imageR.setX(500);
        imageR.setY(0);
        imageR.setMinimumWidth(50);
        imageR.setMinimumHeight(1000);
        imageR.setScaleType(ImageView.ScaleType.FIT_XY);

        imageG.setImageResource(R.drawable.green);
        imageG.setX(500);
        imageG.setY(250);
        imageG.setMinimumWidth(50);
        imageG.setMinimumHeight(20);
        imageG.setScaleType(ImageView.ScaleType.FIT_XY);

        imagePf.setY(100);
        timer = new CountDownTimer(1000,50) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(speed>0){
                    if(imagePf.getY()<700){
                        imagePf.setY(imagePf.getY()+speed);
                    }else{
                        imagePf.setY(700);
                        speed = -speed;
                    }
                }else{
                    if(imagePf.getY()>100){
                        imagePf.setY(imagePf.getY()+speed);
                    }else{
                        imagePf.setY(100);
                        speed = -speed;
                    }
                }


            }

            @Override
            public void onFinish() {
                restart();
            }
        }.start();

        activity.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lifes--;


                if(lifes == 0) {
                    activity.gameFinish();
                    //activity.getLayout().removeView(text);
                    activity.geteImageV().setAnimation(null);
                    activity.timerRight();
                }
            }
        });


    }
    private void restart(){
        timer.start();
    }

}
