package com.example.this4homework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    EditText etTitle, etDescription;
    Button btnAddTask;
    TaskModel task;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_descripton);
        btnAddTask = findViewById(R.id.add_btn_task);
        Intent secIntent = getIntent();

        String Title = secIntent.getStringExtra("title");
        String Description = secIntent.getStringExtra("description");

        etTitle.setText(Title);
        etDescription.setText(Description);

        btnAddTask.setOnClickListener(v -> {
            if (!etTitle.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty()) {
                String title = etTitle.getText().toString().trim();
                String descriprion = etDescription.getText().toString().trim();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", descriprion);
               intent.putExtra("pos", pos);
                setResult(RESULT_OK, intent);
                if (task != null){
                    task.setTitle(title);
                    task.setDescriptoin(descriprion);
                }
                finish();


            }


        });


    }

}