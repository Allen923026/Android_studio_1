package com.example.finalproject3up;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class StartScreen extends AppCompatActivity {

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        mediaPlayer = MediaPlayer.create(StartScreen.this,R.raw.startmusic_u);
        mediaPlayer.setLooping(true);

        Button btnNormalMode,btnTimeLimitedMode,btnMemberMode,btnCalender;
        ImageView imageView = findViewById(R.id.imageView2);
        btnNormalMode = findViewById(R.id.button4);
        btnTimeLimitedMode = findViewById(R.id.button2);
        btnMemberMode = findViewById(R.id.button3);
        btnCalender =findViewById(R.id.button5);
        // 設定點擊事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic(); // 切換音樂播放狀態
            }
        });

        btnNormalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡處理一般模式按鈕的點擊事件
                Intent intent = new Intent(StartScreen.this, MainActivity3.class);
                startActivity(intent);
                Toast.makeText(StartScreen.this, "你選擇了一般模式", Toast.LENGTH_LONG).show();
            }
        });

        btnTimeLimitedMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡處理限時模式按鈕的點擊事件
                Intent intent = new Intent(StartScreen.this,MainActivity4.class);
                startActivity(intent);
                Toast.makeText(StartScreen.this, "你選擇了限時模式", Toast.LENGTH_LONG).show();
            }
        });

        btnMemberMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡處理會員模式按鈕的點擊事件
                Intent intent = new Intent(StartScreen.this,MainActivity3.class);
                startActivity(intent);
                Toast.makeText(StartScreen.this, "你選擇了會員模式", Toast.LENGTH_LONG).show();

            }
        });

        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, CalenderActivity.class);
                startActivity(intent);
                Toast.makeText(StartScreen.this, "你進入了日誌", Toast.LENGTH_LONG).show();
            }
        });



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
    private void toggleMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }
    }
}
