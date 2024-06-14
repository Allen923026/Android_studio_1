package com.tqc.gdd01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GDD01 extends Activity
{
  private Button button1;
  private TextView text1;
  private String[] s1={"美味蟹堡","義式香腸堡","蔬菜水果堡","香蕉潛艇堡","香烤雞肉堡"};
  private boolean[] checkedList = new boolean[s1.length];

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    button1 = (Button) findViewById(R.id.button1);
    text1 = (TextView) findViewById(R.id.text1);

    button1.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        //TO DO



      }
    });
  }
}

