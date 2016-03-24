package in.ac.mace.in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.ac.mace.in.activities.Main;
import in.ac.mace.in.activities.Registration;

public class RegComp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String username,email,phone1,college1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_comp);
        TextView name= (TextView)findViewById(R.id.r_name);
        TextView mail= (TextView)findViewById(R.id.r_mail);
        TextView phone= (TextView)findViewById(R.id.r_phone);
        TextView college= (TextView)findViewById(R.id.r_college);
        Intent intent =getIntent();
        username=intent.getStringExtra("name");
        email=intent.getStringExtra("mail");
        phone1=intent.getStringExtra("phone");
        college1=intent.getStringExtra("college");
        name.setText(username);
        mail.setText(email);
        phone.setText(phone1);
        college.setText(college1);
    }

    public void newreg(View view){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);

    }
}
