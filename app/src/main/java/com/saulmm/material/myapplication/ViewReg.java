package com.saulmm.material.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.saulmm.material.R;
import com.saulmm.material.utils.TransitionAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ViewReg extends Activity {

    private static final int SCALE_DELAY = 30;
    private LinearLayout rowContainer;

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
        setContentView(R.layout.activity_view_reg);

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


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        qrImg = (ImageView)findViewById(R.id.qrcode);
        qrTxt = (TextView)findViewById(R.id.textView1);



        clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);



        /*
         * clipboard.getText() is now deprecated. But I am going to use it here
         * because the new way of doing the same thing only works on API lvl 11+
         * Since I want this application to support API lvl 4+ we have to use
         * the old method.
         */
        CharSequence clipTxt = clipboard.getText();

        //This is the new, non-deprecated way of getting text from the Clipboard.
        //CharSequence clipTxt = clipboard.getPrimaryClip().getItemAt(0).getText();


        //If the clipboard has text, and it is more than 0 characters.
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
    }
}
