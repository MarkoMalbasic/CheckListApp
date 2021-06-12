package com.example.checklistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class ItemDAO {

    private Context context;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mDbHelper;
    private String[] mAllColumns = { mDbHelper.ITEMS_ID,
            mDbHelper.ITEMS_TITLE, mDbHelper.ITEMS_SECTIONS_ID };


    public ItemDAO(Context context) {
        mDbHelper = new DatabaseHelper(context);
        this.context = context;
        // open the database
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void addItem(String item, String sectionID){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.ITEMS_TITLE, item);
        contentValues.put(mDbHelper.ITEMS_SECTIONS_ID, sectionID);

        long result = db.insert(mDbHelper.TABLE_ITEMS, null, contentValues);
        Cursor cursor = mDatabase.query(mDbHelper.TABLE_ITEMS, mAllColumns,
                mDbHelper.ITEMS_ID + " = " + result, null, null,
                null, null);
        cursor.moveToFirst();

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully added to DB", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllData(){
        String query = "SELECT * FROM " + mDbHelper.TABLE_ITEMS;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getItemsOfSection(String section_id) {
        String query = "SELECT * FROM " + mDbHelper.TABLE_ITEMS + " WHERE " +
                mDbHelper.ITEMS_SECTIONS_ID + " = " + section_id;
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
        contentValues.put(mDbHelper.ITEMS_TITLE, item);

        long result = db.update(mDbHelper.TABLE_ITEMS, contentValues, "ITEM=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteRow(String row_id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long result = db.delete(mDbHelper.TABLE_ITEMS, "ITEM=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }


}
