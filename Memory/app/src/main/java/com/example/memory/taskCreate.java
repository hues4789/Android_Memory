package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class taskCreate extends AppCompatActivity implements View.OnClickListener{

    String taskName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        BootstrapButton TodayTask = findViewById(R.id.btToday);

        BootstrapButton RandomNum = findViewById(R.id.btRandom);

        TodayTask.setOnClickListener(this);

        RandomNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        if(v !=null){
            switch(v.getId()){
                case R.id.btRandom:
                    Intent RandomIntent = new Intent(this,RandomActivity.class);
                    startActivity(RandomIntent);
                    break;

                case R.id.btCreate:
                    Intent MainIntent = new Intent(this,MainActivity.class);
                    startActivity(MainIntent);
                    break;

                default:
                    break;
            }
        }


    }

    public void onCreateButtonClick(View view){
        //入力文字を取得
        BootstrapEditText newTask = findViewById(R.id.newTask);
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

            //登録成功表示
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.taskCreate))
                    .setMessage(getString(R.string.taskCreated))
                    .setPositiveButton(getString(R.string.OK), null)
                    .create().show();

            //登録に成功した場合、入力textを空に
            newTask.setText("");

            //キーボード非表示
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }finally {
            db.close();
        }

    }
}