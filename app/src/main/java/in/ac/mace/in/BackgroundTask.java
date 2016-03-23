package in.ac.mace.in;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Abhinav on 3/23/2016.
 */
public class BackgroundTask extends AsyncTask<String,Data,String> {

    ImageLoader imgLoader;
    ImageView qrImg;
    String copiedStr;
    TextView qrTxt;
    ClipboardManager clipboard;

    String BASE_QR_URL = "http://chart.apis.google.com/chart?cht=qr&chs=400x400&chld=M&choe=UTF-8&chl=";
    String fullUrl = BASE_QR_URL;

    Context ctx;
    DataAdapter dataAdapter;
    Activity activity;
    ListView listView;
    public BackgroundTask(Context ctx){
        this.ctx=ctx;
        activity=(Activity)ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        DbOperations dbOperations = new DbOperations(ctx);
        if (method.equals("add_info")){
            String username = params[1];
            String email = params[2];
            String phone = params[3];
            String college = params[4];
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.addInformation(db,username,email,phone,college);
            return "One Row Inserted";
        }
        else if (method.equals("get_info")){
            listView=(ListView)activity.findViewById(R.id.listView);
            SQLiteDatabase db = dbOperations.getReadableDatabase();
            Cursor cursor = dbOperations.getInformation(db);
            dataAdapter = new DataAdapter(ctx,R.layout.activity_listview);
            String name,mail,phone,college;
            while (cursor.moveToNext()){
                name=cursor.getString(cursor.getColumnIndex(Contract.DataEntry.USERNAME));
                mail=cursor.getString(cursor.getColumnIndex(Contract.DataEntry.MAIL));
                phone=cursor.getString(cursor.getColumnIndex(Contract.DataEntry.PHONE));
                college=cursor.getString(cursor.getColumnIndex(Contract.DataEntry.COLLEGE));
                Data data = new Data(name,mail,phone,college);
                publishProgress(data);

            }
            return "get_info";
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Data... values) {
        dataAdapter.add(values[0]);

    }


    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_info")){
            listView.setAdapter(dataAdapter);
        }
        else {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }

        /*ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        qrImg = (ImageView)findViewById(R.id.qrcode);
        qrTxt = (TextView)findViewById(R.id.textView1);}
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String product = ((TextView) view).getText().toString();
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
            }*/

        }}
