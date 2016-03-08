package com.saulmm.material;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abhinav on 3/8/2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version=1;
    public String CREATE_QUERY="CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+ TableData.TableInfo.USER_NAME+" TEXT,"+
            TableData.TableInfo.EMAIL+" TEXT,"+ TableData.TableInfo.PHONE+" TEXT,"+ TableData.TableInfo.COLLEGE+" TEXT);";

    public DatabaseOperations(Context context){
        super(context, TableData.TableInfo.DATABASE_NAME,null,database_version);
        Log.d("Database Operations", "Database Created ");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb){

        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table  Created ");

    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0,int arg1,int arg2){

    }

    public void putInformation(DatabaseOperations dop,String name,String email, String phone, String college){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,name);
        cv.put(TableData.TableInfo.EMAIL,email);
        cv.put(TableData.TableInfo.PHONE,phone);
        cv.put(TableData.TableInfo.COLLEGE, college);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database Operations", "One Row Inserted ");

    }

    public Cursor getInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns={TableData.TableInfo.USER_NAME};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }

}
