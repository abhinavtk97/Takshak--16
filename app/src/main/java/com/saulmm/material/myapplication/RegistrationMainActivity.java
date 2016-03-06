package com.saulmm.material.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.saulmm.material.R;

public class RegistrationMainActivity extends Activity {

    private static final int SCALE_DELAY = 30;
    private LinearLayout rowContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);

        rowContainer = (LinearLayout) findViewById(R.id.row_container2);
        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);

    }
}

