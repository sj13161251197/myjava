package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
/**
 * 这里注意输出控制台的操作
 * @author Administrator
 *
 */
public class NetClient1  {
	static Socket socket;
	public static void main(String[] args) throws IOException {
		TCPtest();
	}
	private static void TCPtest() throws IOException {
		//客户端
		//1、创建客户端Socket，指定服务器地址和端口
		socket =new Socket("127.0.0.1",10086);
		//2、获取输出流，向服务器端发送信息
		OutputStream os = socket.getOutputStream();//字节输出流
		PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
		pw.write("我是客户端1"); 
		pw.flush(); 
		socket.shutdownOutput(); 
		
		//3、获取输入流，并读取服务器端的响应信息
		/*InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info=br.readLine())!=null){
		 System.out.println("我是客户端1，服务器说："+info);
		}*/

		//4、关闭资源
		//br.close();
		//is.close();
		//pw.close();
		//os.close();
		//socket.close();
		if(!socket.isClosed()) {
			new NetClient3(socket).start();
		}else {
			System.err.println("****************************");
		}
		
	}
   public static void UDPclient() throws IOException{
	 //客户端
	 //1、定义服务器的地址、端口号、数据
	 InetAddress address =InetAddress.getByName("localhost");
	 int port =10010;
	 byte[] data ="用户名：admin;密码：123".getBytes();
	 //2、创建数据报，包含发送的数据信息
	 
	 DatagramPacket packet = new DatagramPacket(data,0,data.length,address,port);
	 //3、创建DatagramSocket对象
	 DatagramSocket socket =new DatagramSocket();
	 //4、向服务器发送数据
	 socket.send(packet);


	 //接受服务器端响应数据
	 //======================================
	 //1、创建数据报，用于接受服务器端响应数据
	 byte[] data2 = new byte[1024];
	 DatagramPacket packet2 = new DatagramPacket(data2,data2.length);
	 //2、接受服务器响应的数据
	 socket.receive(packet2);
	 String raply = new String(data2,0,packet2.getLength());
	 System.out.println("我是客户端，服务器说："+raply);
	 //4、关闭资源
	 socket.close();
   }
}
