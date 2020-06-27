package com.example.listviewsample2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ListViewオブジェクトを取得
        ListView lvMenu = findViewById(R.id.lvMenu);

        List<String> menuList = new ArrayList<>();

        menuList.add("唐揚げ定食");
        menuList.add("ハンバーグ定食");
        menuList.add("生姜焼き定食");
        menuList.add("ステーキ定食");
        menuList.add("野菜炒め定食");
        menuList.add("とんかつ定食");
        menuList.add("ミンチカツ定食");
        menuList.add("チキンカツ定食");
        menuList.add("コロッケ定食");
        menuList.add("焼き魚定食");
        menuList.add("焼肉定食");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,menuList);

        lvMenu.setAdapter(adapter);
    }

   private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();

            dialogFragment.show(getSupportFragmentManager(),"OrderConfirmDialogFragment");
        }
   }
}