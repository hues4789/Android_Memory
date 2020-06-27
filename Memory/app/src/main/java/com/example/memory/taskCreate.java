package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class taskCreate extends AppCompatActivity implements View.OnClickListener{

    String taskName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        Button TodayTask = findViewById(R.id.btToday);

        TodayTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onCreateButtonClick(View view){
        //入力文字を取得
        EditText newTask = findViewById(R.id.newTask);
        String task = newTask.getText().toString();

        //データベースヘルパーを取得
        DatabaseHelper helper = new DatabaseHelper(this);

        //データベース接続オブジェクトを取得
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            String sqlInsert = "INSERT INTO task(learn_context)VALUES (?)";
            SQLiteStatement stmt = db.compileStatement(sqlInsert);

            stmt.bindString(1,task);

            stmt.executeInsert();

        }finally {
            db.close();
        }

    }
}