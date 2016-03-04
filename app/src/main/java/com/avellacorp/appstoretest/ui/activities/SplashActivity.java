package com.avellacorp.appstoretest.ui.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.avellacorp.appstoretest.R;
import com.avellacorp.appstoretest.base.Constant;
import com.avellacorp.appstoretest.ui.customviews.KenBurnsView;
import com.avellacorp.appstoretest.ui.customviews.RobotoTextView;
import com.avellacorp.appstoretest.ui.presenter.asynctasks.DownloadAppsTask;


public class SplashActivity extends MasterActivity {

    // timer

    private KenBurnsView mKenBurns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();

        mKenBurns = (KenBurnsView) findViewById(R.id.ken_burns_images);
        mKenBurns.setImageResource(R.drawable.splash);
        ((RobotoTextView)findViewById(R.id.welcome_text)).setRobotoTypeface(Constant.ROBOTO_REGULAR);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                //si ya finalizó finaliza la actividad

                //si no ha terminado muestra el dialogo de progreso
                findViewById(R.id.pBarSplash).setVisibility(View.VISIBLE);

            }
        }, Constant.SPLASH_TIME_OUT);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                //si ya finalizó finaliza la actividad

                //si no ha terminado muestra el dialogo de progreso
                findViewById(R.id.pBarSplash).setVisibility(View.VISIBLE);
                //Arranca una tarea asíncrona de descarga

                new DownloadAppsTask(SplashActivity.this).execute();

            }
        }, Constant.SPLASH_TIME_OUT);


    }

    private void setAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1200);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1200);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(1200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(500);
        animatorSet.start();

        findViewById(R.id.imagelogo).setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_center);
        findViewById(R.id.imagelogo).startAnimation(anim);
    }




}
