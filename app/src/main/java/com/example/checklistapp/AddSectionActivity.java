package com.example.checklistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSectionActivity extends AppCompatActivity {

    public EditText mEditText;
    public Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mEditText = findViewById(R.id.editText);
        mButton = findViewById(R.id.buttonEditAddActivity);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionDAO s = new SectionDAO(AddSectionActivity.this);
                s.addItem(mEditText.getText().toString());
                //myDB.addItem(mEditText.getText().toString());
            }
        });
    }
}