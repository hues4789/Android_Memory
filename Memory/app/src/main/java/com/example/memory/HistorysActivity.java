package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorysActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historys);

        //遷移ボタン
        BootstrapButton TodayTask = findViewById(R.id.btToday);
        BootstrapButton CreateTask = findViewById(R.id.btCreate);
        BootstrapButton RandomNum = findViewById(R.id.btRandom);

        ListView lvMenu = findViewById(R.id.onedaylist);
        List<Map<String,String>> menuList = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(this);
        //データベース接続オブジェクトを取得
        SQLiteDatabase db = helper.getWritableDatabase();

        try{

            String sql = "SELECT * FROM task ORDER BY created_at DESC";

            Cursor cursor = db.rawQuery(sql,null);


            while(cursor.moveToNext()){
                Map<String,String>menu = new HashMap<>();
                menu.put("task",cursor.getString(cursor.getColumnIndex("learn_context")));
                menu.put("date",cursor.getString(cursor.getColumnIndex("created_at")));
                menuList.add(menu);

            }


        }finally{
            db.close();
        }

        String[] from = {"task","date"};
        int[] to = {android.R.id.text1,android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(this,menuList,android.R.layout.simple_list_item_2,from,to);

        lvMenu.setAdapter(adapter);

        //btn遷移リスナ
        TodayTask.setOnClickListener(this);
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

                case R.id.btToday:
                    Intent TodayIntent = new Intent(this,MainActivity.class);
                    startActivity(TodayIntent);
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