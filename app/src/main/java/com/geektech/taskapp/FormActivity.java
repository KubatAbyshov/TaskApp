package com.geektech.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geektech.taskapp.ui.home.HomeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText desc;

    Task task = new Task();
    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        desc = findViewById(R.id.description);
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String description = desc.getText().toString().trim();


        if (task != null) {
            task.setTitle(title);
            task.setDesc(description);
        }

        intent.putExtra("key", task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
