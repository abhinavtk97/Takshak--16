package com.saulmm.material.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.saulmm.material.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class qrcode extends Activity {


    ImageLoader imgLoader;
    ImageView qrImg;
    String copiedStr;
    TextView qrTxt;
    ClipboardManager clipboard;
    String BASE_QR_URL = "http://chart.apis.google.com/chart?cht=qr&chs=400x400&chld=M&choe=UTF-8&chl=";
    String fullUrl = BASE_QR_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        Intent intent=getIntent();
        String as=intent.getStringExtra("qrt");
        Toast.makeText(getApplicationContext(),as, Toast.LENGTH_LONG).show();



        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);


        qrImg = (ImageView)findViewById(R.id.imageView2);
        qrTxt = (TextView)findViewById(R.id.textView2);
        qrTxt.setText(as);






        final CharSequence  clipTxt = qrTxt.getText();


        if((null != clipTxt) && (clipTxt.length() > 0)){
            try {
                qrTxt.setText(clipTxt);
                copiedStr = clipTxt.toString();
                fullUrl += URLEncoder.encode(copiedStr, "UTF-8");
                imgLoader.displayImage(fullUrl, qrImg);

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}}
