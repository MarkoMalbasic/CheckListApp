package com.example.checklistapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemActivity extends AppCompatActivity {

    private ArrayList<ItemModel> mItemList;
    private SectionModel sectionModel;
    private String id;
    private int integerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        RecyclerView mRecyclerView = findViewById(R.id.itemRecycleView);

        BottomNavigation();

        mItemList = new ArrayList<>();

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("sectionID");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("sectionID");
        }

        AddItemActivity.selectedItem = newString;

        readItemDB(newString);

        ListNoteItemAdapter mListNoteItemAdapter = new ListNoteItemAdapter(ItemActivity.this, this, mItemList);
        mRecyclerView.setAdapter(mListNoteItemAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void readItemDB(String sectionId){
        ItemDAO i = new ItemDAO(ItemActivity.this);
        Cursor cursor = i.getItemsOfSection(sectionId);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data in DB", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){

                mItemList.add(new ItemModel(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }

    public void BottomNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.itemNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_delete:
                        break;
                    case R.id.id_add:
                        Intent intent = new Intent(ItemActivity.this, AddItemActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}