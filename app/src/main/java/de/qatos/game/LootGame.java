package de.qatos.game;

import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.qatos.minerush.GameActivity;
import de.qatos.minerush.R;

public class LootGame implements View.OnTouchListener {

    private ImageView coin;
    private ImageView bag;

    private TextView test;

    private int xDelta;
    private int yDelta;
    private int lootTime = 5000;

    private int loot, coins;

    private GameActivity activity;

    private CountDownTimer timer;

    public LootGame(final GameActivity activity) {

        this.activity = activity;

        loot = 0;
        coins = 0;


        test = new TextView(activity);
        test.setText("PENIS!");


        coin = new ImageView(activity);
        coin.setImageResource(R.drawable.coin);
        coin.setMinimumWidth(70);
        coin.setMinimumHeight(70);
        coin.setOnTouchListener(this);
        coin.setVisibility(View.INVISIBLE);

        bag = new ImageView(activity);
        bag.setImageResource(R.drawable.bag);
        bag.setX(activity.getEnemyImgX());
        bag.setY(activity.getEnemyImgY());

        activity.geteImageV().setVisibility(View.VISIBLE);
        activity.geteImageV().setX(activity.getPlayerImgX());
        activity.geteImageV().setY(activity.getPlayerImgY());

        activity.getLayout().addView(bag);
        activity.getLayout().addView(coin);

        activity.geteImageV().setClickable(true);
        activity.geteImageV().setFocusable(true);
        activity.geteImageV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coins == 0) {
                    test.setText("POPOPOPOPOPOPOPO!");
                    coin.setVisibility(View.VISIBLE);
                    coin.setX(activity.geteImageV().getX() / 2 + 35);
                    coin.setY(activity.geteImageV().getY() / 2 + 35);
                    coins++;
                }else{
                    return;
                }
            }
        });

        timer = new CountDownTimer(lootTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if(coin.getX() > bag.getX() && coin.getX() < bag.getX() + bag.getWidth() && coin.getY() > bag.getY() && coin.getY() < bag.getY() + bag.getHeight()) {
                    coins = 0;
                    loot++;
                    coin.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFinish() {
                activity.getLayout().removeView(bag);
                activity.getLayout().removeView(coin);
                activity.gameFinish();
                activity.timerRight();
                timer.cancel();
            }
        }.start();

    }

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        switch(event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                xDelta = X - lParams.leftMargin;
                yDelta = Y - lParams.topMargin;
                break;

            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                break;

            case MotionEvent.ACTION_POINTER_UP:
                break;

            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - xDelta;
                layoutParams.topMargin = Y - yDelta;
                layoutParams.rightMargin = 0;
                layoutParams.bottomMargin = 0;
                view.setLayoutParams(layoutParams);
                break;

        }
        activity.getLayout().invalidate();
        return true;
    }

}
