package com.example.checklistapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SectionModel> mItemList;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = findViewById(R.id.recycleView);

        BottomNavigation();

        mItemList = new ArrayList<>();

        readSectionDB();

        ListNoteSectionAdapter mListNoteSectionAdapter = new ListNoteSectionAdapter(MainActivity.this, cursor, this,  mItemList);
        mRecyclerView.setAdapter(mListNoteSectionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void readSectionDB(){
        SectionDAO s = new SectionDAO(MainActivity.this);
        Cursor cursor = s.getAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data in DB", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                mItemList.add(new SectionModel(cursor.getInt(0), cursor.getString(1)));
            }
        }
    }

    public void BottomNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_delete:
                        break;
                    case R.id.id_add:
                        Intent intent = new Intent(MainActivity.this, AddSectionActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
