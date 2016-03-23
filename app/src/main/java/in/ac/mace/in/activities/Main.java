package in.ac.mace.in.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;

import in.ac.mace.in.R;
import in.ac.mace.in.myapplication.RegistrationMain;

public class Main extends Activity {

    private View mFabButton;
    private View mHeader;
    private View mLogo;
    private View mHeader1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFabButton = findViewById(R.id.fab_button);
        mHeader = findViewById(R.id.activity_transition_header);
        mLogo = findViewById(R.id.imageView);
        mHeader1=findViewById(R.id.activity_transition_header_small);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header, true);

        getWindow().setExitTransition(slideExitTransition);
    }

    public void onFabPressed(View view) {

        Intent i  = new Intent (Main.this,
            RegistrationMain.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                Main.this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"), Pair.create(mHeader1,"holder2"),
                Pair.create(mLogo,"logo"));

        startActivity(i, transitionActivityOptions.toBundle());
    }

    public void onFabPressedEvents(View view) {

        Intent i  = new Intent (Main.this,
                Events.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                Main.this, Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"),Pair.create(mHeader1,"holder2"), Pair.create(mLogo,"logo"));

        startActivity(i, transitionActivityOptions.toBundle());
    }






}
