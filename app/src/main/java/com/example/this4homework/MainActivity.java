package com.example.this4homework;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTask;
    TaskAdapter taskAdapter;
    TaskModel model;
    boolean isEdit = true;
    private FloatingActionButton btnOpenAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTask = findViewById(R.id.rv_task);


        taskAdapter = new TaskAdapter();
        rvTask.setAdapter(taskAdapter);
        btnOpenAddTask = findViewById(R.id.btn_open_add_task);
        btnOpenAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            isEdit = false;
            startActivityForResult(intent, 100);


        });

        taskAdapter.setItemClickList(new ItemClickList() {
            @Override
            public void CLickItem(int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("title", taskAdapter.list.get(position).getTitle());
                intent.putExtra("description", taskAdapter.list.get(position).getDescriptoin());
                intent.putExtra("pos", position);
                // setResult(RESULT_OK, intent);
                startActivityForResult(intent, 1);


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null) {
                TaskModel model = new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"));
                taskAdapter.addData(model);

            }

        } else if (requestCode == 1 && resultCode == RESULT_OK) {

            if (data != null) {
                TaskModel model = new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"));

              int pos = data.getIntExtra("pos",0);
                Toast.makeText(MainActivity.this, "text is change", Toast.LENGTH_LONG).show();

                taskAdapter.editData(model, pos);


            }

        }
    }
}
