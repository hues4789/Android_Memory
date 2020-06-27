package com.example.menusample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView _lvMenu;

    private List<Map<String,Object>> _menuList;

    private static final String[] FROM = {"name","price"};

    private static final int[] TO = {R.id.tvMenuName,R.id.tvMenuPrice};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面部品lvMenuを取得し、フィールドに格納
        _lvMenu = findViewById(R.id.lvMenu);
        //サンプルデータを取得
        _menuList =createTeishokuList();
//初期表示
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,_menuList,R.layout.row,FROM,TO);

        _lvMenu.setAdapter(adapter);

        //リストをタップした時のリスナ
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        registerForContextMenu(_lvMenu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_options_menu_list,menu);

        return super.onCreateOptionsMenu(menu);
    }
//////////////////////////テストデータ作成/////////////////////////////////////////////////////////////
    private List<Map<String,Object>> createTeishokuList(){

        List<Map<String,Object>> menuList = new ArrayList<>();

        Map<String,Object> menu = new HashMap<>();
        menu.put("name","唐揚げ定食");
        menu.put("price",800);
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","ハンバーグ定食");
        menu.put("price",850);
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","海苔べん定食");
        menu.put("price",850);
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","白身魚定食");
        menu.put("price",850);
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","刺身定食");
        menu.put("price",850);
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        return menuList;
    }
///////////////////////////////////////////////////////////////////////////////


    /*    リストがタップされた時の処理が記述されたメンバクラス*/
    private class ListItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){

            //clickいたポジションのitemを呼び出す
            Map<String,Object>item = (Map<String,Object>)parent.getItemAtPosition(position);
            //注文処理
            order(item);
        }


    }

    private void order(Map<String,Object>menu){
        //選択されたname,priceを呼び出す
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer)menu.get("price");

        //画面遷移に必要なintentをインスタンス化 1引数:今のクラス　2引数:移動するクラス
        Intent intent = new Intent(MainActivity.this,MenuThanksActivity.class);

        //引き渡すデータ
        intent.putExtra("menuName",menuName);
        intent.putExtra("menuPrice",menuPrice + "円");

        startActivity(intent);
    }
    ////////////////////テストデータ作成//////////////////////////
    private List<Map<String,Object>> createCurryList(){

        List<Map<String,Object>> menuList = new ArrayList<>();

        Map<String,Object> menu = new HashMap<>();
        menu.put("name","ビーフカレー");
        menu.put("price",520);
        menu.put("desc","特製スパイスをきかせた国産ビーフ100%カレーです。");

        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","ポークカレー");
        menu.put("price",420);
        menu.put("desc","特製スパイスをきかせた国産ポーク100%カレーです。");

        menuList.add(menu);

        return menuList;
        /////////////////////////////////////////////////
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        //選択されたメニューのIDを取得
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.menuListOptionTeishoku:
                _menuList = createTeishokuList();
                break;

            case R.id.menuListOptionCurry:
                _menuList = createCurryList();
                break;
        }

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,_menuList,R.layout.row,FROM,TO);

        _lvMenu.setAdapter(adapter);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,view,menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_menu_list,menu);
        menu.setHeaderTitle(R.string.menu_list_context_header);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        //長押しされたビューに関する情報が格納されたオブジェクトを取得
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //押された位置を取得
        int listPosition = info.position;

        Map<String,Object>menu = _menuList.get(listPosition);

        ///////選択された選択肢のIDを取得
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.menuListContextDesc:
                String desc =(String) menu.get("desc");

                Toast.makeText(MainActivity.this,desc,Toast.LENGTH_LONG).show();

                break;

            case R.id.menuListContextOrder:
                order(menu);
                break;
        }

        return super.onContextItemSelected(item);
    }

}