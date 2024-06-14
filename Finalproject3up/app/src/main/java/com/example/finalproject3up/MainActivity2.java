package com.example.finalproject3up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private int number;
    private int time;
    private String[] words;

    private Map<String, String> loadedDictionary;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView TextViewTime, TextViewNumber;


        TextViewNumber = findViewById(R.id.textView2);
        TextViewTime = findViewById(R.id.textView3);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewResults);
        ImageView imageView = findViewById(R.id.imageView2);

        //背景音樂
        mediaPlayer = MediaPlayer.create(MainActivity2.this,R.raw.resultmusic_u);
        mediaPlayer.setLooping(true);

        // 設定點擊事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic(); // 切換音樂播放狀態
            }
        });


        // 獲取啟動該 Activity 的 Intent
        Intent intent = getIntent();

        number = intent.getIntExtra("Number", -1);
        time = intent.getIntExtra("Time", -1);
        words = intent.getStringArrayExtra("wordGroups");
        String[] questionData = intent.getStringArrayExtra("questionData");

        TextViewNumber.setText("題數：" + number + "題");
        TextViewTime.setText("用時：" + time + "秒");

        String[] chineseTranslations = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            Log.d("words", words[i]);
            // 你也可以在其他地方使用loadDictionary方法來讀取字典
            Map<String, String> loadedDictionary = DictionaryManager.loadDictionary(this);

            // 使用讀取的字典進行其他操作
            chineseTranslations[i] = loadedDictionary.get(words[i]);
            Log.d("Translation", "The Chinese translation of 'dream' is: " + chineseTranslations[i]);
        }
        loadedDictionary = DictionaryManager.loadDictionary(this);

        // 在 MainActivity2 中的 onCreate 方法中
        RecyclerView recyclerView1 = findViewById(R.id.recyclerViewResults);
        QuestionAdapter adapter = new QuestionAdapter(questionData, chineseTranslations, this, loadedDictionary);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 獲取按鈕
        Button btnBackToStartScreen;
        btnBackToStartScreen = findViewById(R.id.button4);

        // 設置按鈕點擊事件
        btnBackToStartScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在按鈕點擊時執行的操作
                backToStartScreen();
            }
        });
    }

    //靜音
    private void toggleMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(mediaPlayer!=null){
                mediaPlayer.stop();
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.stop();
        mediaPlayer.release();
    }

    // 新增一個方法，用於返回主畫面
    private void backToStartScreen() {
        Intent intent = new Intent(this, StartScreen.class);
        startActivity(intent);
        finish();  // 結束當前的 MainActivity2
    }

}