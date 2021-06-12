package com.example.checklistapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.security.Key;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final Context context;

    //Sections table
    public static final String DATABASE_NAME = "myList.db";
    public static final String TABLE_SECTIONS = "sections_data";
    public static final String SECTIONS_ID = "ID";
    public static final String SECTIONS_TITLE = "SECTION";

    //Item table
    public static final String TABLE_ITEMS = "items_data";
    public static final String ITEMS_ID = SECTIONS_ID;
    public static final String ITEMS_TITLE = "ITEM";
    public static final String ITEMS_SECTIONS_ID = "SECTIONS_ID";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querySections = "CREATE TABLE " + TABLE_SECTIONS + " (" + SECTIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SECTIONS_TITLE + " TEXT);";
        String queryItems = "CREATE TABLE " + TABLE_ITEMS + " (" + ITEMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEMS_TITLE + " TEXT, " + ITEMS_SECTIONS_ID + " INTEGER NOT NULL" + ");";
        db.execSQL(querySections);
        db.execSQL(queryItems);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

//    public int GetUserID(String tableName,String emailId) {
//        String where = COLUMN_USER_EMAIL+" LIKE '%"+emailId+"%'";
//        Cursor c = db.query(true, tableName, null,
//                where, null, null, null, null, null);
//        if(c.getCount()>0)
//            return c.getInt(0);
//        else
//            return 0;
//    }
//    public void addItem(String item){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SECTIONS_TITLE, item);
//
//        long result = db.insert(TABLE_SECTIONS, null, contentValues);
//
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Successfully added to DB", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public String getSectionID(){
//        String query = "SELECT ID FROM " + TABLE_SECTIONS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String str = null;
//        if(db != null){
//            str = String.valueOf(db.rawQuery(query, null));
//        }
//        return str;
//    }
//
//    public void updateData(String row_id, String item){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SECTIONS_TITLE, item);
//
//        long result = db.update(TABLE_SECTIONS, contentValues, "ID=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public long getRow(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result =
//        if(result == -1){
//            Toast.makeText(context, "Failed to Delete!", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
//        }
//        return result;
//    }


}
