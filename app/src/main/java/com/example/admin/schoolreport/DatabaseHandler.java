package com.example.admin.schoolreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 8/23/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME = "students_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "IDENTITY";
    public static final String COL4 = "DATE";
    public static final String COL5 = "GRADE";
    public static final String COL6 = "SUBJECT1";
    public static final String COL7 = "SUBJECT2";
    public static final String COL8 = "SUBJECT3";
    public static final String COL9 = "SUBJECT4";
    public static final String COL10 = "SUBJECT5";
    public static final String COL11 = "SUBJECT6";
    public static final String COL12 = "SUBJECT7";
    public static final String COL13 = "STATUS";



    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT,IDENTITY TEXT,DATE TEXT, GRADE TEXT, SUB1 TEXT, SUB2 TEXT, SUB3 TEXT, SUB4 TEXT, SUB5 TEXT, SUB6 TEXT, SUB7 TEXT, STATUS TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String name, String identity, String date, String grade, String subject1, String subject2, String subject3, String subject4, String subject5, String subject6, String subject7, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,identity);
        contentValues.put(COL4,date);
        contentValues.put(COL5,grade);
        contentValues.put(COL6,subject1);
        contentValues.put(COL7,subject2);
        contentValues.put(COL8,subject3);
        contentValues.put(COL9,subject4);
        contentValues.put(COL10,subject5);
        contentValues.put(COL11,subject6);
        contentValues.put(COL12,subject7);
        contentValues.put(COL13,status);

        long result = db.insert(TABLE_NAME, null , contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }

    }

    public Cursor showData (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean updateData (String id,String name, String identity, String date, String grade, String subject1, String subject2, String subject3, String subject4, String subject5, String subject6, String subject7, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,identity);
        contentValues.put(COL4,date);
        contentValues.put(COL5,grade);
        contentValues.put(COL6,subject1);
        contentValues.put(COL7,subject2);
        contentValues.put(COL8,subject3);
        contentValues.put(COL9,subject4);
        contentValues.put(COL10,subject5);
        contentValues.put(COL11,subject6);
        contentValues.put(COL12,subject7);
        contentValues.put(COL13,status);

        db.update(TABLE_NAME, contentValues,"ID = ?", new String[] {id});
        return true;


    }

    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }


}
