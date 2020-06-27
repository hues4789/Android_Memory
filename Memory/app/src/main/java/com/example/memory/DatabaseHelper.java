package com.example.memory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE task(");
        sb.append("id INTEGER PRIMARY KEY AUTOINCREMENT");
        sb.append(",learn_context TEXT");
        sb.append(",created_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))");
        sb.append(",updated_at TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))");
        sb.append(");");

        String sql=sb.toString();

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

}
