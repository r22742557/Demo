package com.example.demo;

import android.R.style;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class PasswordActivity extends Activity {
	
	EditText et;
	Button btn;
	String str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pass);
        et = (EditText)findViewById(R.id.et);
        btn = (Button)findViewById(R.id.confirmBtn);
        btn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) { 
        		Intent intent = getIntent();
        		intent.putExtra("password", et.getText().toString());
        		setResult(RESULT_OK,intent);
        		finish();
        	}
        });
    }
    /**
     * Callback for Theme Style setting
     */
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first); 
       theme.applyStyle(style.Theme_Panel, true);
    }
}