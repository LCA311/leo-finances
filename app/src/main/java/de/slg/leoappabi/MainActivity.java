package de.slg.leoappabi;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * MainActivity.
 * <p>
 * Einzige Activity in dieser App. Zeigt die Zeit bis zum Abiball als Countdown an.
 *
 * @author Gianni
 * @version 2017.2411
 * @since 0.0.1
 */

public class MainActivity extends AppCompatActivity {

    private long[] timer;

    private TextView minutes;
    private TextView hours;
    private TextView days;
    private TextView seconds;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.activity_main);
        minutes = (TextView) findViewById(R.id.minutes);
        hours = (TextView) findViewById(R.id.hours);
        days = (TextView) findViewById(R.id.days);
        seconds = (TextView) findViewById(R.id.seconds);

        initCurrentTimerPosition();
        initTimerDisplay();
        initTimer();
        initFullscreen();

        View parent = findViewById(R.id.parent);
        AnimationDrawable drawable = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.background_drawable);
        parent.setBackground(drawable);
        drawable.setEnterFadeDuration(200);
        drawable.setExitFadeDuration(500);
        drawable.start();

        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InformationDialog(MainActivity.this)
                        .setText(getString(R.string.info_text)).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initCurrentTimerPosition();
        initTimer();
        initFullscreen();
    }

    private String getMinuteHourString(long l) {
        if (l < 10)
            return "0" + l;
        return "" + l;
    }

    private String getDayString(long l) {
        if (l < 10)
            return "00" + l;
        if (l < 100)
            return "0" + l;
        return "" + l;
    }

    private void initFullscreen() {
        final View decorView = getWindow().getDecorView();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    hideSystemBars(decorView);
                }
            });
        }
    }

    private void initCurrentTimerPosition() {
        try {
            timer = new long[4];

            long time = System.currentTimeMillis();

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.GERMANY);
            Date date = dateFormat.parse("30.06.2018 19:00:00");

            long goal = date.getTime();
            goal = (goal - time) / 1000;
            timer[3] = goal % 60;
            goal /= 60;
            timer[0] = goal % 60;
            goal /= 60;
            timer[1] = goal % 24;
            goal /= 24;
            timer[2] = goal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initTimerDisplay() {
        minutes.setText(getMinuteHourString(timer[0]));
        hours.setText(getMinuteHourString(timer[1]));
        days.setText(getDayString(timer[2]));
        seconds.setText(getMinuteHourString(timer[3]));
    }

    private void initTimer() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                initCurrentTimerPosition();
                initTimerDisplay();
                handler.postDelayed(this, 50);

            }
        };
        handler.post(runnable);
    }

    private void hideSystemBars(final View decorView) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        };
        handler.postDelayed(runnable, 100);
    }

}
