package com.saulmm.material.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.saulmm.material.R;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;

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

        File f = QRCode.from("https://github.com/kenglxn/QRGen").to(ImageType.JPG).file();

        Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

        ImageView myImage = (ImageView) findViewById(R.id.imageView1);
        myImage.setImageBitmap(myBitmap);







    }
}

