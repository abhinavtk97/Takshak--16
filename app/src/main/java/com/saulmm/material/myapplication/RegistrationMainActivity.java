package com.saulmm.material.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.saulmm.material.R;


import com.saulmm.material.activities.TransitionSecondActivity;


public class RegistrationMainActivity extends Activity {
    private View mFabButton;
    private View mHeader;
    private View mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);

        mFabButton = findViewById(R.id.fab_button);
        mHeader = findViewById(R.id.activity_transition_header);
        mLogo = findViewById(R.id.imageView);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header1, true);
        getWindow().setExitTransition(slideExitTransition);

    }
    public void onFabPressedReg(View view) {

        Intent i  = new Intent (RegistrationMainActivity.this,
                TransitionSecondActivity.class);
        ActivityOptions option =ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(mFabButton, "fab"),
                Pair.create(mLogo, "logo"));

        startActivity(i,option.toBundle());
    }
    public void onFabPressedReg1(View view) {

        Intent i  = new Intent (this,
                ViewReg.class);

        ActivityOptions option =ActivityOptions.makeSceneTransitionAnimation(this,Pair.create(mFabButton,"fab"),
                Pair.create(mLogo,"logo"));

        startActivity(i,option.toBundle());
    }


        }











