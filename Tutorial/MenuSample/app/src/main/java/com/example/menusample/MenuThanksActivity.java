package com.example.menusample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);

        //Intentで取り出し
        Intent intent=getIntent();
        //intentの中の情報を取り出し
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");

        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        //もどるぼたんをゆうこうに
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        //選択されたメニューのIDを取得
        int itemId = item.getItemId();

        if(itemId == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /*戻るボタンをタップした時の処理*/
    public void onBackButtonClick(View view){
        finish();
    }

}