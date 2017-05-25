package de.qatos.minerush;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
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

    private ImageView mineV, lootV, fightV, gatherV;
    private ImageView oreV, chestV, monsterV, plantV;

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
        imageHide();

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
                    test.setText("RICHTIG!");

                    Random nEnemy = new Random();
                    eIndex = nEnemy.nextInt(4 - 0) + 0;

                    switchEIndex();

                    timer.start();
                }else{
                    test.setText("FALSCH!");

                    Random nEnemy = new Random();
                    eIndex = nEnemy.nextInt(4 - 0) + 0;

                    switchEIndex();

                    timer.start();
                }

            }
        }.start();
    }

    private void imageLoad() {

        mineV = new ImageView(this);
        mineV.setImageResource(R.drawable.mine);
        mineV.setX(playerImgX);
        mineV.setY(playerImgY);

        lootV = new ImageView(this);
        lootV.setImageResource(R.drawable.loot);
        lootV.setX(playerImgX);
        lootV.setY(playerImgY);

        fightV = new ImageView(this);
        fightV.setImageResource(R.drawable.fight);
        fightV.setX(playerImgX);
        fightV.setY(playerImgY);

        gatherV = new ImageView(this);
        gatherV.setImageResource(R.drawable.gather);
        gatherV.setX(playerImgX);
        gatherV.setY(playerImgY);

        oreV = new ImageView(this);
        oreV.setImageResource(R.drawable.ore);
        oreV.setX(enemyImgX);
        oreV.setY(enemyImgY);

        chestV = new ImageView(this);
        chestV.setImageResource(R.drawable.chest);
        chestV.setX(enemyImgX);
        chestV.setY(enemyImgY);

        monsterV = new ImageView(this);
        monsterV.setImageResource(R.drawable.monster);
        monsterV.setX(enemyImgX);
        monsterV.setY(enemyImgY);

        plantV = new ImageView(this);
        plantV.setImageResource(R.drawable.plant);
        plantV.setX(enemyImgX);
        plantV.setY(enemyImgY);

        layout.addView(mineV);
        layout.addView(lootV);
        layout.addView(fightV);
        layout.addView(gatherV);
        layout.addView(oreV);
        layout.addView(chestV);
        layout.addView(monsterV);
        layout.addView(plantV);
    }

    // PLAYER IMAGE HIDE
    private void pImageHide() {
        mineV.setVisibility(View.INVISIBLE);
        lootV.setVisibility(View.INVISIBLE);
        fightV.setVisibility(View.INVISIBLE);
        gatherV.setVisibility(View.INVISIBLE);

    }

    // ENEMY IMAGE HIDE
    private void eImageHide() {
        oreV.setVisibility(View.INVISIBLE);
        chestV.setVisibility(View.INVISIBLE);
        monsterV.setVisibility(View.INVISIBLE);
        plantV.setVisibility(View.INVISIBLE);
    }

    private void imageHide() {
        mineV.setVisibility(View.INVISIBLE);
        lootV.setVisibility(View.INVISIBLE);
        fightV.setVisibility(View.INVISIBLE);
        gatherV.setVisibility(View.INVISIBLE);
        oreV.setVisibility(View.INVISIBLE);
        chestV.setVisibility(View.INVISIBLE);
        monsterV.setVisibility(View.INVISIBLE);
        plantV.setVisibility(View.INVISIBLE);
    }

    private void switchPIndex() {
        switch (pIndex) {
            case 0:
                mineV.setVisibility(View.VISIBLE);
                break;

            case 1:
                lootV.setVisibility(View.VISIBLE);
                break;

            case 2:
                fightV.setVisibility(View.VISIBLE);
                break;

            case 3:
                gatherV.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void switchEIndex() {
        switch(eIndex) {
            case 0:
                eImageHide();
                oreV.setVisibility(View.VISIBLE);
                break;

            case 1:
                eImageHide();
                chestV.setVisibility(View.VISIBLE);
                break;

            case 2:
                eImageHide();
                monsterV.setVisibility(View.VISIBLE);
                break;

            case 3:
                eImageHide();
                plantV.setVisibility(View.VISIBLE);
                break;
        }
    }
}
