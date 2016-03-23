package in.ac.mace.in.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import in.ac.mace.in.BackgroundTask;
import in.ac.mace.in.R;
import in.ac.mace.in.utils.AnimatorAdapter;
import in.ac.mace.in.utils.TransitionAdapter;

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
        String x = "Select College";
        int flag1=0,flag2=0,flag3=0;
        if(phone.length()==10){
             flag1=1;
        }
        if(username.length()>4){
            flag2=1;
        }
        if(email.contains("@")&&!email.contains(" ")){
            flag3=1;

        }


        if(college!=x&&flag1==1&&flag2==1&&flag3==1){
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute("add_info",username,email,phone,college);

            Toast.makeText(getApplicationContext(),"Successfully Registered", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Please review the registration",Toast.LENGTH_SHORT).show();
        }



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
