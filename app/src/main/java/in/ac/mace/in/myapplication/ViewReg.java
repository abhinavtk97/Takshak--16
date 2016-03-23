package in.ac.mace.in.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import in.ac.mace.in.BackgroundTask;
import in.ac.mace.in.DataAdapter;
import in.ac.mace.in.R;
import in.ac.mace.in.utils.TransitionAdapter;

public class ViewReg extends Activity {

    private SimpleCursorAdapter dataAdapter;

    private static final int SCALE_DELAY = 30;
    private LinearLayout rowContainer;
    String[] array = {"afvkjfvkgcg","jdfgciycb","vkvkuvuyhc","jdfgciycb","vkvkuvuyhc","jdfgciycb","vkvkuvuyhc","ufuifwfiugwf","fvkhavajk","adgkyuhagdkyhad"};

    ImageLoader imgLoader;
    ImageView qrImg;
    String copiedStr;
    TextView qrTxt;
    ListView listView = (ListView)findViewById(R.id.listView);
    ClipboardManager clipboard;

    String BASE_QR_URL = "http://chart.apis.google.com/chart?cht=qr&chs=400x400&chld=M&choe=UTF-8&chl=";
    String fullUrl = BASE_QR_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Finally", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reg);

        BackgroundTask backgroundTask =new BackgroundTask(this);
        backgroundTask.execute("get_info");

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
        qrTxt = (TextView)findViewById(R.id.textView1);}




       public void viewqrmain(){
           Toast.makeText(getApplicationContext(),"f*",Toast.LENGTH_LONG).show();
                View view=findViewById(R.id.t_name);
                String product = ((Button) view).getText().toString();
                qrTxt.setText(product);


                final CharSequence  clipTxt = qrTxt.getText();


                Toast.makeText(getApplicationContext(),qrTxt.getText(), Toast.LENGTH_SHORT).show();
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



    public void ViewQr(View view){
        String copiedStr1;


        Intent intent = new Intent(this,qrcode.class);
        copiedStr1 = qrTxt.getText().toString();
        intent.putExtra("qrt", copiedStr1);
        startActivity(intent);
    }


}
