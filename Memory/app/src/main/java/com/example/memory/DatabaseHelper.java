package com.example.memory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";

    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //taskテーブル作成
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE task(");
        sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT");
        sb.append(",learn_context TEXT");
        sb.append(",created_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))");
        sb.append(",updated_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))");
        sb.append(");");

        String sql=sb.toString();

        db.execSQL(sql);

        //Randomテーブル作成
        StringBuilder sbRandom = new StringBuilder();
        sbRandom.append("CREATE TABLE Random(");
        sbRandom.append("id INTEGER PRIMARY KEY AUTOINCREMENT");
        sbRandom.append(",from_num INTEGER");
        sbRandom.append(",to_num INTEGER");
        sbRandom.append(",created_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))");
        sbRandom.append(",updated_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))");
        sbRandom.append(");");

        String sqlRandom=sbRandom.toString();

        db.execSQL(sqlRandom);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

}
