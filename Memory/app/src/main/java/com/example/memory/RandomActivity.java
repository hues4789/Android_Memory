package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Button CreateTask = findViewById(R.id.btCreate);

        Button TodayTask = findViewById(R.id.btToday);

        CreateTask.setOnClickListener(this);

        TodayTask.setOnClickListener(this);


        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //セットする値
        List<Integer> FromNumList = new ArrayList<>();
        List<Integer> ToNumList = new ArrayList<>();

        try{

            String sql = "SELECT * FROM Random";

            Cursor cursor = db.rawQuery(sql,null);


            while(cursor.moveToNext()){

                Integer SetFromNum = cursor.getInt(cursor.getColumnIndex("from_num"));
                FromNumList.add(SetFromNum);

                Integer SetToNum = cursor.getInt(cursor.getColumnIndex("to_num"));
                ToNumList.add(SetToNum);
            }


        }finally{
            db.close();
        }

        TextView SetFromNum = findViewById(R.id.SetFromNum);
        TextView SetToNum = findViewById(R.id.SetToNum);

        if(FromNumList.size() != 0 || ToNumList.size() != 0) {
            //値をセット
            SetFromNum.setText(FromNumList.get(FromNumList.size()-1).toString());
            SetToNum.setText(ToNumList.get(ToNumList.size()-1).toString());
        }else{
            SetFromNum.setText("");
            SetToNum.setText("");
        }

    }

    @Override
    public void onClick(View v){
        if(v !=null){
            switch(v.getId()){
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

    public void onStartButtonClick(View view){
        //画面の値をセット
        TextView SetFromNum = findViewById(R.id.SetFromNum);
        TextView SetToNum = findViewById(R.id.SetToNum);
        //Random数from
        int fromNum = Integer.parseInt(SetFromNum.getText().toString());
        //Random数to
        int ToNum = Integer.parseInt(SetToNum.getText().toString());;

        //ランダムの数を作成
        Random random = new Random();
        int RandomNum = random.nextInt(ToNum) + fromNum;

        //ランダム数を出力
        TextView RandomText = findViewById(R.id.RandomNum);
        RandomText.setText(Integer.toString(RandomNum));

    }

    public void CreateRandomNumButtonClick(View view){
        //入力文字を取得
        EditText fromNum = findViewById(R.id.FromNum);
        EditText toNum = findViewById(R.id.ToNum);

        //数値取得
        int fromIntNum = Integer.parseInt(fromNum.getText().toString());
        int toIntNum = Integer.parseInt(toNum.getText().toString());

        //データベースヘルパーを取得
        DatabaseHelper helper = new DatabaseHelper(this);

        //データベース接続オブジェクトを取得
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            String sqlInsert = "INSERT INTO Random(from_num,to_num)VALUES (?,?)";
            SQLiteStatement stmt = db.compileStatement(sqlInsert);

            stmt.bindLong(1,fromIntNum);
            stmt.bindLong(2,toIntNum);

            stmt.executeInsert();

        }finally {
            db.close();
        }

        TextView SetFromNum = findViewById(R.id.SetFromNum);
        TextView SetToNum = findViewById(R.id.SetToNum);

            //値をセット
            SetFromNum.setText(fromNum.getText().toString());
            SetToNum.setText(toNum.getText().toString());

    }
}