package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class AutoControl extends Activity {
	  int SERVER_PORT;
	  String  ServerIP;
	  PrintWriter out = null;
	  int n;
	  String  mes=null;
	  byte buff[]=new byte[20];
	  BufferedReader networkIn = null;
	  Map_4_m Map4m;

	  public String RobotTBack(){
		  Thread cThread = new Thread(new TBack());
		  cThread.run();
		  return mes;
	  }

	  public String RobotGo(){
		  Thread cThread = new Thread(new Go());
		  cThread.run();
		  return mes;
	  }

	  public String RobotTLeft(){
		  Thread cThread = new Thread(new TLeft());
		  cThread.run();
		  return mes;
	  }

	  public String RobotTRight(){
		  Thread cThread = new Thread(new TRight());
		  cThread.run();
		  return mes;
	  }


	 public class Go implements Runnable{
		 public void run() {
			 try {
				 Socket socket = new Socket(ServerIP, SERVER_PORT);

				 OutputStream out = socket.getOutputStream();

				 out.write('g');

				 InputStream in = socket.getInputStream();
				 int n = in.read(buff);
				 mes = new String(buff, 0, n);
				 socket.close();
			 } catch (Exception e) {

			 }
		 }
	}

	 public class TBack implements Runnable{
		 public void run(){

			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);

				OutputStream out = socket.getOutputStream();

				out.write('a');

				InputStream in = socket.getInputStream();
				n=in.read(buff);
				mes=new String(buff,0,n);

				socket.close();
			}catch(Exception e){

			}
		}
	}

	 public class TLeft implements Runnable{
		 public void run(){

			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);

				OutputStream out = socket.getOutputStream();

				out.write('e');

				InputStream in = socket.getInputStream();
				int n=in.read(buff);
				mes=new String(buff,0,n);

				socket.close();
			}catch(Exception e){

			}
		}
	}


	 public class TRight implements Runnable{
		 public void run(){

			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);

				OutputStream out = socket.getOutputStream();

				out.write('i');

				InputStream in = socket.getInputStream();
				int n=in.read(buff);
				mes=new String(buff,0,n);

				socket.close();
			}catch(Exception e){

			}
		}
	}



}