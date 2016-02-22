package com.example.demo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Map_4_m extends Activity{
	int[] state = new int[16] ,que = new int[16] ,block = new int[16];
	int count=1;
	int cnt = 0;
	int start = 0 , next = 0;
	String  obstacle , cango="H", notcango="U" ,test="",test1="";
	//int obstacle = 0;
	int old_dir = 0;

	static boolean change = false;
	char now = '↑';
	Map_4_m map4m;
	AutoControl robot;
	public int i,x,y;
	public static int num=0;
	public static float nowx=610 , nowy=1070 , nowrotate=0 ;
	public static int continuego=1;
	public static int threadnum=0;
	public int dir;
	public boolean time=true;
	private Handler handler= new Handler(){
		@SuppressLint("NewApi")
		public void handleMessage (Message msg) {
			super.handleMessage(msg);
			ImageView img = (ImageView) findViewById(R.id.car);
			ObjectAnimator an , an1;
			switch(msg.arg1)
			{
				case 1:
					an= ObjectAnimator.ofFloat(img,"rotation",nowrotate,nowrotate-90);
					nowrotate-=90;
					an.setDuration(2000);
					an.setInterpolator(new LinearInterpolator());
					an.start();
					break;
				case 2:
					an= ObjectAnimator.ofFloat(img,"rotation",nowrotate,nowrotate+90);
					nowrotate+=90;
					an.setDuration(2000);
					an.setInterpolator(new LinearInterpolator());
					an.start();
					break;
			}
			switch(msg.what)
			{
				case 1:
					nowy = img.getY();
					an1 = ObjectAnimator.ofFloat(img, "y", nowy, nowy - 180);
					an1.setDuration(2000);
					an1.setInterpolator(new LinearInterpolator());
					if(msg.arg1==1 || msg.arg1==2) {
						an1.setStartDelay(3000);
					}
					an1.start();
					break;
				case 2:
					nowy = img.getY();
					an1 = ObjectAnimator.ofFloat(img, "y", nowy, nowy + 180);
					an1.setDuration(2000);
					an1.setInterpolator(new LinearInterpolator());
					if(msg.arg1==1 || msg.arg1==2) {
						an1.setStartDelay(3000);
					}
					an1.start();
					break;
				case 3:
					/*an= ObjectAnimator.ofFloat(img,"rotation",nowrotate,nowrotate-90);
					nowrotate-=90;
					an.setDuration(2000);
					an.setInterpolator(new LinearInterpolator());
					an.start();*/
					nowx = img.getX();
					an1 = ObjectAnimator.ofFloat(img, "x", nowx, nowx - 180);
					an1.setDuration(2000);
					an1.setInterpolator(new LinearInterpolator());
					if(msg.arg1==1 || msg.arg1==2) {
						an1.setStartDelay(3000);
					}
					an1.start();
					break;
				case 4:
					/*an= ObjectAnimator.ofFloat(img,"rotation",nowrotate,nowrotate+90);
					nowrotate+=90;
					an.setDuration(2000);
					an.setInterpolator(new LinearInterpolator());
					an.start();*/
					nowx = img.getX();
					an1 = ObjectAnimator.ofFloat(img, "x", nowx, nowx + 180);
					an1.setDuration(2000);
					an1.setInterpolator(new LinearInterpolator());
					if(msg.arg1==1 || msg.arg1==2) {
						an1.setStartDelay(3000);
					}
					an1.start();
			}
			/*ImageView img = (ImageView) findViewById(R.id.car);
			nowy = img.getY();
			ObjectAnimator an = ObjectAnimator.ofFloat(img, "y", nowy, nowy - 180);
			an.setDuration(2000);
			an.setInterpolator(new LinearInterpolator());
			an.start();*/
		}
	};
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_4_m);

		robot = new AutoControl();

		Intent e = getIntent();
		final Bundle extras = e.getExtras();
		robot.ServerIP = extras.getString("ip");
		robot.SERVER_PORT = extras.getInt("port");


		Bundle gbundle = this.getIntent().getExtras();
		/*ImageView img = (ImageView) findViewById( R.id.car);
		nowy=img.getY();
		ObjectAnimator an= ObjectAnimator.ofFloat(img, "y", nowy, nowy + 100);
		an.setDuration(2000);
		an.setInterpolator(new LinearInterpolator());
		an.start();*/

		num=0;
		nowrotate=0;

		Button button_b = (Button)findViewById(R.id.back);
		button_b.setOnClickListener(back);
		final Button[] button_4x = new Button[16];
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
		Button btnMove = (Button)findViewById(R.id.btnMove);
		btnMove.setOnClickListener(moveListener);
		String[] data = new String[16];
		data[0] = gbundle.getString("String41");
		data[1] = gbundle.getString("String42");
		data[2] = gbundle.getString("String43");
		data[3] = gbundle.getString("String44");
		data[4] = gbundle.getString("String45");
		data[5] = gbundle.getString("String46");
		data[6] = gbundle.getString("String47");
		data[7] = gbundle.getString("String48");
		data[8] = gbundle.getString("String49");
		data[9] = gbundle.getString("String410");
		data[10] = gbundle.getString("String411");
		data[11] = gbundle.getString("String412");
		data[12] = gbundle.getString("String413");
		data[13] = gbundle.getString("String414");
		data[14] = gbundle.getString("String415");
		data[15] = gbundle.getString("String416");
		for(int i=0 ; i<16 ; i++)
		{
			que[i]=-1;
			block[i]=0;
		}

		for(int i=0;i<16;i++)
		{
			if(data[i].equals("節點"))
			{
				button_4x[i].setText(" ");
				button_4x[i].setBackgroundResource(R.drawable.pho_default);
			}
			else
			{
				button_4x[i].setText(data[i]);
				if(data[i].equals("起點"))
				{
					start=i;
					//button_4x[i].setBackgroundResource(R.drawable.car_1);
					button_4x[i].setText(" ");
					next = i;
					x=start/4;
					y=start%4;
					ImageView img = (ImageView) findViewById(R.id.car);
					ObjectAnimator anx = ObjectAnimator.ofFloat(img, "x", 610, 610 - 180*(3-y));
					anx.setDuration(2000);
					anx.setInterpolator(new LinearInterpolator());
					anx.start();
					ObjectAnimator any = ObjectAnimator.ofFloat(img, "y", 1070, 1070 - 180*(4-x));
					any.setDuration(2000);
					any.setInterpolator(new LinearInterpolator());
					any.start();
				}
				if(data[i].equals(" "))
				{
					button_4x[i].setBackgroundResource(R.drawable.stop);
					block[i]=1;
				}

				if(data[i].equals("目地點"+i))
				{
					button_4x[i].setBackgroundResource(R.drawable.pho_end);
				}
			}
		}

		String r="";
		state =gbundle.getIntArray("state");
		for(int j = 1 ; j < 16 ; j++){    //將目標點做排序
			for(int i = 0 ; i<16 ; i++){
				if(state[i]==j)
				{
					que[j]=i;
					r=r+Integer.toString(que[j]);
				}
			}
		}

	};
	private Button.OnClickListener Reset = new
			Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					cnt=0;
				}
			};
	private OnClickListener back = new OnClickListener()
	{
		public void onClick(View v)
		{
			Button button_b = (Button)findViewById(R.id.back);
			button_b.setBackgroundResource(R.drawable.pho_back_2);
			Intent e = getIntent();
			final Bundle extras = e.getExtras();
			String ip = extras.getString("ip");
			int port = extras.getInt("port");
			Intent intent = new Intent();

			intent.setClass(Map_4_m.this, Map_4.class);
			intent.putExtra("ip", ip);
			intent.putExtra("port", port);

			startActivity(intent);
			finish();
		}
	};


	public View.OnClickListener moveListener = new
			View.OnClickListener(){

				@SuppressLint("NewApi")
				@Override
				public void onClick(View v) {
					//final Thread cartest = new Thread(new car_go());
					//cartest.run();
					TextView txtShowPass = (TextView)findViewById(R.id.txtShowPass);
					String pass = "";
					String new_pass = "";
					String total_pass = "";
					String path = shortpass(start,que[1],block); //return path

					setTitle(start+"->"+que[1]);
					pass = "第"+count+"個目標點 移動路徑:";
					if(count==1){
						pass += path;
						total_pass +=path;
					}
					else
					{
						if(que[count]!=-1){
							pass += shortpass(que[count-1],que[count],block);
						}
					}

					//TextView map4 = (TextView)findViewById(R.id.map4_title);
					//map4.setText("PAPA");
					txtShowPass.setText(pass);
					count++;
					cnt++;

					final char [] con_path = pass.toCharArray();
					final int path_length = pass.length();

					new Thread( new Runnable() {
						@Override
						public void run() {
							for(int i=0;i<path_length+1;i=i+2) {
								if (now == '↑')
								{
									if (con_path[i] == '↑')
									{
										int dir = 1;
										//obstacle = robot.RobotGo();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 1;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next, dir);
										now = con_path[i];
									}
									else if (con_path[i] == '↓')
									{
										int dir = 2;
										//obstacle = robot.RobotTBack();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 2;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next, dir);
										now = con_path[i];
									}
									else if (con_path[i] == '←')
									{
										int dir = 3;
										//obstacle = robot.RobotTLeft();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 3;
												msg.arg1 = 1;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next, dir);
										now = con_path[i];
									}
									else if (con_path[i] == '→')
									{
										int dir = 4;
										//obstacle = robot.RobotTRight();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 4;
												msg.arg1 = 2;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next, dir);
										now = con_path[i];
									} else
									{continue;}
								}/////////////////////////////////////////////////////////
								else if(now == '↓')
								{
									if(con_path[i] == '↑')
									{
										int dir = 1;
										//obstacle = robot.RobotTBack();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 1;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '↓')
									{
										int dir = 2;
										//obstacle = robot.RobotGo();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 2;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '←')
									{
										int dir = 3;
										//obstacle = robot.RobotTRight();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 3;
												msg.arg1 = 2;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '→')
									{
										int dir = 4;
										//obstacle = robot.RobotTLeft();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 4;
												msg.arg1 = 1;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else
									{continue;}
								}
								/////////////////////////////////////////////////////////
								else if(now == '→')
								{
									if(con_path[i] == '↑')
									{
										int dir = 1;
										//obstacle = robot.RobotTLeft();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 1;
												msg.arg1 = 1;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}

									else if(con_path[i] == '↓')
									{
										int dir = 2;
										//obstacle = robot.RobotTRight();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 2;
												msg.arg1 = 2;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '←')
									{
										int dir = 3;
										//obstacle = robot.RobotTBack();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 3;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '→')
									{
										int dir = 4;
										//obstacle = robot.RobotGo();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 4;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else{continue;}
								}
								/////////////////////////////////////////////////////////
								else if(now == '←')
								{
									if(con_path[i] == '↑')
									{
										int dir = 1;
										//obstacle = robot.RobotTRight();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 1;
												msg.arg1 = 2;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '↓')
									{
										int dir = 2;
										//obstacle = robot.RobotTLeft();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													time=false;
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 2;
												msg.arg1 = 1;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '←')
									{
										int dir = 3;
										//obstacle = robot.RobotGo();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 3;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else if(con_path[i] == '→')
									{
										int dir = 4;
										//obstacle = robot.RobotTBack();
										new Thread(new Runnable() {
											@Override
											public void run() {
												try {
													if(!time)
													{
														num++;
														time=true;
													}
													num++;
													Thread.sleep(1000 + 3000 * (num - 1));
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
												Message msg = new Message();
												msg.what = 4;
												msg.arg1 = 0;
												handler.sendMessage(msg);
												//handler.sendMessage(handler.obtainMessage());
											}
										}).start();
										next = go_next(next,dir);
										now = con_path[i];
									}
									else
									{
										continue;
									}
								}
							}
						}
					}).start();
					/////////////////////////////////////////////////////////////////


					for(int i=0;i<path_length+1;i=i+2)
					{
						/*if(now == '↑')
						{
							if(con_path[i] == '↑')
							{
								int dir = 1;
								//obstacle = robot.RobotGo();
								new Thread( new Runnable() {
									@Override
									public void run() {
										try{
											num++;
											Thread.sleep(1000+3000*(num-1));
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										Message msg = new Message();
										msg.what = 1;
										handler.sendMessage(msg);
										//handler.sendMessage(handler.obtainMessage());
									}
								}).start();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if (con_path[i] == '↓')
							{
								int dir = 2;
								//obstacle = robot.RobotTBack();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '←')
							{
								int dir = 3;
								new Thread( new Runnable() {
									@Override
									public void run() {
										try{
											num++;
											Thread.sleep(1000+3000*(num-1));
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										Message msg = new Message();
										msg.what = 3;
										handler.sendMessage(msg);
										//handler.sendMessage(handler.obtainMessage());
									}
								}).start();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '→')
							{
								int dir = 4;
								//obstacle = robot.RobotTRight();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else
							{continue;}
						}
						/////////////////////////////////////////////////////////
						else if(now == '↓')
						{
							if(con_path[i] == '↑')
							{
								int dir = 1;
								//obstacle = robot.RobotTBack();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '↓')
							{
								int dir = 2;
								//obstacle = robot.RobotGo();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '←')
							{
								int dir = 3;
								//obstacle = robot.RobotTRight();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '→')
							{
								int dir = 4;
								//obstacle = robot.RobotTLeft();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else
							{continue;}
						}
						/////////////////////////////////////////////////////////
						else if(now == '→')
						{
							if(con_path[i] == '↑')
							{
								int dir = 1;
								//obstacle = robot.RobotTLeft();
								next = go_next(next,dir);
								now = con_path[i];
							}

							else if(con_path[i] == '↓')
							{
								int dir = 2;
								//obstacle = robot.RobotTRight();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '←')
							{
								int dir = 3;
								//obstacle = robot.RobotTBack();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '→')
							{
								int dir = 4;
								//obstacle = robot.RobotGo();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else{continue;}
						}
						/////////////////////////////////////////////////////////
						else if(now == '←')
						{
							if(con_path[i] == '↑')
							{
								int dir = 1;
								//obstacle = robot.RobotTRight();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '↓')
							{
								int dir = 2;
								//obstacle = robot.RobotTLeft();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '←')
							{
								int dir = 3;
								//obstacle = robot.RobotGo();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else if(con_path[i] == '→')
							{
								int dir = 4;
								//obstacle = robot.RobotTBack();
								next = go_next(next,dir);
								now = con_path[i];
							}
							else
							{
								continue;
							}
						}*/
						/////////////////////////////////////////////////////////

						test +=obstacle;
						/*if( obstacle.equals(notcango) )
						{
							obstacle=" ";
							block[next]=1; //重設障礙物
							if( old_dir == 1 )
							{
								start=next+4;
								next-=4;
							}
							else if( old_dir == 2 )
							{
								start=next-4;
								next+=4;
							}
							else if( old_dir == 3 )
							{
								start=next+1;
								next-=1;
							}
							else
							{
								start=next-1;
								next+=1;
							}
							//重設起點
							test1 +="b:"+next+"s:"+start;
							new_pass +=" ";

							i=-2;
							count=1;
							cnt=0;

							//path = shortpass(start,que[twice],block); //return path
							pass="";
							path = shortpass(start,que[1],block); //return path
							//setTitle(start+"->"+que[1]);
							//pass = "第"+count+"個目標點 移動路徑:";
							if(count==1){
								//pass += path;
								pass += path;
								total_pass +=" "+path;
							}

							txtShowPass.setText(total_pass);
							count++;
							cnt++;

							con_path = pass.toCharArray();
							path_length = pass.length();


						}*/
						/////////////////////////////////////////////////////////
					}
					//Toast mes = Toast.makeText(Map_4_m.this,ggyy, Toast.LENGTH_SHORT);
					//mes.show();
					TextView txt_checkset = (TextView)findViewById(R.id.txt_checkset);
					txt_checkset.setText(test1);
					TextView map4 = (TextView)findViewById(R.id.map4_title);
					//-map4.setText(test);

				}
			};
	private String shortpass(int start ,int goal,int[] block)  {
		int [] a=new int [100];
		int nx=0 , ny=0;
		int [] short_road=new int [100];
		int [] short_path=new int [100];
		int [][] cost = new int[4][4] , id=new int[4][4];
		int count1=0,que=0,que_pass=0;
		int map_size=4;   //專題預設
		int x=2,y=1,x1=2,y1=2,x2=2,y2=3,x3=4,y3=4;
		int x_g,y_g;   //終點
		int x_s=1,y_s=1;
		int x_cost=0;
		String path="",b="";

		char type[][] = new char[4][4];
		for(int i=0 ; i<100 ; i++)
			a[i]=0;

		x_s = start%map_size;
		y_s = start/map_size;
		x_g = goal%map_size;
		y_g = goal/map_size;
		for(int i=0;i<map_size;i++)
		{
			for(int j=0;j<map_size;j++)
			{
				type[i][j]='o';
				cost[i][j]=0;
				id[i][j] = i + j*map_size;
			}
		}
		for(int i=0 ; i<16; i++){
			if(block[i]==1){
				type[i%map_size][i/map_size]='x';
				b=b+Integer.toString(i)+" ";
			}
		}
		setTitle(b+"!");
		type[x_s][y_s]='s';
		type[x_g][y_g]='g';
		nx = x_s;
		ny = y_s;

		while(true)  //右上左下
		{


			if(nx+1<map_size && type[nx+1][ny]!='x' &&  cost[nx+1][ny]==0 && type[nx+1][ny]!='s')
			{
				que++;
				a[que] = id[nx+1][ny];
				cost[nx+1][ny] = cost[nx][ny]+1;
			}
			if(ny+1<map_size && type[nx][ny+1]!='x' && cost[nx][ny+1]==0 && type[nx][ny+1]!='s')
			{
				que++;
				a[que]=id[nx][ny+1];
				cost[nx][ny+1]=cost[nx][ny]+1;
			}
			if(nx-1>=0 && type[nx-1][ny]!='x' && cost[nx-1][ny]==0 &&  type[nx-1][ny]!='s')
			{
				que++;
				a[que]=id[nx-1][ny];
				cost[nx-1][ny]=cost[nx][ny]+1;
			}
			if(ny-1>=0 && type[nx][ny-1]!='x' && cost[nx][ny-1]==0 &&  type[nx][ny-1]!='s')
			{
				que++;
				a[que] = id[nx][ny-1];
				cost[nx][ny-1] = cost[nx][ny]+1;
			}

			if(count1==que){break;}

			count1++;
			nx = a[count1]%map_size;
			ny = a[count1]/map_size;
		}

		int way=0;
		if(cost[x_g][y_g]==0){way=1;}

		else{
			for( nx = x_g , ny = y_g; cost[nx][ny]>0 ; que_pass++ )
			{

				if(nx+1 < map_size && cost[nx][ny]-1==cost[nx+1][ny] && type[nx+1][ny]!='x')
				{
					short_road[que_pass]=(nx+1)+ny*map_size;
					short_path[que_pass]=4;
					nx=nx+1;
				}
				else if(ny+1 < map_size && cost[nx][ny]-1==cost[nx][ny+1] && type[nx][ny+1]!='x')
				{
					short_road[que_pass]=nx+(ny+1)*map_size;
					short_path[que_pass]=3;
					ny=ny+1;
				}
				else if(nx-1>=0 && cost[nx][ny]-1==cost[nx-1][ny] && type[nx-1][ny]!='x')
				{
					short_road[que_pass]=(nx-1)+ny*map_size;
					short_path[que_pass]=2;
					nx=nx-1;
				}
				else if(ny-1>=0 && cost[nx][ny]-1==cost[nx][ny-1] && type[nx][ny-1]!='x')
				{
					short_road[que_pass]=nx+(ny-1)*map_size;
					short_path[que_pass]=1;
					ny=ny-1;
				}
			}

		}
		if(way==1)
		{
			return "There has no short path";
		}//判斷是否有路到終點
		else
		{
			for(int n=que_pass-1 ; n>=0 ; n--)
			{
				if(n!=que_pass-1)
					path = path + " > ";

				if(short_path[n]==1)
					path=path+"↓";
				else if(short_path[n]==2)
					path=path+"→";
				else if(short_path[n]==3)
					path=path+"↑";
				else if(short_path[n]==4)
					path=path+"←";
			}
		}

		return path;
	}
	///////////////////////////////////////////////////////////////////////////////////////

	int go_next(int next,int dir)
	{
		/*old_dir = dir;
		final Button[] button = new Button[16];
		button[0] = (Button) findViewById(R.id.btn_41);
		button[1] = (Button) findViewById(R.id.btn_42);
		button[2] = (Button) findViewById(R.id.btn_43);
		button[3] = (Button) findViewById(R.id.btn_44);
		button[4] = (Button) findViewById(R.id.btn_45);
		button[5] = (Button) findViewById(R.id.btn_46);
		button[6] = (Button) findViewById(R.id.btn_47);
		button[7] = (Button) findViewById(R.id.btn_48);
		button[8] = (Button) findViewById(R.id.btn_49);
		button[9] = (Button) findViewById(R.id.btn_410);
		button[10] = (Button) findViewById(R.id.btn_411);
		button[11] = (Button) findViewById(R.id.btn_412);
		button[12] = (Button) findViewById(R.id.btn_413);
		button[13] = (Button) findViewById(R.id.btn_414);
		button[14] = (Button) findViewById(R.id.btn_415);
		button[15] = (Button) findViewById(R.id.btn_416);

		Bundle gbundle = this.getIntent().getExtras();
		String[] data = new String[16];
		data[0] = gbundle.getString("String41");
		data[1] = gbundle.getString("String42");
		data[2] = gbundle.getString("String43");
		data[3] = gbundle.getString("String44");
		data[4] = gbundle.getString("String45");
		data[5] = gbundle.getString("String46");
		data[6] = gbundle.getString("String47");
		data[7] = gbundle.getString("String48");
		data[8] = gbundle.getString("String49");
		data[9] = gbundle.getString("String410");
		data[10] = gbundle.getString("String411");
		data[11] = gbundle.getString("String412");
		data[12] = gbundle.getString("String413");
		data[13] = gbundle.getString("String414");
		data[14] = gbundle.getString("String415");
		data[15] = gbundle.getString("String416");

		button[next].setBackgroundResource(R.drawable.pho_default);

		if(data[next].equals(" "))
		{
			button[next].setBackgroundResource(R.drawable.pho_default);	//button[next].setText(" ");
		}
		else if(data[next].equals("起點"))
		{
			button[next].setBackgroundResource(R.drawable.pho_begin);
			button[next].setText("起點");
		}
		else
		{
			for(int x=1;x<17;x++)
			{
				if(data[next].equals("目地點"+x))
				{
					button[next].setBackgroundResource(R.drawable.pho_end);
					button[next].setText("目地點"+x);
				}
			}
		}*/


		if(dir==1)
		{
			next = next-4;
			//if(!obstacle.equals(notcango))
				//button[next].setBackgroundResource(R.drawable.car_1); //上
		}
		else if(dir==2)
		{
			next = next+4;
			//if(!obstacle.equals(notcango))
				//button[next].setBackgroundResource(R.drawable.car_2); //下
		}
		else if(dir==3)
		{
			next = next-1;
			//if(!obstacle.equals(notcango))
				//button[next].setBackgroundResource(R.drawable.car_3); //左
		}
		else
		{
			next = next+1;
			//if(!obstacle.equals(notcango))
				//button[next].setBackgroundResource(R.drawable.car_4); //右
		}

		//button[next].setText(" ");
		/*if(obstacle.equals(notcango))
		{
			button[next].setBackgroundResource(R.drawable.stop);;
		}*/

		try{}catch(Exception e){}

		return next;

	}

	/*public class car_go implements Runnable {
		@SuppressLint("NewApi")
		public void run() {
				continuego=0;
				ImageView img = (ImageView) findViewById(R.id.car);
				nowy = img.getY();
				ObjectAnimator an = ObjectAnimator.ofFloat(img, "y", nowy, nowy - 180);
				an.setDuration(2000);
				an.setInterpolator(new LinearInterpolator());
				an.start();
				continuego=1;
				//Thread.currentThread().sleep(2000);
			try {
				//Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public class car_back implements Runnable{
		@SuppressLint("NewApi")
		public void run(){
			while(continuego==0)
			{
				try {
					Thread.sleep(100);
				}
				catch(Exception e) {

				}
			}
			try{
				continuego=0;
				ImageView img = (ImageView) findViewById( R.id.car);
				nowy=img.getY();
				ObjectAnimator an= ObjectAnimator.ofFloat(img,"y",nowy,nowy+180);
				an.setDuration(2000);
				an.setInterpolator(new LinearInterpolator());
				continuego=1;
				Thread.sleep(2000);
			}
			catch(Exception e){

			}
		}
	}
	public class car_left implements Runnable{
		@SuppressLint("NewApi")
		public void run(){
				continuego=0;
				ImageView img = (ImageView) findViewById( R.id.car);
				nowx=img.getX();
				ObjectAnimator an1= ObjectAnimator.ofFloat(img,"x",nowx,nowx-180);
				an1.setDuration(2000);
				an1.setInterpolator(new LinearInterpolator());
				//an1.setStartDelay(2000);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public class car_right implements Runnable{
		@SuppressLint("NewApi")
		public void run(){
			try{
				ImageView img = (ImageView) findViewById( R.id.car);
				nowx=img.getX();
				ObjectAnimator an= ObjectAnimator.ofFloat(img,"x",nowx,nowx+180);
				an.setDuration(2000);
				an.setInterpolator(new LinearInterpolator());
				an.start();
				Thread.sleep(2000);
			}
			catch(Exception e){

			}
		}
	}*/








}
