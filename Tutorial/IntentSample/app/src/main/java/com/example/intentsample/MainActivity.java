package com.example.intentsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMenu = findViewById(R.id.lvMenu);

        List<Map<String,String>> menuList = new ArrayList<>();

////////////サンプルデータ作成/////////////////////////
        Map<String,String> menu = new HashMap<>();

        menu.put("name","唐揚げ定食");
        menu.put("price","800円");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","ハンバーグ定食");
        menu.put("price","850円");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","海苔べん定食");
        menu.put("price","850円");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","白身魚定食");
        menu.put("price","850円");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","刺身定食");
        menu.put("price","850円");
        menuList.add(menu);

///////////////////////////////////////////////////

        //キー名の配列
        String[] from = {"name","price"};

        //上記に対応したvalue
        int[] to ={android.R.id.text1,android.R.id.text2};

        //Mapをアダプト　登録したmenuListを第二引数に
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,menuList,android.R.layout.simple_list_item_2,from,to);

        lvMenu.setAdapter(adapter);

        lvMenu.setOnItemClickListener(new ListItemClickListener());

    }

/*    リストがタップされた時の処理が記述されたメンバクラス*/
    private class ListItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){

            //clickいたポジションのitemを呼び出す
            Map<String,String>item = (Map<String,String>)parent.getItemAtPosition(position);

            //選択されたname,priceを呼び出す
            String menuName = item.get("name");
            String menuPrice = item.get("price");

            //画面遷移に必要なintentをインスタンス化 1引数:今のクラス　2引数:移動するクラス
            Intent intent = new Intent(MainActivity.this,MenuThanksActivity.class);

            //引き渡すデータ
            intent.putExtra("menuName",menuName);
            intent.putExtra("menuPrice",menuPrice);

            startActivity(intent);

        }
}



}