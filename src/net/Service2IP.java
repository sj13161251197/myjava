package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Service2IP {
	public static void main(String[] args) throws IOException {
		ServerSocket st=new ServerSocket(10087);
		Socket socket=null;
		while(true) {
			socket = st.accept();
			//3、获取输入流，并读取客户端信息
			InputStream is = socket.getInputStream();  
			
			InputStreamReader isr =new InputStreamReader(is);
			
			BufferedReader br =new BufferedReader(isr);
			String info =null;
			while((info=br.readLine())!=null){
				System.out.println("我是服务器，客户端说："+info);
			}
			isr.close();
			is.close();
			socket.close();
		}
		
		
	}
}
