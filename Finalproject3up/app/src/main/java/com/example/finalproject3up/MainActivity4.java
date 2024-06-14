package com.example.finalproject3up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity4 extends AppCompatActivity {

    private Button button;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button = findViewById(R.id.button4);
        //背景音樂
        mediaPlayer = MediaPlayer.create(MainActivity4.this,R.raw.startmusic_u);
        mediaPlayer.setLooping(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 使用 Intent 啟動新的 Activity
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                intent.putExtra("source", "MainActivity4");
                startActivity(intent);
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

}