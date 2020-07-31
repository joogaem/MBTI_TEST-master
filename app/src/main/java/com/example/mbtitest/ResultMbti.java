package com.example.mbtitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.print.PrintJob;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Random;

public class ResultMbti extends AppCompatActivity {

    private BackPressCloseHandler backKeyClickHandler;
    JSONArray jsonArray_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultmbti);
        backKeyClickHandler = new BackPressCloseHandler(this);  //뒤로 가기 버튼 핸들러 : 1번 토스트 팝업 , 2번 앱 종료

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
            JSONArray jsonArray = new JSONArray(jsonData);
            jsonArray_p=jsonArray;
            JSONObject jo = jsonArray_p.getJSONObject(1);


        } catch (IOException | JSONException e) {e.printStackTrace();}

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
        result_m[2][0] = "INFP"; result_m[2][1] = "INTJ";
        result_m[3][0] = "INFJ"; result_m[3][1] = "INTJ";
        result_m[4][0] = "ISFP"; result_m[4][1] = "ISTP";
        result_m[5][0] = "ISFJ"; result_m[5][1] = "ISTJ";
        result_m[6][0] = "INTP"; result_m[6][1] = "ISFP"; //3개네..
        result_m[7][0] = "ISFJ"; result_m[7][1] = "ISTJ";
        result_m[8][0] = "ENFP"; result_m[8][1] = "ENTP";
        result_m[9][0] = "ENFJ"; result_m[9][1] = "ENTJ";
        result_m[10][0] = "ENFP"; result_m[10][1] = "ENTP";
        result_m[11][0] = "ENTJ"; result_m[11][1] = "ESTJ";
        result_m[12][0] = "ESFP"; result_m[12][1] = "ESTP";
        result_m[13][0] = "ENFJ"; result_m[13][1] = "ESFJ"; //3개
        result_m[14][0] = "ESFP"; result_m[14][1] = "ESTP";
        result_m[15][0] = "ESFJ"; result_m[15][1] = "ESTJ";
        //앞으로 14*2 더 추가

        Random rd = new Random();
        int random = rd.nextInt(2);

        //해시맵에서 자신의 mbti 인덱스를 get한 후, result_m 배열에서 관련 유형 랜덤으로 뽑아서 출력
        int idx = 0; //mbti 해시맵 인덱스 받기 위한 변수
        idx = mbti.get(receiveStr).intValue();

        String relation_mbti = result_m[idx][random];  //해당 사용자의 mbti와 잘 맞는 mbti 랜덤으로 출력

        TextView result = (TextView) findViewById(R.id.mbtiresult);
        result.setText("나와 잘 맞는 mbti유형은?");

        TextView rr = (TextView) findViewById(R.id.mbtii);
        rr.setText(relation_mbti);

        //r_idx = relation index; 매칭된 mbti의 idx가져와야함
        int r_idx=0;
        r_idx= mbti.get(relation_mbti).intValue();


        //String index2 = Integer.toString(index);

        //TextView tx2  = (TextView) findViewById(R.id.tx2);
        //tx2.setText(index2);
        System.out.println("ss");

        switch (r_idx){
            case 0:
                try {
                    ENFJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    ENFP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    ENTJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    ENTP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    ESFJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    ESFP();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                try {
                    ESTJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 7:
                try {
                    ESTP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 8:
                try {
                    INFJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 9:
                try {
                    INFP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 10:
                try {
                    INTJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 11:
                try {
                    INTP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 12:
                try {
                    ISFJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 13:
                try {
                    ISFP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 14:
                System.out.println("14, ISTJ");
                try {
                    ISTJ();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case 15:
                System.out.println("15, ISTP");
                try {
                    ISTP();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    //idx=0
    void ENFJ () throws JSONException {

        for (int i=74; i<108;i++) {

            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index i인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+name); //멘트 수정해야 함
        }
    }

    //idx=1
    void ENFP() throws JSONException {
        //TextView tx3 = (TextView) findViewById(R.id.tx2);
        //tx3.setText("EXID 하니"); //멘트 수정해야 함

        //ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //imageView.setImageResource(R.drawable.hani);

        for (int i=0; i<38;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=2
    void ENTJ() throws JSONException {
        for (int i=194; i<201;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=3
    void ENTP() throws JSONException {
        for (int i=215; i<222;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=4
    void ESFJ() throws JSONException {
        for (int i=132; i<146;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=5
    void ESFP() throws JSONException {
        for (int i=184; i<194;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=6
    void ESTJ() throws JSONException {
        for (int i=236; i<238;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=7
    void ESTP() throws JSONException {
        for (int i=232; i<236;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=8
    void INFJ() throws JSONException {
        for (int i=166; i<184;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=9
    void INFP() throws JSONException {
        for (int i=38; i<74;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=10
    void INTJ() throws JSONException {
        for (int i=230; i<233;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=11
    void INTP() throws JSONException {
        for (int i=222; i<230;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=12
    void ISFJ() throws JSONException {
        for (int i=108; i<132;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=13
    void ISFP() throws JSONException {
        for (int i=146; i<166;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=14
    void ISTJ() throws JSONException {
        for (int i=207; i<215;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }

    //idx=15
    void ISTP() throws JSONException {
        for (int i=201; i<207;i++) {
            JSONObject jo = jsonArray_p.getJSONObject(i);

            //index 1인 row의 name필드와 group필드값 불러옴 1)
            String name = jo.getString("name");
            String group = jo.getString("group");
            System.out.println(group+" "+name);
            TextView tx2 = (TextView) findViewById(R.id.tx2);
            tx2.setText(group+" "+name); //멘트 수정해야 함
        }
    }


}


