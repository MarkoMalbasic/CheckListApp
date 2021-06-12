package com.example.checklistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class SectionDAO {

    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;
    private Context context;
    private String[] mAllColumns = {mDbHelper.SECTIONS_ID, mDbHelper.SECTIONS_TITLE};

    public SectionDAO(Context context){
        this.context = context;
        mDbHelper = new DatabaseHelper(context);
        try{
            open();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void open() throws SQLException{
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void addItem(String item){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.SECTIONS_TITLE, item);

        long result = db.insert(mDbHelper.TABLE_SECTIONS, null, contentValues);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully added to DB", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllData(){
        String query = "SELECT * FROM " + mDbHelper.TABLE_SECTIONS;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String item){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.SECTIONS_ID, row_id);
        contentValues.put(mDbHelper.SECTIONS_TITLE, item);

        long result = db.update(mDbHelper.TABLE_SECTIONS, contentValues, "ID=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteRow(String row_id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long result = db.delete(mDbHelper.TABLE_SECTIONS, "ID=?", new String[]{row_id});
        long result1 = db.delete(mDbHelper.TABLE_ITEMS, "SECTIONS_ID=?", new String[]{row_id});
        if (result == -1 || result1 == -1) {
            Toast.makeText(context, "Failed to Delete!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

//    public Cursor getSectionById(String row_id) {
//        String query = "SELECT " + mDbHelper.SECTIONS_ID + " = " + row_id + " FROM " + mDbHelper.TABLE_SECTIONS;
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//        Cursor cursor = null;
//        if(db != null){
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }
}
