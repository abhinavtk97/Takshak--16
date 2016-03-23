package in.ac.mace.in.activities;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import in.ac.mace.in.R;
import in.ac.mace.in.utils.TransitionAdapter;


public class Events extends Activity {
    private static final int SCALE_DELAY = 30;
    private LinearLayout rowContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        rowContainer = (LinearLayout) findViewById(R.id.row_container2);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);


        getWindow().getEnterTransition().addListener(new TransitionAdapter() {

            @Override
            public void onTransitionEnd(Transition transition) {

                super.onTransitionEnd(transition);

                getWindow().getEnterTransition().removeListener(this);

                for (int i = 0; i < rowContainer.getChildCount(); i++) {

                    View rowView = rowContainer.getChildAt(i);
                    rowView.animate().setStartDelay(i * SCALE_DELAY)
                            .scaleX(1).scaleY(1);
                }
            }
        });
    }

}
