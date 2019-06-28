package net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientIp1 {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("192.168.1.93",10087);
		//2、获取输出流，向服务器端发送信息 
		OutputStream os = socket.getOutputStream();//字节输出流
		PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
		pw.write("我是客户端93"); 
		pw.flush(); 
		socket.shutdownOutput(); 
	}
}
