package com.example.mbtitest;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;



public class MyResult extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myresult);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        //내코드 시작
            TextView tx3 = (TextView) findViewById(R.id.txtxss); //결과 멘트
            tx3.setText("당신과 지금 당장 찐친 가능한 아이돌은 바로 ... ");

        //내코드 끝


            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            //mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

            // specify an adapter (see also next example)
            myDataset = new ArrayList<>();
            mAdapter = new MyAdapter(myDataset);
            mRecyclerView.setAdapter(mAdapter);


            // 1)에서 만든 name, group 변수에 저장된 값 불러옴
            myDataset.add(new MyData("group + name", R.drawable.hani));
            myDataset.add(new MyData("test 이미지 디저트",R.drawable.test_img_dessert));



    }

}