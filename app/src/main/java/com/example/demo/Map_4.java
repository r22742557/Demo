package com.example.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Map_4 extends Activity {
	int a = 0;
	int[] state = new int[16];
	int[] start = new int[16];
	int flag = 0;
	public static int count_goal = 1;

	Button[] button_4x = new Button [16];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_4);

		//Listen for button clicks
		Button button_b = (Button)findViewById(R.id.back);
		button_b.setOnClickListener(back);
		Button button_n = (Button)findViewById(R.id.next);
		button_n.setOnClickListener(go_next);

		button_4x[0] = (Button) this.findViewById(R.id.btn_41);
		button_4x[1] = (Button) this.findViewById(R.id.btn_42);
		button_4x[2] = (Button) this.findViewById(R.id.btn_43);
		button_4x[3] = (Button) this.findViewById(R.id.btn_44);
		button_4x[4] = (Button) this.findViewById(R.id.btn_45);
		button_4x[5] = (Button) this.findViewById(R.id.btn_46);
		button_4x[6] = (Button) this.findViewById(R.id.btn_47);
		button_4x[7] = (Button) this.findViewById(R.id.btn_48);
		button_4x[8] = (Button) this.findViewById(R.id.btn_49);
		button_4x[9] = (Button) this.findViewById(R.id.btn_410);
		button_4x[10] = (Button) this.findViewById(R.id.btn_411);
		button_4x[11] = (Button) this.findViewById(R.id.btn_412);
		button_4x[12] = (Button) this.findViewById(R.id.btn_413);
		button_4x[13] = (Button) this.findViewById(R.id.btn_414);
		button_4x[14] = (Button) this.findViewById(R.id.btn_415);
		button_4x[15] = (Button) this.findViewById(R.id.btn_416);

		button_4x[0].setOnClickListener(btn_41);
		button_4x[1].setOnClickListener(btn_42);
		button_4x[2].setOnClickListener(btn_43);
		button_4x[3].setOnClickListener(btn_44);
		button_4x[4].setOnClickListener(btn_45);
		button_4x[5].setOnClickListener(btn_46);
		button_4x[6].setOnClickListener(btn_47);
		button_4x[7].setOnClickListener(btn_48);
		button_4x[8].setOnClickListener(btn_49);
		button_4x[9].setOnClickListener(btn_410);
		button_4x[10].setOnClickListener(btn_411);
		button_4x[11].setOnClickListener(btn_412);
		button_4x[12].setOnClickListener(btn_413);
		button_4x[13].setOnClickListener(btn_414);
		button_4x[14].setOnClickListener(btn_415);
		button_4x[15].setOnClickListener(btn_416);

	}

	private OnClickListener back = new OnClickListener()
	{
		public void onClick(View v)
		{

			count_goal=1;
			Button button_b = (Button)findViewById(R.id.back);
			button_b.setBackgroundResource(R.drawable.pho_back_2);
			Intent e = getIntent();
			final Bundle extras = e.getExtras();
			String ip = extras.getString("ip");
			int port = extras.getInt("port");

			Intent intent = new Intent();
			intent.setClass(Map_4.this, Automatically.class);
			intent.putExtra("ip", ip);
			intent.putExtra("port", port);

			startActivity(intent);
			finish();
		}
	};

	private OnClickListener go_next = new OnClickListener()
	{
		public void onClick(View v)
		{

			Intent e = getIntent();
			final Bundle extras = e.getExtras();
			String ip = extras.getString("ip");
			int port = extras.getInt("port");

			Intent intent = new Intent(Map_4.this, Map_4_m.class);
			intent.putExtra("ip", ip);
			intent.putExtra("port", port);

			Bundle bundle = new Bundle();
			bundle.putString("String41", button_4x[0].getText().toString());
			bundle.putString("String42", button_4x[1].getText().toString());
			bundle.putString("String43", button_4x[2].getText().toString());
			bundle.putString("String44", button_4x[3].getText().toString());
			bundle.putString("String45", button_4x[4].getText().toString());
			bundle.putString("String46", button_4x[5].getText().toString());
			bundle.putString("String47", button_4x[6].getText().toString());
			bundle.putString("String48", button_4x[7].getText().toString());
			bundle.putString("String49", button_4x[8].getText().toString());
			bundle.putString("String410", button_4x[9].getText().toString());
			bundle.putString("String411", button_4x[10].getText().toString());
			bundle.putString("String412", button_4x[11].getText().toString());
			bundle.putString("String413", button_4x[12].getText().toString());
			bundle.putString("String414", button_4x[13].getText().toString());
			bundle.putString("String415", button_4x[14].getText().toString());
			bundle.putString("String416", button_4x[15].getText().toString());
			bundle.putIntArray("state", state);
			intent.putExtras(bundle);
			startActivity(intent);
			count_goal=1;
			finish();
		}
	};


	private OnClickListener btn_41 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[0]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {



						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[0].setText(point[which]);
								button_4x[0].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[0]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else
							button_4x[0].setText(point[which]);
						if(point[which].equals("障礙點"))
						{
							button_4x[0].setBackgroundResource(R.drawable.btn_stop);
							button_4x[0].setText(" ");
							if(start[0]==1){
								start[0]=0;
								flag=0;
							}
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[0]=count_goal;
							count_goal++;
							button_4x[0].setBackgroundResource(R.drawable.btn_end);
							if(start[0]==1){
								start[0]=0;
								flag=0;
							}
						}
						if(point[which].equals("節點"))
						{
							button_4x[0].setBackgroundResource(R.drawable.btn_default);
							if(start[0]==1){
								start[0]=0;
								flag=0;
							}
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[0])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[0].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[0].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[0].setBackgroundResource(R.drawable.btn_stop);
							button_4x[0].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[0])))
						{
							button_4x[0].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[0].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[0]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[0])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[0]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}

		}
	};

	private OnClickListener btn_42 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[1]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {


						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[0].setText(point[which]);
								button_4x[1].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[1]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else
							button_4x[1].setText(point[which]);
						if(point[which].equals("障礙點"))
						{
							button_4x[1].setBackgroundResource(R.drawable.btn_stop);
							button_4x[1].setText(" ");
							if(start[1]==1){
								start[1]=0;
								flag=0;
							}
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[1]=count_goal;
							count_goal++;
							button_4x[1].setBackgroundResource(R.drawable.btn_end);
							if(start[1]==1){
								start[1]=0;
								flag=0;
							}
						}
						if(point[which].equals("節點"))
						{
							button_4x[1].setBackgroundResource(R.drawable.btn_default);
							if(start[1]==1){
								start[1]=0;
								flag=0;
							}
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[1])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[1].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[1].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[1].setBackgroundResource(R.drawable.btn_stop);
							button_4x[1].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[1])))
						{
							button_4x[1].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[1].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[1]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[1])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[1]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}
		}
	};

	private OnClickListener btn_43 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[2]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {


						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[2].setText(point[which]);
								button_4x[2].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[2]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[2].setText(point[which]);
							if(start[2]==1){
								start[2]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[2].setBackgroundResource(R.drawable.btn_stop);
							button_4x[2].setText(" ");
							if(start[2]==1){
								start[2]=0;
								flag=0;
							}
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[2]=count_goal;
							count_goal++;
							button_4x[2].setBackgroundResource(R.drawable.btn_end);
							if(start[2]==1){
								start[2]=0;
								flag=0;
							}
						}
						if(point[which].equals("節點"))
						{
							button_4x[2].setBackgroundResource(R.drawable.btn_default);
							if(start[2]==1){
								start[2]=0;
								flag=0;
							}
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[2])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[2].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[2].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[2].setBackgroundResource(R.drawable.btn_stop);
							button_4x[2].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[2])))
						{
							button_4x[2].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[2].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[2]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[2])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[2]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}
		}
	};

	private OnClickListener btn_44 = new OnClickListener()
	{
		public void onClick(View v)
		{
			if(state[3]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[3].setText(point[which]);
								button_4x[3].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[2]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[3].setText(point[which]);
							if(start[3]==1){
								start[3]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[3].setBackgroundResource(R.drawable.btn_stop);
							button_4x[3].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[3]=count_goal;
							count_goal++;
							button_4x[3].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[3].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[3])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[3].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[3].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[3].setBackgroundResource(R.drawable.btn_stop);
							button_4x[3].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[3])))
						{
							button_4x[3].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[3].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[3]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[3])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[3]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}
		}
	};

	private OnClickListener btn_45 = new OnClickListener()
	{
		public void onClick(View v)
		{
			if(state[4]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[4].setText(point[which]);
								button_4x[4].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[4]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[4].setText(point[which]);
							if(start[4]==1){
								start[4]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[4].setBackgroundResource(R.drawable.btn_stop);
							button_4x[4].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[4]=count_goal;
							count_goal++;
							button_4x[4].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[4].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[4])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[4].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[4].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[4].setBackgroundResource(R.drawable.btn_stop);
							button_4x[4].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[4])))
						{
							button_4x[4].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[4].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[4]))==false)
						{

							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[4])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[4]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
		}

	};

	private OnClickListener btn_46 = new OnClickListener()
	{

		//@Override
		public void onClick(View v)
		{
			if(state[5]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[5].setText(point[which]);
								button_4x[5].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[5]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[5].setText(point[which]);
							if(start[5]==1){
								start[5]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[5].setBackgroundResource(R.drawable.btn_stop);
							button_4x[5].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[5]=count_goal;
							count_goal++;
							button_4x[5].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[5].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[5])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[5].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[5].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[5].setBackgroundResource(R.drawable.btn_stop);
							button_4x[5].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[5])))
						{
							button_4x[5].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[5].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[5]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[5])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[5]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}
		}
	};

	private OnClickListener btn_47 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[6]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[6].setText(point[which]);
								button_4x[6].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[6]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[6].setText(point[which]);
							if(start[6]==1){
								start[6]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[6].setBackgroundResource(R.drawable.btn_stop);
							button_4x[6].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[6]=count_goal;
							count_goal++;
							button_4x[6].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[6].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[6])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[6].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[6].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[6].setBackgroundResource(R.drawable.btn_stop);
							button_4x[6].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[6])))
						{
							button_4x[6].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[6].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[6]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[6])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[6]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_48 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[7]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[7].setText(point[which]);
								button_4x[7].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[7]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[7].setText(point[which]);
							if(start[7]==1){
								start[7]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[7].setBackgroundResource(R.drawable.btn_stop);
							button_4x[7].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[7]=count_goal;
							count_goal++;
							button_4x[7].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[7].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[7])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[7].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[7].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[7].setBackgroundResource(R.drawable.btn_stop);
							button_4x[7].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[7])))
						{
							button_4x[7].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[7].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[7]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[7])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[7]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_49 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[8]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[8].setText(point[which]);
								button_4x[8].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[8]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[8].setText(point[which]);
							if(start[8]==1){
								start[8]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[8].setBackgroundResource(R.drawable.btn_stop);
							button_4x[8].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[8]=count_goal;
							count_goal++;
							button_4x[8].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[8].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[8])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[8].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[8].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[8].setBackgroundResource(R.drawable.btn_stop);
							button_4x[8].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[8])))
						{
							button_4x[8].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[8].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[8]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[8])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[8]=0;

						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_410 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[9]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[9].setText(point[which]);
								button_4x[9].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[9]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[9].setText(point[which]);
							if(start[9]==1){
								start[9]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[9].setBackgroundResource(R.drawable.btn_stop);
							button_4x[9].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[9]=count_goal;
							count_goal++;
							button_4x[9].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[9].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[9])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[9].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[9].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[9].setBackgroundResource(R.drawable.btn_stop);
							button_4x[9].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[9])))
						{
							button_4x[9].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[9].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[9]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[9])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[9]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_411 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[10]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[10].setText(point[which]);
								button_4x[10].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[10]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[10].setText(point[which]);
							if(start[10]==1){
								start[10]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[10].setBackgroundResource(R.drawable.btn_stop);
							button_4x[10].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[10]=count_goal;
							count_goal++;
							button_4x[10].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[10].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[10])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[10].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[10].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[10].setBackgroundResource(R.drawable.btn_stop);
							button_4x[10].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[10])))
						{
							button_4x[10].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[10].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[10]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[10])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[10]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_412 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[11]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[11].setText(point[which]);
								button_4x[11].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[11]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[11].setText(point[which]);
							if(start[11]==1){
								start[11]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[11].setBackgroundResource(R.drawable.btn_stop);
							button_4x[11].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[11]=count_goal;
							count_goal++;
							button_4x[11].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[11].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[11])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							button_4x[11].setText(point[which]);
							button_4x[11].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[11].setBackgroundResource(R.drawable.btn_stop);
							button_4x[11].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[11])))
						{
							button_4x[11].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[11].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[11]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[11])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
						}
						state[11]=0;
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_413 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[12]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[12].setText(point[which]);
								button_4x[12].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[12]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[12].setText(point[which]);
							if(start[12]==1){
								start[12]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[12].setBackgroundResource(R.drawable.btn_stop);
							button_4x[12].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[12]=count_goal;
							count_goal++;
							button_4x[12].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[12].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[12])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[12].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[12].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[12].setBackgroundResource(R.drawable.btn_stop);
							button_4x[12].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[12])))
						{
							button_4x[12].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[12].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[12]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[12])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[12]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_414 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[13]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[13].setText(point[which]);
								button_4x[13].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[13]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[13].setText(point[which]);
							if(start[13]==1){
								start[13]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[13].setBackgroundResource(R.drawable.btn_stop);
							button_4x[13].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[13]=count_goal;
							count_goal++;
							button_4x[13].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[13].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[13])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[13].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[13].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[13].setBackgroundResource(R.drawable.btn_stop);
							button_4x[13].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[13])))
						{
							button_4x[13].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[13].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[13]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[13])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[13]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_415 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[14]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[14].setText(point[which]);
								button_4x[14].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[14]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[14].setText(point[which]);
							if(start[14]==1){
								start[14]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[14].setBackgroundResource(R.drawable.btn_stop);
							button_4x[14].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[14]=count_goal;
							count_goal++;
							button_4x[14].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[14].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[14])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[14].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[14].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[14].setBackgroundResource(R.drawable.btn_stop);
							button_4x[14].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[14])))
						{
							button_4x[14].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[14].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[14]))==false)
						{
							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[14])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[14]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};

	private OnClickListener btn_416 = new OnClickListener()
	{
		//@Override
		public void onClick(View v)
		{
			if(state[15]==0)
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(count_goal)};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {

						if(point[which].equals("起點"))
						{
							if(flag==0)
							{
								button_4x[15].setText(point[which]);
								button_4x[15].setBackgroundResource(R.drawable.btn_begin);
								flag=1;
								start[15]=1;
							}
							else if(flag==1)
							{
								new AlertDialog.Builder(Map_4.this)
										.setTitle("錯誤")
										.setIcon(R.drawable.ic_launcher)
										.setMessage("起點已經設置!")
										.setPositiveButton("確定", new DialogInterface.OnClickListener() {

											@Override
											public void onClick(DialogInterface dialog, int which) {

											}
										})
										.show();
							}
						}
						else{
							button_4x[15].setText(point[which]);
							if(start[15]==1){
								start[15]=0;
								flag=0;
							}
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[15].setBackgroundResource(R.drawable.btn_stop);
							button_4x[15].setText(" ");
						}
						if(point[which].equals(("目地點")+Integer.toString(count_goal)))
						{
							state[15]=count_goal;
							count_goal++;
							button_4x[15].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[15].setBackgroundResource(R.drawable.btn_default);
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();

			}
			else
			{
				final String[] point = {"節點","起點","障礙點","目地點"+Integer.toString(state[15])};

				AlertDialog.Builder dialog = new AlertDialog.Builder(Map_4.this);
				dialog.setTitle("你選的是");
				dialog.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialoginterface, int i){}
						});
				dialog.setSingleChoiceItems(point, a,new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						button_4x[15].setText(point[which]);

						if(point[which].equals("起點"))
						{
							button_4x[15].setBackgroundResource(R.drawable.btn_begin);
							count_goal--;
						}
						if(point[which].equals("障礙點"))
						{
							button_4x[15].setBackgroundResource(R.drawable.btn_stop);
							button_4x[15].setText(" ");
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[15])))
						{
							button_4x[15].setBackgroundResource(R.drawable.btn_end);
						}
						if(point[which].equals("節點"))
						{
							button_4x[15].setBackgroundResource(R.drawable.btn_default);
							count_goal--;
						}
						if(point[which].equals(("目地點")+Integer.toString(state[15]))==false)
						{

							for(int i=0 ; i<16 ; i++)
							{
								if(state[i]!=0 && state[i]>state[15])
								{
									state[i]-=1;
									button_4x[i].setText("目標點"+state[i]);
								}
							}
							state[15]=0;
						}
						a = which;
						dialog.dismiss();
					}
				}).create().show();
			}}
	};
	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	//getMenuInflater().inflate(R.menu.main, menu);
	//return true;
	//}

}
