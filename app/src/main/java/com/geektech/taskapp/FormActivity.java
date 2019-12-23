package com.geektech.taskapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geektech.taskapp.room.TaskDao;
import com.geektech.taskapp.ui.home.HomeFragment;
import com.geektech.taskapp.ui.home.TaskAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText desc;
    private String userId;

    Task task;

//    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        desc = findViewById(R.id.description);
        userId = FirebaseAuth.getInstance().getUid();
        getInfo();
        edit();
    }

    public void edit() {

        task = (Task) getIntent().getSerializableExtra("Task");
        if (task != null) {
            editTitle.setText(task.getTitle());
            desc.setText(task.getDesc());
        }
    }

    private void getInfo() {
        FirebaseFirestore.getInstance()
                .collection("tasks")
                .document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            String name = task.getResult().getString("name");
                            String email = task.getResult().getString("email");
                            editTitle.setText(name);
                            desc.setText(email);
                        }
                    }
                });
    }


    public void onClick(View view) {

        String title = editTitle.getText().toString().trim();
        String description = desc.getText().toString().trim();


        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("description", description);
        FirebaseFirestore.getInstance()
                .collection("tasks")
                .document()
                .set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toaster.show("Успешно");
                        } else {
                            Toaster.show("Ошибка");
                        }
                    }

                });

        if (task != null) {
            task.setTitle(title);
            task.setDesc(description);
            App.getDatabase().taskDao().update(task);

        } else {
            task = new Task(title, description);
            App.getDatabase().taskDao().insert(task);


       /* intent.putExtra("key", task);
        setResult(RESULT_OK, intent);*/


        }


        finish();


    }

}
