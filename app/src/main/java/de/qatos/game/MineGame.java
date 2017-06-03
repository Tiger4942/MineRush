package de.qatos.game;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.qatos.minerush.GameActivity;

public class MineGame {

    private int ores;
    private TextView text;

    public MineGame(final GameActivity activity) {

        ores = 5;
        text = new TextView(activity);
        text.setX(0);
        text.setY(0);
        text.setWidth(100);
        text.setHeight(100);
        activity.getLayout().addView(text);

        activity.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ores--;

                text.setText("Ores: " + ores);

                if(ores == 0) {
                    activity.getLayout().setOnClickListener(activity.getLayoutClick());
                    activity.timerRight();
                }else{
                    return;
                }
            }
        });

    }

}
