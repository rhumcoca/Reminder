package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "reminder.db";
    public static final String TABLE_NAME = "Event";
    public static final String COL_1 = "Id";
    public static final String COL_2 ="Name";
    public static final String COL_3 = "Description";
    public static final String COL_4 = "Category";
    public static final String COL_5 = "Date";
    public static final String COL_6 = "Frequency";
    public static final String COL_7 = "Status";
    public static final int DATABASE_VERSION = 1;

    public SQLiteDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,Name TEXT, Description TEXT, Category TEXT," +
                "Date INTEGER, Fraquency TEXT, Status TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteDataBaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String description, String category, Integer date, String frequency, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, category);
        contentValues.put(COL_5, date);
        contentValues.put(COL_6, frequency);
        contentValues.put(COL_7, status);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == 1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }
}