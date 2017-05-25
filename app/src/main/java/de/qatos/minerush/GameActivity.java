package de.qatos.minerush;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends Activity {

    private int WIDTH = MenuActivity.WIDTH;
    private int HEIGHT = MenuActivity.HEIGHT;

    private CountDownTimer timer;
    private TextView time;
    private RelativeLayout layout;

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

        layout = (RelativeLayout) findViewById(R.id.activity_game);

        layout.addView(time);

        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Remaining: " + millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                time.setText("Finish!");
            }
        }.start();
    }
}
