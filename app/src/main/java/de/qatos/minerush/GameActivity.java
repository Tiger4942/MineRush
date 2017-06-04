package de.qatos.minerush;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import de.qatos.game.FightGame;
import de.qatos.game.GatherGame;
import de.qatos.game.LootGame;
import de.qatos.game.MineGame;

public class GameActivity extends Activity {

    public int WIDTH = MenuActivity.WIDTH;
    public int HEIGHT = MenuActivity.HEIGHT;

    private int playerImgY, playerImgX;
    private int enemyImgY, enemyImgX;

    private int pIndex; // Player Index
    private int eIndex; // Enemy Index

    private CountDownTimer timer;
    private TextView time, test;
    private RelativeLayout layout;

    private ImageView pImageV;
    private ImageView eImageV;

    private long countdownInit = 5000;
    private long countdownCurrent = 5000;

    private MineGame mineGame;
    private LootGame lootGame;
    private FightGame fightGame;
    private GatherGame gatherGame;

    private View.OnClickListener layoutClick;

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

        Random player = new Random(); // Player Index Random
        pIndex = player.nextInt(4 - 0) + 0;

        switchPIndex();

        layout.setOnClickListener(layoutClick = new View.OnClickListener() {
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

        timer = new CountDownTimer(countdownInit, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                onTickTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                onFinishTimer();
            }
        }.start();
    }

    public void setEnemyVisible(){
        eImageV.setVisibility(View.VISIBLE);
    }

    public void onFinishTimer() {
        if(pIndex == eIndex) {
            gameStart();
            timer.cancel();
            if(pIndex == 0) {
                mineRight();
            }else if(pIndex == 1) {
                lootRight();
            }else if(pIndex == 2) {
                fightRight();
            }else if(pIndex == 3){
                gatherRight();
            }
        }else{
            timerFalse();
        }

    }

    private void mineRight() {
        mineGame = new MineGame(this);
    }

    private void lootRight() {
        lootGame = new LootGame(this);
    }

    private void fightRight() {
        fightGame = new FightGame(this);
    }

    private void gatherRight() {
        gatherGame = new GatherGame(this);
    }

    /*public void mineRight(ImageView pImage, ImageView eImage) {
        pImage.setVisibility(View.INVISIBLE);
        eImage.setMinimumWidth(300);
        eImage.setMinimumHeight(300);
        eImage.setMaxWidth(300);
        eImage.setMaxHeight(300);
        eImage.setX(WIDTH / 2 - 300 / 2);
        eImage.setY(HEIGHT / 2 - 300 / 2);
    }*/

    public void gameStart(){
        pImageV.setVisibility(View.INVISIBLE);
        eImageV.setVisibility(View.INVISIBLE);
        test.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
    }

    public void gameFinish() {
        pImageV.setVisibility(View.VISIBLE);
        eImageV.setVisibility(View.VISIBLE);
        test.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        eImageV.setX(enemyImgX);
        eImageV.setY(enemyImgY);
        eImageV.setMinimumWidth(100);
        eImageV.setMinimumHeight(100);
        eImageV.setMaxWidth(100);
        eImageV.setMaxHeight(100);

        getLayout().setOnClickListener(layoutClick);
    }

    public void onTickTimer(long millisUntilFinished) {
        time.setText("Remaining: " + millisUntilFinished / 1000);

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
    public void switchPIndex() {
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
    public void switchEIndex() {

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

    public void timerRight()  {
        test.setText("INDEX: " + pIndex + " RICHTIG!");

        Random nEnemy = new Random();
        eIndex = nEnemy.nextInt(4);

        switchEIndex();
        timer = new CountDownTimer(countdownCurrent, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                onTickTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                onFinishTimer();
            }
        }.start();
    }

    private void timerFalse() {
        test.setText("INDEX: " + pIndex + " FALSCH!");

        Random nEnemy = new Random();
        eIndex = nEnemy.nextInt(4);

        switchEIndex();
        countdownCurrent = countdownInit;
        timer = new CountDownTimer(countdownCurrent, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                onTickTimer(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                onFinishTimer();
            }
        }.start();
    }

    public RelativeLayout getLayout() {
        return layout;
    }

    public int getpIndex() {
        return pIndex;
    }

    public int geteIndex() {
        return eIndex;
    }

    public CountDownTimer getTimer() {
        return timer;
    }

    public int getEnemyImgX() {
        return enemyImgX;
    }

    public int getEnemyImgY() {
        return enemyImgY;
    }

    public ImageView getpImageV() {
        return pImageV;
    }

    public ImageView geteImageV() {
        return eImageV;
    }
}
