package com.saulmm.material.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;

import com.saulmm.material.R;
import com.saulmm.material.myapplication.RegistrationMainActivity;
import com.saulmm.material.myapplication.ViewReg;

public class TransitionFirstActivity extends Activity {

    private View mFabButton;
    private View mHeader;
    private View mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_first);

        mFabButton = findViewById(R.id.fab_button);
        mHeader = findViewById(R.id.activity_transition_header);
        mLogo = findViewById(R.id.imageView);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header, true);
        getWindow().setExitTransition(slideExitTransition);
    }

    public void onFabPressed(View view) {

        Intent i  = new Intent (TransitionFirstActivity.this,
            RegistrationMainActivity.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                TransitionFirstActivity.this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"), Pair.create(mLogo,"logo"));

        startActivity(i, transitionActivityOptions.toBundle());
    }

    public void onFabPressedEvents(View view) {

        Intent i  = new Intent (TransitionFirstActivity.this,
                Events.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                TransitionFirstActivity.this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"), Pair.create(mLogo,"logo"));

        startActivity(i, transitionActivityOptions.toBundle());
    }






}
