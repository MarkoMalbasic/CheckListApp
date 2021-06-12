package com.example.checklistapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateItemActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button updateButton, deleteButton;
    private String id, item, sectionsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mEditText = findViewById(R.id.editTextUpdate);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton = findViewById(R.id.buttonDelete);

        getAndSetIntentData();

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("ID");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("ID");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(item);
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDAO i = new ItemDAO(UpdateItemActivity.this);
                i.updateData(newString, mEditText.getText().toString());
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
        if (getIntent().hasExtra("ID") && getIntent().hasExtra("ITEM") && getIntent().hasExtra("SECTIONS_ID")){
            id = getIntent().getStringExtra("ID");
            item = getIntent().getStringExtra("ITEM");
            sectionsID = getIntent().getStringExtra("SECTIONS_ID");
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
                ItemDAO i = new ItemDAO(UpdateItemActivity.this);
                i.deleteRow(id);
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