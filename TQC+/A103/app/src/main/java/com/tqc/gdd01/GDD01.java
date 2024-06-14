package com.tqc.gdd01;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class GDD01 extends Activity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
   // TO DO
    final EditText mEditAccount,mEditPassword;
    final CheckBox mCheckbox;

    mEditAccount = findViewById(R.id.mAccount);
    mEditPassword = findViewById(R.id.mPassword);
    mCheckbox = findViewById(R.id.mCheck);

    mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { //boolean為isChecked
        if(isChecked){
          mEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
          mEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

      }
    });



  }
}
