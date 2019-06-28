package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetClient3 extends Thread{
	Socket socket =null;
	public NetClient3(Socket socket) {
		this.socket=socket;
	}
	public void run(){
		while(socket!=null) {
			try {
				InputStream is = socket.getInputStream();
				if(is!=null) {
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String info = null;
					while((info=br.readLine())!=null){
						System.out.println("我是客户端1，服务器说："+info);
					}
					//br.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
