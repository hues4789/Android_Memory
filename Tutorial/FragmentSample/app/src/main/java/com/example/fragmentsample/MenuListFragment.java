package com.example.fragmentsample;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Activity _parentActivity;

    public MenuListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuListFragment newInstance(String param1, String param2) {
        MenuListFragment fragment = new MenuListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //フラグメントで表示する画面をXMLファイルからインフレーと
        View view = inflater.inflate(R.layout.fragment_menu_list,container,false);

        //画面部品ListViewを取得
        ListView lvMenu = view.findViewById(R.id.lvMenu);

        List<Map<String,String>> menuList = new ArrayList<>();

///////データ作成//////////////////////////////////////////////////////////////////////////////
        Map<String,String> menu = new HashMap<>();
        menu.put("name","唐揚げ定食");
        menu.put("price","800");
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","ハンバーグ定食");
        menu.put("price","850");
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","海苔べん定食");
        menu.put("price","850");
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","白身魚定食");
        menu.put("price","850");
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);

        menu = new Hashtable<>();

        menu.put("name","刺身定食");
        menu.put("price","850");
        menu.put("desc","若鶏の唐揚げにサラダ、ご飯とお味噌汁がつきます。");
        menuList.add(menu);
        ///////////////////////////////////////////////////////////////////////////////////////

        //キー名の配列
        String[] from = {"name","price"};

        //上記に対応したvalue
        int[] to ={android.R.id.text1,android.R.id.text2};

        //Mapをアダプト　登録したmenuListを第二引数に
        SimpleAdapter adapter = new SimpleAdapter(_parentActivity,menuList,android.R.layout.simple_list_item_2,from,to);

        lvMenu.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }
}