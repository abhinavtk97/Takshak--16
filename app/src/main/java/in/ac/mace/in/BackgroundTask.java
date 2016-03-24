package in.ac.mace.in;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Abhinav on 3/23/2016.
 */
public class BackgroundTask extends AsyncTask<String,Data,String> {

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
    }
}
