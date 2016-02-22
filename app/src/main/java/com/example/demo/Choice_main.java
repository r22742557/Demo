package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import android.view.Menu;


public class Choice_main extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {  //如果APP是使用者執行 會是NULL 否則可以利用引數取得APP之前執行狀態
        super.onCreate(savedInstanceState);  //呼叫基礎類別的onCreate(),並且把傳進來的savedInstanceState物件傳給它處理
        setContentView(R.layout.choice_main);    //顯示    //R.layout指定R類別中的layout類別

        //Listen for button clicks
        Button button1 = (Button) this.findViewById(R.id.main_to_manu);
        button1.setOnClickListener(manu_form_show);
        Button button2 = (Button) this.findViewById(R.id.main_to_auto);
        button2.setOnClickListener(auto_form_show);
    }

    private OnClickListener manu_form_show = new OnClickListener()
    {
        public void onClick(View v) //事件處理
        {
            Intent e = getIntent();
            final Bundle extras = e.getExtras();
            String ip = extras.getString("ip");
            int port = extras.getInt("port");

            Intent intent = new Intent();
            intent.setClass(Choice_main.this, Manually.class);//跳轉頁面 也可寫成Intent intent = new Intent(Choice_main.this, Manually.class);
            intent.putExtra("ip", ip);
            intent.putExtra("port", port);
            startActivity(intent);
            finish();
        }
    };

    private OnClickListener auto_form_show = new OnClickListener()
    {
        public void onClick(View v)
        {
            Intent e = getIntent();
            final Bundle extras = e.getExtras();
            String ip = extras.getString("ip");
            int port = extras.getInt("port");

            Intent intent = new Intent();
            intent.setClass(Choice_main.this, Automatically.class);
            intent.putExtra("ip", ip);
            intent.putExtra("port", port);
            startActivity(intent);
            finish();
        }
    };


}
