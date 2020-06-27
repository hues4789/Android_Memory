package com.example.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MenuThanksFragment extends Fragment {

    private Activity _parentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //フラグメントのViewを呼び出す
        View view = inflater.inflate(R.layout.fragment_menu_thanks,container,false);

        //データを引き継ぎ
        Intent intent = _parentActivity.getIntent();
        Bundle extras = intent.getExtras();

        //定食名と金額
        String menuName = "";
        String menuPrice = "";

        //引き継いだデータが存在すればセットされる
        if(extras != null){
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }

        //定食名と金額を表示させるTextView
        TextView tvMenuName = view.findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);

        //TextViewに定食名と金額を表示
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        //戻るボタン
        Button btBackButton = view.findViewById(R.id.btBackButton);
        //戻るボタンにリスなを登録
        btBackButton.setOnClickListener(new ButtonClickListener());

        // Inflate the layout for this fragment
        return view;
    }


    private class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            _parentActivity.finish();
        }
    }


}