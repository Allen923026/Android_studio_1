package com.tqc.gdd03;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.IOException;

public class GDD03 extends Activity
{
  /** Called when the activity is first created. */
  MediaPlayer mediaPlayer;
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mediaPlayer = MediaPlayer.create(GDD03.this,R.raw.light);
    mediaPlayer.setLooping(true);

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