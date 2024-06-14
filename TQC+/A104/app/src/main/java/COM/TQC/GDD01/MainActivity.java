package COM.TQC.GDD01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button mButton01;
    private Button mButton02;
    private ImageView mImageView;
    private final Context context = this; //重要的隱藏考點

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //context = getApplicationContext();
        mImageView=(ImageView) findViewById(R.id.imageView);
        //    TO DO  (1) 按下「圖片」按鈕，顯示一個「YES/NO對話視窗」，對話窗標題顯示【要顯示圖片嗎？】，對話內容顯示【選擇YES 會顯示圖片】，當按下【YES】顯示「R.drawable.tca」的檔案在ImageView當中。 當按下【NO】顯示，清空下方ImageView圖片。

        mButton01=(Button) findViewById(R.id.button);
        mButton01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("要顯示圖片嗎？")
                        .setMessage("選擇YES 會顯示圖片")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Drawable drawable = getDrawable(R.drawable.csf); //mImageView.setImageDrawable(R.drawable.drawable.csf)
                                mImageView.setImageDrawable(drawable);
                                mImageView.setVisibility(View.VISIBLE); //顯示

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mImageView.setImageDrawable(null);
                                mImageView.setVisibility(View.GONE); //消失
                            }
                        }).show();

            }
        });



        //    TO DO  (2) 當按下「清除」按鈕，清空下方ImageView圖片，並用Toast訊息方式於畫面中顯示【已經清除圖片】文字。
        mButton02=(Button) findViewById(R.id.button2);

        mButton02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setImageDrawable(null);
                mImageView.setVisibility(View.GONE); //消失
                Toast.makeText(context,"已經清除圖片",Toast.LENGTH_LONG).show();
            }
        });
        //   TO DO  (3) 當ImageView有圖片時，點選該圖會以Log.d訊息方式設定tag為debug並顯示【你點選了圖片】。
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug","你點選了圖片");
            }
        });

    }
}
