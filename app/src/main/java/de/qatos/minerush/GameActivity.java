package de.qatos.minerush;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends Activity {

    private int WIDTH = MenuActivity.WIDTH;
    private int HEIGHT = MenuActivity.HEIGHT;

    private int playerImgY, playerImgX;
    private int enemyImgY, enemyImgX;

    private int pIndex; // Player Index
    private int eIndex; // Enemy Index

    private CountDownTimer timer;
    private TextView time, test;
    private RelativeLayout layout;

    private ImageView pImageV;
    private ImageView eImageV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        time = new TextView(this);
        time.setX(WIDTH / 2 - (WIDTH / 4) / 2);
        time.setY(HEIGHT / 10);
        time.setWidth(WIDTH / 4);
        time.setHeight(HEIGHT / 9);
        time.setTextColor(Color.BLACK);

        test = new TextView(this);
        test.setX(time.getX());
        test.setY(time.getY() + 50);
        test.setWidth(WIDTH / 4);
        test.setHeight(HEIGHT / 9);
        test.setTextColor(Color.BLACK);

        layout = (RelativeLayout) findViewById(R.id.activity_game);
        layout.addView(time);
        layout.addView(test);

        playerImgY = HEIGHT / 3;
        playerImgX = 50;

        enemyImgY = playerImgY;
        enemyImgX = WIDTH / 2 + WIDTH / 5;

        imageLoad();

        Random enemy = new Random(); // Enemy Index Random
        eIndex = enemy.nextInt(4 - 0) + 0;

        Random player = new Random(); // Player Index Random
        pIndex = player.nextInt(4 - 0) + 0;

        switchPIndex();

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pIndex++;
                if(pIndex == 4) {
                    pIndex = 0;
                }

                test.setText("INDEX: " + pIndex);

                switchPIndex();
            }
        });

        switchEIndex();

        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Remaining: " + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                if(pIndex == eIndex) {
                    //test.setText("RICHTIG!");

                    Random nEnemy = new Random();
                    eIndex = nEnemy.nextInt(4 - 0) + 0;

                    switchEIndex();

                    timer.start();
                }else{
                    //test.setText("FALSCH!");

                    Random nEnemy = new Random();
                    eIndex = nEnemy.nextInt(4 - 0) + 0;

                    switchEIndex();

                    timer.start();
                }

            }
        }.start();
    }

    private void imageLoad() {

        pImageV = new ImageView(this);
        pImageV.setImageResource(R.drawable.mine);
        pImageV.setX(playerImgX);
        pImageV.setY(playerImgY);

        eImageV = new ImageView(this);
        eImageV.setImageResource(R.drawable.ore);
        eImageV.setX(enemyImgX);
        eImageV.setY(enemyImgY);

        layout.addView(pImageV);
        layout.addView(eImageV);

    }

    // switch player Index
    private void switchPIndex() {
        switch (pIndex) {
            case 0:
                pImageV.setImageResource(R.drawable.mine);
                break;

            case 1:
                pImageV.setImageResource(R.drawable.loot);
                break;

            case 2:
                pImageV.setImageResource(R.drawable.fight);
                break;

            case 3:
                pImageV.setImageResource(R.drawable.gather);
                break;
        }
    }

    // switch enemy Index
    private void switchEIndex() {
        switch(eIndex) {
            case 0:
                eImageV.setImageResource(R.drawable.ore);
                break;

            case 1:
                eImageV.setImageResource(R.drawable.chest);
                break;

            case 2:
                eImageV.setImageResource(R.drawable.monster);
                break;

            case 3:
                eImageV.setImageResource(R.drawable.plant);
                break;
        }
    }
}
