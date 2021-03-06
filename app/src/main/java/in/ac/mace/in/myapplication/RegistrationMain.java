package in.ac.mace.in.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;

import in.ac.mace.in.R;


import in.ac.mace.in.activities.Registration;


public class RegistrationMain extends Activity {
    private View mFabButton;
    private View mHeader;
    private View mLogo;
    private View mHeader1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);

        mFabButton = findViewById(R.id.fab_button);
        mHeader = findViewById(R.id.activity_transition_header1);
        mLogo = findViewById(R.id.imageView);
        mHeader1=findViewById(R.id.activity_transition_header_small);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header_small, true);

        getWindow().setExitTransition(slideExitTransition);

    }
    public void onFabPressedReg(View view) {

        Intent i  = new Intent (RegistrationMain.this,
                Registration.class);
        ActivityOptions option =ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"),
                Pair.create(mLogo, "logo"), Pair.create(mHeader1, "holder2"));

        startActivity(i,option.toBundle());
    }
    public void onFabPressedReg1(View view) {
        Log.d("Hell no", "onFabPressedReg1: ");
        Intent i  = new Intent (this,
                ViewReg.class);
        ActivityOptions option =ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"),
                Pair.create(mLogo, "logo"), Pair.create(mHeader1, "holder2"));
        startActivity(i, option.toBundle());

    }


        }











