package com.saulmm.material.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.saulmm.material.DatabaseOperations;
import com.saulmm.material.R;
import com.saulmm.material.utils.AnimatorAdapter;
import com.saulmm.material.utils.TransitionAdapter;

public class Registration extends Activity {

    private static final int SCALE_DELAY = 30;
    private LinearLayout rowContainer;
    String username,email,phone,college;
    Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rowContainer = (LinearLayout) findViewById(R.id.row_container2);



        Spinner dropdown = (Spinner)findViewById(R.id.college);
        String[] items = new String[]{"Select College","1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


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

    public void onClick(View view){
        final EditText USER_NAME= (EditText)findViewById(R.id.name);
        final EditText EMAIL= (EditText)findViewById(R.id.mail);
        final EditText PHONE= (EditText)findViewById(R.id.phone);
        final Spinner COLLEGE= (Spinner)findViewById(R.id.college);
        username=USER_NAME.getText().toString();
        email=EMAIL.getText().toString();
        phone=PHONE.getText().toString();
        college=COLLEGE.getSelectedItem().toString();

        DatabaseOperations DB = new DatabaseOperations(ctx);
        DB.putInformation(DB, username, email, phone, college);
        Toast.makeText(getApplicationContext(), "YAAY", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {

        for (int i = 0; i < rowContainer.getChildCount(); i++) {

            View rowView = rowContainer.getChildAt(i);

            ViewPropertyAnimator propertyAnimator = rowView.animate()
                .setStartDelay(i * SCALE_DELAY)
                .scaleX(0).scaleY(0)
                .setListener(new AnimatorAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        super.onAnimationEnd(animation);
                        finishAfterTransition();
                    }
                });
        }
    }
}
