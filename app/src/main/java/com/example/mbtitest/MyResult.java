package com.example.mbtitest;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

//7.30 추가
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


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

        AssetManager assetManager= getAssets(); //7.30 추가

        //7.30 추가, json파일 불러오는 부분. try-catch 사용
        try {
            InputStream is= assetManager.open("mbti_csv_2.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            //불러온 파일 JsonArray에 저장
            String jsonData= buffer.toString();
            JSONArray jsonArray= new JSONArray(jsonData);

            //index 1에 저장된 정보를 불러옴,
            //추후엔 조건절로 mbti에 맞는 사람들 별도 Array에 넣어서 반복문 돌리면 될듯
            JSONObject jo=jsonArray.getJSONObject(1);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name= jo.getString("name");
            String group= jo.getString("group");

        //내코드 시작
            TextView tx3 = (TextView) findViewById(R.id.txtxss); //결과 멘트
            tx3.setText("당신과 지금 당장 찐친 가능한 아이돌은 바로 ... "+name+" "+group);

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
            myDataset.add(new MyData(group + " " + name, R.drawable.hani));
            myDataset.add(new MyData("test 이미지 디저트",R.drawable.test_img_dessert));

        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }

    }

}