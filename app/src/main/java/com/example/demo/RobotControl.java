package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class RobotControl extends Activity {
	  int SERVER_PORT;
	  String  ServerIP;
	  PrintWriter out = null;
	  BufferedReader networkIn = null;
	  public void RobotBW(){
		  Thread cThread = new Thread(new TCPBWClient());
		  cThread.run();
	  }

	  public void RobotFW(){
		  Thread cThread = new Thread(new TCPFWClient());
		  cThread.run();
	  }
	  
	  public void RobotLeft(){
		  Thread cThread = new Thread(new TCPLeftClient());
		  cThread.run();
	  }
	  
	  public void RobotRight(){
		  Thread cThread = new Thread(new TCPRightClient());
		  cThread.run();
	  }
	  
	  public void RobotStop(){
		  Thread cThread = new Thread(new TCPStopClient());
		  cThread.run();
	  }
	  public void RM(){
		  Thread cThread = new Thread(new TCPRMClient());
		  cThread.run();
	  }
	  public void LN(){
		  Thread cThread = new Thread(new TCPLNClient());
		  cThread.run();
	  }
	  /*public void RightFORWARD(){
		  Thread cThread = new Thread(new TCPRFClient());
		  cThread.run();
	  }
	  public void RightBACKWARD(){
		  Thread cThread = new Thread(new TCPRBClient());
		  cThread.run();
	  }*/
	  public void S1(){
		  Thread cThread = new Thread(new TCPS1Client());
		  cThread.run();
	  }
	  public void S2(){
		  Thread cThread = new Thread(new TCPS2Client());
		  cThread.run();
	  }
	  public void S3(){
		  Thread cThread = new Thread(new TCPS3Client());
		  cThread.run();
	  }
	  
	 
	 public class TCPFWClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();

				out.write('f');
				String  mes=null;
				byte buff[]=new byte[20];
				InputStream in = socket.getInputStream();
				int n=in.read(buff);
				mes=new String(buff,0,n);
				socket.close();
			}catch(Exception e){
					
			}
		}
	}

	 public class TCPBWClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				
				out.write('b');
				
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 
	 public class TCPLeftClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('l');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 
	 
	 public class TCPRightClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('r');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 
	 public class TCPStopClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('s');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	public class TCPRMClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('m');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 public class TCPLNClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('n');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	/* public class TCPRFClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('g');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 public class TCPRBClient implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('f');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}*/
	 public class TCPS1Client implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('1');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 public class TCPS2Client implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('2');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
	 public class TCPS3Client implements Runnable{
		 public void run(){				  
			
			try{
				Socket socket = new Socket(ServerIP, SERVER_PORT);
				
				OutputStream out = socket.getOutputStream();
				
				out.write('3');
				
				socket.close();
			}catch(Exception e){
					
			}
		}
	}
}