package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class userClient {
	public static void main(String[] args) throws IOException {
		//检测内网/外网
	}
	/**
	 * 客户端主动给服务端发生信息
	 * @throws IOException
	 */
	private static void zhudSendToService() throws IOException {
		Socket socket = NetClient1.socket;
		if(NetClient1.socket!=null) {
			OutputStream os = socket.getOutputStream();//字节输出流
			PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
			pw.write("我是客户端1的外来人员"); 
			pw.flush(); 
			socket.shutdownOutput(); 
			
			//3、获取输入流，并读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info=br.readLine())!=null){
			 System.out.println("我是客户端1，服务器说："+info);
			}

			//4、关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
		}
	}
	/**
	 * 服务端主动给客户端发送信息
	 * @throws IOException 
	 */
	public static void serviceToClient(int i) throws IOException {
		Socket socket = StudyNet1.map.get("socket0");
		if(!socket.isClosed()) {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎您！"+i);
			pw.flush();
			pw.close(); 
		}else {
			System.err.println("我错了");
		}
	}
}
