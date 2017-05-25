package de.qatos.minerush;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MenuActivity extends Activity {

    public static int WIDTH, HEIGHT;

    private RelativeLayout layout;
    private Display display;
    private Point size;

    private Button play, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        layout = (RelativeLayout) findViewById(R.id.activity_menu);

        display = getWindowManager().getDefaultDisplay();

        // display size in pixels
        size = new Point();
        display.getSize(size);
        WIDTH = size.x;
        HEIGHT = size.y;

        play = new Button(this);
        play.setText("Play");
        play.setX(0);
        play.setY(HEIGHT / 5);
        play.setWidth(WIDTH / 5);
        play.setHeight(HEIGHT / 6);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        exit = new Button(this);
        exit.setText("Exit");
        exit.setX(0);
        exit.setY(play.getY() + HEIGHT / 4);
        exit.setWidth(WIDTH / 5);
        exit.setHeight(HEIGHT / 6);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        layout.addView(play);
        layout.addView(exit);

    }
}
