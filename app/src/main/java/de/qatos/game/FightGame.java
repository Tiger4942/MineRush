package de.qatos.game;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.Choreographer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import de.qatos.minerush.GameActivity;
import de.qatos.minerush.R;

public class FightGame {

    private GameActivity activity;
    private CountDownTimer timer;
    private float speed = 7f;

    private ImageView imagePf;
    private ImageView imageR;
    private ImageView imageG;
    private ImageView imageE;
    private ImageView imageS;

    private TextView text;

    private int maxLifes;
    private int lifes;

    public FightGame(final GameActivity activity) {
        maxLifes = 3;
        lifes = maxLifes;

        this.activity = activity;
        //activity.gameFinish();
        //activity.timerRight();
        imagePf = new ImageView(activity);
        imageG = new ImageView(activity);
        imageR = new ImageView(activity);
        imageE = new ImageView(activity);
        imageS = new ImageView(activity);
        text = new TextView(activity);

        activity.getLayout().addView(imagePf);
        activity.getLayout().addView(imageR);
        activity.getLayout().addView(imageG);
        activity.getLayout().addView(imageE);
        activity.getLayout().addView(imageS);
        activity.getLayout().addView(text);

        text.setY(100);
        text.setX(700);
        text.setText("Leben: " + lifes + "/" + maxLifes);

        imageE.setImageResource(R.drawable.monster);
        imageE.setX(700);
        imageE.setY(200);
        imageE.setMinimumWidth(100);
        imageE.setMinimumHeight(100);

        imageS.setImageResource(R.drawable.fight);
        imageS.setX(100);
        imageS.setY(200);
        imageS.setMinimumWidth(100);
        imageS.setMinimumHeight(100);

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

                if(Math.abs(imagePf.getY()-imageG.getY()) < 10){
                    lifes--;
                    Random r = new Random();
                    imageG.setY(r.nextInt(450)+150);
                }

                text.setText("Leben: " + lifes + "/" + maxLifes);

                if(lifes == 0) {
                    endGame();
                    activity.gameFinish();
                    activity.timerRight();
                }
            }
        });
    }

    private void endGame(){
        timer.cancel();
        activity.getLayout().setOnClickListener(null);
        activity.getLayout().removeView(imageE);
        activity.getLayout().removeView(imageS);
        activity.getLayout().removeView(imageG);
        activity.getLayout().removeView(imageR);
        activity.getLayout().removeView(imagePf);
        activity.getLayout().removeView(text);
    }

    private void restart(){
        timer.start();
    }

}
