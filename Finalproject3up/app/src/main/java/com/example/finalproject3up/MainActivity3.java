package com.example.finalproject3up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 假設你的佈局中有一個id為"recyclerView"的RecyclerView
        RecyclerView recyclerView = findViewById(R.id.level);

        // 創建一個 LevelModel 對象的列表
        List<LevelModel> levelList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            levelList.add(new LevelModel("關卡 " + i));
        }
        //背景音樂
        mediaPlayer = MediaPlayer.create(MainActivity3.this,R.raw.startmusic_u);
        mediaPlayer.setLooping(true);

        // 創建自定義適配器的實例
        LevelAdapter levelAdapter = new LevelAdapter(this, levelList);

        // 將適配器和佈局管理器設置給RecyclerView
        recyclerView.setAdapter(levelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}
