package com.ardic.android.iotignite.greenhouse.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ardic.android.iotignite.greenhouse.R;

/**
 * Created by pmirkelam on 21.07.2017.
 */

public class SplashScreenActivity extends Activity {


    private Handler animationHandler;

    private Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {
            startLoginActivity();
            SplashScreenActivity.this.finish();
        }
    };

    private long duration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        duration = 1000 + getResources().getInteger(R.integer.anim_frame_duration_first) + (18 * getResources().getInteger(R.integer.anim_frame_duration));

        animationHandler = new Handler(Looper.getMainLooper());
        animationHandler.postDelayed(animationRunnable, duration);

        ImageView mImageViewFilling = (ImageView) findViewById(R.id.activity_splash_screen_img_animation_list);
        ((AnimationDrawable) mImageViewFilling.getBackground()).start();

        ImageView imgIoTIgnite = (ImageView) findViewById(R.id.activity_splash_screen_img_animation_list_iot_ignite);
        ((AnimationDrawable) imgIoTIgnite.getBackground()).start();
    }


    private void startLoginActivity() {
        Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(i);

    }
}
