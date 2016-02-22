package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import android.view.Menu;



public class Automatically extends Activity {

	final String[] point = {"起點","障礙點","目的點"};
	int a = 0;    
    Button button;   
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.automatically);
	    
	    
	    //Listen for button clicks
        final Button button1 = (Button)findViewById(R.id.back_to_main);
        button1.setOnClickListener(back_to_main);
        Button button3 = (Button)findViewById(R.id.to_3);
        button3.setOnClickListener(to_3);
        Button button4 = (Button)findViewById(R.id.to_4);
        button4.setOnClickListener(to_4);
        Button button5 = (Button)findViewById(R.id.to_5);
        button5.setOnClickListener(to_5);        
        
        
 }
        
        
                             
                	
	

    private OnClickListener back_to_main = new OnClickListener()
    {
        public void onClick(View v) 
        {
          Intent e = getIntent();
          final Bundle extras = e.getExtras();
          String ip = extras.getString("ip");
          int port = extras.getInt("port");
          Intent intent = new Intent();
          intent.setClass(Automatically.this, Choice_main.class);
          intent.putExtra("ip", ip);
  		  intent.putExtra("port", port);
          startActivity(intent);
          finish();
        }
    };
    ////////////////////////  to_map  /////////////////////////
    private OnClickListener to_3 = new OnClickListener()
    {
        public void onClick(View v) 
        {
          Intent e = getIntent();
          final Bundle extras = e.getExtras();
          String ip = extras.getString("ip");
          int port = extras.getInt("port");
          
          Intent intent = new Intent();
          intent.setClass(Automatically.this, Map_4.class);
          intent.putExtra("ip", ip);
  		  intent.putExtra("port", port);
          startActivity(intent);
          finish();
        }
    };
    private OnClickListener to_4 = new OnClickListener()
    {
        public void onClick(View v) 
        {
          Intent e = getIntent();
          final Bundle extras = e.getExtras();
          String ip = extras.getString("ip");
          int port = extras.getInt("port");
            
          Intent intent = new Intent();
          intent.setClass(Automatically.this, Map_4.class);
          intent.putExtra("ip", ip);
  		  intent.putExtra("port", port);
          startActivity(intent);
          finish();
        }
    };
    private OnClickListener to_5 = new OnClickListener()
    {
        public void onClick(View v) 
        {
          Intent e = getIntent();
          final Bundle extras = e.getExtras();
          String ip = extras.getString("ip");
          int port = extras.getInt("port");
          
          Intent intent = new Intent();
          intent.setClass(Automatically.this, Map_4.class);
          intent.putExtra("ip", ip);
  		  intent.putExtra("port", port);
          startActivity(intent);
          finish();
        }
    };  

    
}
