package com.example.finalproject3up;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject3up.R;
import com.example.finalproject3up.StartScreen;

import java.io.IOException;

public class CalenderActivity extends AppCompatActivity {
    CalendarView calendarView;
    Button btnWrite, btnBack;
    TextView mTextView;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);

        calendarView = findViewById(R.id.calenderView);
        btnWrite = findViewById(R.id.button);
        btnBack = findViewById(R.id.button2);
        mTextView = findViewById(R.id.textView5);
        ImageView imageView = findViewById(R.id.imageView2);

        //背景音樂
        mediaPlayer = MediaPlayer.create(CalenderActivity.this,R.raw.startmusic_u);
        mediaPlayer.setLooping(true);

        // 設定點擊事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic(); // 切換音樂播放狀態
            }
        });

        // calendarView 監聽事件
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 顯示用戶選擇的日期
                Toast.makeText(CalenderActivity.this, year + "年" + (month + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalenderActivity.this, StartScreen.class);
                startActivity(intent);
                Toast.makeText(CalenderActivity.this, "返回主選單", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("輸入文字");

        // 設置一個EditText控件來接收用戶輸入
        final EditText input = new EditText(this);
        builder.setView(input);

        // 設置確定按鈕
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 在確定按鈕點擊時取得用戶輸入的文字並設置到TextView中
                String userInput = input.getText().toString();
                mTextView.setText(userInput);
            }
        });

        // 設置取消按鈕
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
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
