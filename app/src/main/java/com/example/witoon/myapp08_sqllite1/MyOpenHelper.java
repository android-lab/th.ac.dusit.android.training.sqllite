package com.example.witoon.myapp08_sqllite1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class MyOpenHelper extends SQLiteOpenHelper {


   // public static final String DATABASE_NAME = "my_contact.db";
    public static final String DATABASE_NAME = "my_contact";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTACT = "table_contact";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE " + TABLE_CONTACT +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT(100)," +
                " surname TEXT(100));");
        Log.d("CREATE TABLE", "Create Table Successfully.");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

// Re Create on method  onCreate

        onCreate(db);

    }
}