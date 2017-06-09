package com.product.is.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuyidi on 17/5/21.
 */
public class DbOpenHelper extends SQLiteOpenHelper{

    public static final String CREATE_DB = "create table Student(" +
            "id integer primary key autoincrement," +
            "name varchar(20), " +
            "className text, "+
            "studentNo varchar(20), "+
            "chinese text, "+
            "math text, "+
            "english text)";

    private Context mContext;
    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student");
        onCreate(db);
    }
}
