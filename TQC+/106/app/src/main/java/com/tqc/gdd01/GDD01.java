package com.tqc.gdd01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GDD01 extends Activity {
  private static final String TAG = "Android_Log";
  private TextView tv;
  private Button b1;
  private Button b2;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // TO DO 加入Log
    tv = (TextView) findViewById(R.id.text1);
    b1 = (Button) findViewById(R.id.button1);
    b2 = (Button) findViewById(R.id.button2);

    //TO DO: b1.setOnClickListener

    // TO DO: b2.setOnClickListener
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // TO DO
  }
}

 // TO DO 在onCreate()、onStart()、onResume()、onPause()、onStop()、onRestart()、onDestroy()這些Method裡加入Log的設定

