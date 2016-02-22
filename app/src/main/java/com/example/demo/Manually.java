package com.example.demo;

import com.example.demo.R;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;


public class Manually extends Activity {
    RobotControl robot;//傳到RobotControl

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manually);

        robot = new RobotControl();

        Intent e = getIntent();
        final Bundle extras = e.getExtras();
        final String ip = extras.getString("ip");
        final int port = extras.getInt("port");
        robot.ServerIP = ip;
        robot.SERVER_PORT = port;

        //Listen for button clicks
        Button button1 = (Button)findViewById(R.id.back_to_main);

        button1.setOnClickListener (new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(Manually.this, Choice_main.class);
                intent.putExtra("ip", ip);
                intent.putExtra("port", port);
                startActivity(intent);
                finish();
            }
        });

        ImageButton btFW = (ImageButton)this.findViewById(R.id.imbtn_forward);
        btFW.setOnClickListener(imbtn_forward);
        ImageButton btBW = (ImageButton)this.findViewById(R.id.imbtn_back);
        btBW.setOnClickListener(imbtn_back);
        ImageButton btLeft = (ImageButton)this.findViewById(R.id.imbtn_left);
        btLeft.setOnClickListener(imbtn_left);
        ImageButton btRight = (ImageButton)this.findViewById(R.id.imbtn_right);
        btRight.setOnClickListener(imbtn_right);
        ImageButton RM = (ImageButton)this.findViewById(R.id.rm);
        RM.setOnClickListener(imbtn_rm);
        ImageButton LN = (ImageButton)this.findViewById(R.id.ln);
        LN.setOnClickListener(imbtn_ln);

        Button btStop = (Button)this.findViewById(R.id.btn_stop);
        btStop.setOnClickListener(btn_stop);


        //speed
        Button btS1 = (Button)this.findViewById(R.id.btn_low);
        Button btS2 = (Button)this.findViewById(R.id.btn_mid);
        Button btS3 = (Button)this.findViewById(R.id.btn_high);
        btS1.setOnClickListener(btn_low);
        btS2.setOnClickListener(btn_mid);
        btS3.setOnClickListener(btn_high);


    }

    private OnClickListener imbtn_forward = new OnClickListener()
    {
        public void onClick(View v) {
            robot.RobotFW();
        }
    };
    private OnClickListener imbtn_back = new OnClickListener()
    {
        public void onClick(View v) {
            robot.RobotBW();
        }
    };
    private OnClickListener imbtn_left = new OnClickListener()
    {
        public void onClick(View v) {
            robot.RobotLeft();
        }
    };
    private OnClickListener imbtn_right = new OnClickListener()
    {
        public void onClick(View v) {
            robot.RobotRight();
        }
    };
    private OnClickListener imbtn_rm = new OnClickListener()
    {
        public void onClick(View v) {
            robot.RM();
        }
    };
    private OnClickListener imbtn_ln = new OnClickListener()
    {
        public void onClick(View v) {
            robot.LN();
        }
    };
    private OnClickListener btn_stop = new OnClickListener()
    {
        public void onClick(View v) {
            robot.RobotStop();
        }
    };
    //~~~~~~~~~~~~~~~~~~~~~~~speed_click~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private OnClickListener btn_low = new OnClickListener()
    {
        public void onClick(View v) {
            robot.S1();
        }
    };
    private OnClickListener btn_mid = new OnClickListener()
    {
        public void onClick(View v) {
            robot.S2();
        }
    };
    private OnClickListener btn_high = new OnClickListener()
    {
        public void onClick(View v) {
            robot.S3();
        }
    };


}
