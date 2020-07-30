package com.example.mbtitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler bpc; //뒤로 가기 버튼 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bpc = new BackPressCloseHandler(this); //객체 생성

        TextView textView1 = (TextView) findViewById(R.id.text1) ;
        textView1.setText("나와 잘 맞는 MBTI를 가진 연예인은 누구?") ;

        TextView text2 = (TextView) findViewById(R.id.text2);
        text2.setText("아이돌편");

        Button button = (Button) findViewById(R.id.start1);  //시작하기 버튼
        button.setText("시작하기     >");

        // 다음 페이지로 이동
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Mymbti.class);
                startActivity(intent);
            }
        });

    }
    //1번 누르면 토스트 팝업, 두번 누르면 종료
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        bpc.onBackPressed();
    }
}