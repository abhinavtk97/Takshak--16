package in.ac.mace.in;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abhinav on 3/23/2016.
 */
public class DbOperations extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="data.db";
    private static final String CREATE_QUERY="create table "+Contract.DataEntry.TABLE_NAME+"("+Contract.DataEntry.USERNAME+" text,"+
            Contract.DataEntry.MAIL+" text,"+Contract.DataEntry.PHONE+" text,"+Contract.DataEntry.COLLEGE+" text);";
    DbOperations(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);
        Log.d("Database Operations","Dtabase Created");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database Operations","Table Created");

    }
    public void addInformation(SQLiteDatabase db,String name,String mail,String phone,String college){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.DataEntry.USERNAME,name);
        contentValues.put(Contract.DataEntry.MAIL,mail);
        contentValues.put(Contract.DataEntry.PHONE,phone);
        contentValues.put(Contract.DataEntry.COLLEGE,college);
        db.insert(Contract.DataEntry.TABLE_NAME, null, contentValues);
        Log.d("Database Operations","One row inserted");
    }
    public Cursor getInformation(SQLiteDatabase db){
        String[] projections = {Contract.DataEntry.USERNAME,Contract.DataEntry.MAIL,Contract.DataEntry.PHONE,Contract.DataEntry.COLLEGE};
        Cursor cursor = db.query(Contract.DataEntry.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
