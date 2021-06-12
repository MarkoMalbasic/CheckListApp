package com.example.checklistapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateSectionActivity extends AppCompatActivity {

    public static String sectionID;
    private EditText mEditText;
    private DatabaseHelper db;
    private Button updateButton, deleteButton;
    private String id, item;
    SectionModel sectionModel = new SectionModel();
    ArrayList<SectionModel> mArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mEditText = findViewById(R.id.editTextUpdate);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton = findViewById(R.id.buttonDelete);
        db = new DatabaseHelper(this);


        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(item);
        }



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionDAO s = new SectionDAO(UpdateSectionActivity.this);
                s.updateData(getIntent().getStringExtra("ID"), mEditText.getText().toString());
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
    }

    public void getAndSetIntentData(){
        if (getIntent().hasExtra("ID") && getIntent().hasExtra("SECTION")){
            id = getIntent().getStringExtra("ID");
            item = getIntent().getStringExtra("SECTION");
            mEditText.setText(item);
        }else{
            Toast.makeText(this, "There's no data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + item);
        builder.setMessage("Are you sure you want to delete " + item + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SectionDAO s = new SectionDAO(UpdateSectionActivity.this);
                s.deleteRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }




}