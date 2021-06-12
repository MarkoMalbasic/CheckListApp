package com.example.checklistapp;

import android.content.ClipData;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class AddItemActivity extends AppCompatActivity {

    public static String selectedItem;
    public EditText mEditText;
    public Button mButton;
    public DatabaseHelper db = new DatabaseHelper(AddItemActivity.this);
    private ArrayList<SectionModel> modelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        modelArrayList = new ArrayList<>();

        mEditText = findViewById(R.id.editText);
        mButton = findViewById(R.id.buttonEditAddActivity);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDAO i = new ItemDAO(AddItemActivity.this);
                i.addItem(mEditText.getText().toString(), selectedItem);
                //myDB.addItem(mEditText.getText().toString());
            }
        });
    }
}
