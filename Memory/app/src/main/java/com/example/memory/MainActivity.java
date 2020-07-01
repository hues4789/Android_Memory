package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button CreateTask = findViewById(R.id.btCreate);

        Button RandomNum = findViewById(R.id.btRandom);

        ListView TodayTaskList = findViewById(R.id.onedaylist);

        DatabaseHelper helper = new DatabaseHelper(this);
//データベース接続オブジェクトを取得
        SQLiteDatabase db = helper.getWritableDatabase();
        List<String> tasks = new ArrayList<>();
        try{

            String sql = "SELECT * FROM task";

            Cursor cursor = db.rawQuery(sql,null);


            while(cursor.moveToNext()){
                int idxTask = cursor.getColumnIndex("task");

                Task task = new Task();
                task.task = cursor.getString(cursor.getColumnIndex("learn_context"));
                tasks.add(task.task);
            }


        }finally{
            db.close();
        }

//////////////データ作成////////////////////////
        List<String> TaskList = new ArrayList<>();
        TaskList.add("物理40p-50p");
        TaskList.add("細菌学30p-50p");
/////////////////////////////////////////////////
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tasks);
        TodayTaskList.setAdapter(adapter);

        CreateTask.setOnClickListener(this);

        RandomNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v !=null){
           switch(v.getId()){
               case R.id.btRandom:
                   Intent RandomIntent = new Intent(this,RandomActivity.class);
                   startActivity(RandomIntent);
                   break;

               case R.id.btCreate:
                   Intent TaskIntent = new Intent(this,taskCreate.class);
                   startActivity(TaskIntent);
                   break;

               default:
                   break;
           }
        }

    }


}