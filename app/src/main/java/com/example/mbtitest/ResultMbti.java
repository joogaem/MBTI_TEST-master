package com.example.mbtitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class ResultMbti extends AppCompatActivity {

    private BackPressCloseHandler backKeyClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultmbti);
        backKeyClickHandler = new BackPressCloseHandler(this);  //뒤로 가기 버튼 핸들러 : 1번 토스트 팝업 , 2번 앱 종료




        Intent intent = getIntent();
        String receiveStr = intent.getExtras().getString("sendData");// 전달한 값을 받을 때(mbti 값)

        //TextView tx  = (TextView) findViewById(R.id.tx1);
        //tx.setText(receiveStr);

        //String[] mbti = {"ENFJ", "ENFP","ENTJ","ENTP", "ESFJ", "ESFP", "ESTJ", "ESTP", "INFJ", "INFP","INTJ","INTP","ISFJ", "ISFP", "ISTJ", "ISTP"};

        //전체 mbti를 해시맵에 저장한 뒤 각 value를 index로 쓸 예정
        HashMap<String,Integer> mbti = new HashMap<>();
        mbti.put("ENFJ",0); mbti.put("ENFP",1); mbti.put("ENTJ",2); mbti.put("ENTP",3); mbti.put("ESFJ",4);
        mbti.put("ESFP",5); mbti.put("ESTJ",6); mbti.put("ESTP",7); mbti.put("INFJ",8); mbti.put("INFP",9);
        mbti.put("INTJ",10); mbti.put("INTP",11); mbti.put("ISFJ",12); mbti.put("ISFP",13);mbti.put("ISTJ",14);mbti.put("ISTP",15);


        String[][] result_m = new String[16][2];
        result_m[0][0] = "INFP"; result_m[0][1] = "ISFP";
        result_m[1][0] = "INFJ"; result_m[1][1] = "INTJ";
        //앞으로 14*2 더 추가

        Random rd = new Random();
        int random = rd.nextInt(2);


        //해시맵에서 자신의 mbti 인덱스를 get한 후, result_m 배열에서 관련 유형 랜덤으로 뽑아서 출력
        int idx = 0; //mbti 해시맵 인덱스 받기 위한 변수
        idx = mbti.get(receiveStr).intValue();

        String relation_mbti = result_m[idx][random];  //해당 사용자의 mbti와 잘 맞는 mbti 랜덤으로 출력

        TextView result = (TextView) findViewById(R.id.mbtiresult);
        result.setText("나와 잘 맞는 mbti 유형은? ");
        TextView rr = (TextView) findViewById(R.id.mbtii);
        rr.setText(relation_mbti);

        //String index2 = Integer.toString(index);

        //TextView tx2  = (TextView) findViewById(R.id.tx2);
        //tx2.setText(index2);

        switch (idx){
            case 0:
                ENFJ();
                break;
            case 1:
                ENFP();
                break;
            case 2:
                ENTJ();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                ISTP();
                break;
        }

        Button btn = (Button) findViewById(R.id.btn);  //돌아가기 버튼

        btn.setText("⟳ 돌아가기");



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

    void ENFJ () {
        TextView tx2 = (TextView) findViewById(R.id.tx2);
        tx2.setText("에이프릴 나은"); //멘트 수정해야 함
    }

    void ENFP(){
        //TextView tx3 = (TextView) findViewById(R.id.tx2);
        //tx3.setText("EXID 하니"); //멘트 수정해야 함

        //ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //imageView.setImageResource(R.drawable.hani);

    }

    void ENTJ(){
        TextView tx2 = (TextView) findViewById(R.id.tx2);
        tx2.setText("ENTJ 아이돌 이름 자리 "); //멘트 수정해야 함
    }

    void ISTP(){
        //카드뷰 연습,,, 지금 intet로 넘기는데 이러면 안 되고 xml / java 다 합쳐야 함.
        Intent intent22 = new Intent(getApplicationContext(),MyResult.class);
        startActivity(intent22);
    }
}


