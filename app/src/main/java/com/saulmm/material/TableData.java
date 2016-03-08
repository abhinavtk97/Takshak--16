package com.saulmm.material;

import android.provider.BaseColumns;

/**
 * Created by Abhinav on 3/8/2016.
 */
public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String id = "id";
        public static final String USER_NAME ="user_name";
        public static final String EMAIL ="email";
        public static final String PHONE="phone";
        public static final String COLLEGE="college";
        public static final String DATABASE_NAME="user_info";
        public static final String TABLE_NAME="reg_info";


    }
}
