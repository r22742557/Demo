package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//import android.widget.EditText;


public class connect extends Activity {
	Button bt_connect;
	EditText ip,port;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect);
        
        bt_connect = (Button)findViewById(R.id.bt_connect);
        ip = (EditText)findViewById(R.id.ip);
        port = (EditText)findViewById(R.id.port);
 
        
        bt_connect.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent = new Intent(connect.this,Choice_main.class);
        		intent.putExtra("ip", "192.168.1.254");//ip.getText().toString()
        	    int intport = Integer.parseInt(("5000"));//port.getText().toString()
        		intent.putExtra("port", intport); 
        		startActivity(intent);
        		finish();
        	}
        });
    }
}
