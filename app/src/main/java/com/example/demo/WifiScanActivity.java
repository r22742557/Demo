package com.example.demo;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WifiScanActivity extends ListActivity {
    /** Called when the activity is first created. */
	Button bt_scan;
	TextView empty;
	ArrayList<String> list;
	ArrayAdapter<String> adapter;
	List<ScanResult> scan_result;
	WifiManager wifi;
	WifiInfo info;
	WifiConfiguration wifi_config;
	BroadcastReceiver reciver;
	int networkId,i;
	String Cur_SSID,password;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi);
        bt_scan = (Button)findViewById(R.id.btscan); // WiFi����
        empty = (TextView)findViewById(R.id.empty); // 瘨����蝯�
        list = new ArrayList<String>(); // ArrayList銝剝��亙銝��ListView憿舐內��蝯���
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list); //�湔��銵刻���
		wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE); //wifimanger WiFi��嚗身蝵桃�
        wifi_config = new WifiConfiguration();
        setListAdapter(adapter);
        bt_scan.setOnClickListener(new OnClickListener(){
        // 閮餃�ListView銝剜��支��istView�������典��IFI AP��
        	//@Override
        	public void onClick(View v){
        		list.clear();
        		wifi.setWifiEnabled(true);
        		wifi.startScan();
        		scan_result = wifi.getScanResults();
        		try{
        			for(int i=0;i<scan_result.size();i++)
					{
            			list.add(scan_result.get(i).SSID+" "+scan_result.get(i).capabilities);

            		}
        			empty.setText("");
        		}
        		catch(Exception e){
        			Log.d("Exception",""+e+"");
        		}



        		adapter.notifyDataSetChanged();
        	}
        });
    }




	// 憒�雿���istView AP�岫���圈摰�AP
	@Override
	protected void onListItemClick(ListView list,View v,int position,long id){
		super.onListItemClick(list, v, position, id);
		Toast.makeText(this, scan_result.get(position).SSID, Toast.LENGTH_SHORT).show();
		Cur_SSID = scan_result.get(position).SSID;
		wifi_config.SSID = "\"".concat(scan_result.get(position).SSID).concat("\"");
		wifi_config.status = WifiConfiguration.Status.DISABLED;
		wifi_config.priority = 40;
				
		//if(scan_result.get(position).capabilities.equals(""))
		//{
		//�詨������閮剔蔭WIFI AP嚗�嚗�
			wifi_config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			wifi_config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);  
			wifi_config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);  
			wifi_config.allowedAuthAlgorithms.clear();  
			wifi_config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);  
			wifi_config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);  
			wifi_config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);  
			wifi_config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);  
			wifi_config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP); 
			last_common_wifi_set();
		//}
		//else if(scan_result.get(position).capabilities.equals("[WEP]"))
		//{
		//	Intent intent = new Intent(getBaseContext(), PasswordActivity.class);
		//	startActivityForResult(intent,1);
		//}
		
	}
	public void last_common_wifi_set()
	{
		List<WifiConfiguration> netList = wifi.getConfiguredNetworks();
		for(i=0;i<netList.size();i++)
		{
			if(netList.get(i).SSID.equals("\""+wifi.getConnectionInfo().getSSID()+"\"")){
				wifi.removeNetwork(netList.get(i).networkId); 
			}
		}
		networkId =	wifi.addNetwork(wifi_config);
		
		if(networkId != -1){
			if(wifi.enableNetwork(networkId, true))//蝬脩窗�寥嚗����銵�.....
			{	
				wifi.saveConfiguration();//���啁雯蝯∩����脣撱箇���
				Toast.makeText(this, "已鏈接",Toast.LENGTH_SHORT).show();
				Intent wifi_intent = new Intent(WifiScanActivity.this,connect.class);
				wifi_intent.putExtra("SSID", Cur_SSID);
				wifi_intent.putExtra("password", password);
				startActivity(wifi_intent);
			}
			else
			{
				Toast.makeText(this, "連接失敗，請再試一次。",Toast.LENGTH_SHORT).show();
			}
		}
		else
		{
			Toast.makeText(this, "not connected.........",Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public void onActivityResult(int requsetCode,int resultCode,Intent data)
	{
		super.onActivityResult(requsetCode, resultCode, data);
		if(resultCode==RESULT_OK) // 瘣餃�甇�虜����   
		{             
			if(requsetCode==1)       
			{              
				password = data.getStringExtra("password"); 
				if(password.equals(""))
				{
					Toast.makeText(this, "Key值錯誤",Toast.LENGTH_SHORT).show();
				}
				else
				{
					//�∠�AP��閮剔蔭�典��堆�WEP嚗�
					wifi_config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
					wifi_config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
					wifi_config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
					wifi_config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
					wifi_config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
					wifi_config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
					wifi_config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
					wifi_config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
					wifi_config.wepKeys[0]="\"".concat(password).concat("\"");
					wifi_config.wepTxKeyIndex=0;
					last_common_wifi_set();
				}
			}         
		} 
	}
}