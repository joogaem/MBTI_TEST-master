package com.example.mbtitest;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;


import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator2;


public class MyResult extends Activity {

    private BackPressCloseHandler backKeyClickHandler;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<MyAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myresult);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        backKeyClickHandler = new BackPressCloseHandler(this);  //뒤로 가기 버튼 핸들러 : 1번 토스트 팝업 , 2번 앱 종료

        Intent intent = getIntent();
        String rembti = intent.getExtras().getString("friend");
        //myDataset = intent.getExtras().getStringArrayList("allData");

        TextView tt = (TextView) findViewById(R.id.friend_mbti);
        tt.setText("나와 잘 맞는 MBTI는?  "+rembti);

        TextView tx3 = (TextView) findViewById(R.id.txtxss); //결과 멘트
        tx3.setText("당신과 지금 당장 찐친 가능한 아이돌은 바로 ... ");



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        //myDataset = (ArrayList<MyData>) intent.getSerializableExtra("allData");

        ArrayList<MyData> list = (ArrayList<MyData>) intent.getSerializableExtra("allData");
        myDataset.add(list.get(0)); //5개만 넣기 위함
        myDataset.add(list.get(1));
        myDataset.add(list.get(2));
        myDataset.add(list.get(3));
        myDataset.add(list.get(4));


        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


        SnapHelper snapHelper = new GravitySnapHelper(Gravity.CENTER);
        snapHelper.attachToRecyclerView(mRecyclerView);


        // 1)에서 만든 name, group 변수에 저장된 값 불러옴
        //myDataset.add(new MyData("group + name", R.drawable.hani));
        //myDataset.add(new MyData("test 이미지 디저트",R.drawable.test_img_dessert));

        CircleIndicator2 indicator = (CircleIndicator2) findViewById(R.id.indicator);
        indicator.attachToRecyclerView(mRecyclerView, snapHelper);
        // optional
        mAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

        Button btn = (Button) findViewById(R.id.btn);  //돌아가기 버튼

        btn.setText("다시하기");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);  //자신의 mbti를 선택하는 화면으로 다시 돌아가기
                startActivity(intent2);
            }
        });



    }


    //aos back 버튼을 1번 눌렀을 시, 토스트 팝업 노출 2번 눌렀을 시, 앱 종료

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backKeyClickHandler.onBackPressed();
    }

}