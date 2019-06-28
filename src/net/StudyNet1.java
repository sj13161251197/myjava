package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * 参考文档https://www.cnblogs.com/rocomp/p/4790340.html
 * 计算机文档：http://blog.51cto.com/wangdy/1590243
 * @author Administrator
 * 1、两台计算机间进行通讯需要以下三个条件：
            IP地址、协议、端口号
   2、TCP/IP协议：
        是目前世界上应用最为广泛的协议，是以TCP和IP为基础的不同层次上多个协议的集合，也成TCP/IP协议族、或TCP/IP协议栈
        TCP：Transmission Control Protocol 传输控制协议
        IP：Internet Protocol 互联网协议
   3、TCP/IP五层模型
        应用层：HTTP、FTP、SMTP、Telnet等
        传输层：TCP/IP
        网络层：
        数据链路层：
        物理层：网线、双绞线、网卡等
   4、IP地址
        为实现网络中不同计算机之间的通信，每台计算机都必须有一个唯一的标识---IP地址。
        32位二进制
   5、端口
        区分一台主机的多个不同应用程序，端口号范围为0-65535，其中0-1023位为系统保留。
        如：HTTP：80  FTP：21 Telnet：23
        IP地址+端口号组成了所谓的Socket，Socket是网络上运行的程序之间双向通信链路的终结点，是TCP和UDP的基础
   6、Socket套接字：
        网络上具有唯一标识的IP地址和端口组合在一起才能构成唯一能识别的标识符套接字。
      Socket原理机制：
         通信的两端都有Socket
         网络通信其实就是Socket间的通信
         数据在两个Socket间通过IO传输      
   7、Java中的网络支持
         针对网络通信的不同层次，Java提供了不同的API，其提供的网络功能有四大类：
          InetAddress:用于标识网络上的硬件资源，主要是IP地址
          URL：统一资源定位符，通过URL可以直接读取或写入网络上的数据
          Sockets：使用TCP协议实现的网络通信Socket相关的类
          Datagram:使用UDP协议，将数据保存在用户数据报中，通过网络进行通信。
 *
 */
public class StudyNet1 {
	public static Map<String ,Socket> map=new HashMap<String,Socket>();
	public static void main(String[] args) throws IOException, InterruptedException {
		StudyNet1 studyNet1 = new StudyNet1();
		studyNet1.duoThread();
		//UDPfuwuDuan();
		
		//studyNet1.TCPFwuDuan();
	}
	/**
	 * 四、TCP编程
	 *   1、两台计算机间进行通讯需要以下三个条件：IP地址、协议、端口号
         1、TCP协议是面向连接的、可靠的、有序的、以字节流的方式发送数据，通过三次握手方式建立连接，形成传输数据的通道，
         		在连接中进行大量数据的传输，效率会稍低
         2、Java中基于TCP协议实现网络通信的类
            客户端的Socket类
            服务器端的ServerSocket类
            3、Socket通信的步骤
                 ① 创建ServerSocket和Socket
                 ② 打开连接到Socket的输入/输出流
                 ③ 按照协议对Socket进行读/写操作
                 ④ 关闭输入输出流、关闭Socket
            4、服务器端：
                 ① 创建ServerSocket对象，绑定监听端口
                 ② 通过accept()方法监听客户端请求
                 ③ 连接建立后，通过输入流读取客户端发送的请求信息
                 ④ 通过输出流向客户端发送乡音信息
                 ⑤ 关闭相关资源
            
	 * @throws IOException
	 */
	private static void TCPFwuDuan() throws IOException {
		/**
		 * 基于TCP协议的Socket通信，实现用户登录，服务端
		 */
		//1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket =new ServerSocket(10086);//1024-65535的某个端口
		//2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		
		//3、获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();  
		
		InputStreamReader isr =new InputStreamReader(is);
		
		BufferedReader br =new BufferedReader(isr);
		
		String info =null;
		while((info=br.readLine())!=null){
		System.out.println("我是服务器，客户端说："+info);
		}
		socket.shutdownInput();//关闭输入流 
			//2、是否关闭输出流和输入流：对于同一个socket，如果关闭了输出流，则与该输出流关联的socket也会被关闭，所以一般不用关闭流，直接关闭socket即可
			//3、使用TCP通信传输对象，IO中序列化部分
	     	//4、socket编程传递文件，IO流部分
		//4、获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		
		PrintWriter pw = new PrintWriter(os);
		
		pw.write("欢迎您！");
		
		pw.flush();
		


		//5、关闭资源
		pw.close();
		
		os.close(); 
		
		br.close();
		
		isr.close();
		
		is.close();
		
		socket.close();
		
		serverSocket.close();
		
	}
	/**
	 * 多线程管理
	 * 应用多线程实现服务器与多客户端之间的通信
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void duoThread() throws IOException, InterruptedException{
		//服务器代码
		ServerSocket serverSocket =new ServerSocket(10086);
		Socket socket =null;
		int count =0;//记录客户端的数量
		while(true){
			socket = serverSocket.accept(); 
			map.put("socket"+count, socket);
			System.out.println("socket"+count);
			ServerThread serverThread =new ServerThread(socket);
			serverThread.start();
			count++;
		}
	}
	
	
	/**
	 * UDP协议（用户数据报协议）是无连接的、不可靠的、无序的,速度快
       进行数据传输时，首先将要传输的数据定义成数据报（Datagram），大小限制在64k，在数据报中指明数据索要达到的Socket（主机地址和端口号），然后再将数据报发送出去
       DatagramPacket类:表示数据报包
       DatagramSocket类：进行端到端通信的类
       1、服务器端实现步骤
           ① 创建DatagramSocket，指定端口号
           ② 创建DatagramPacket
           ③ 接受客户端发送的数据信息
           ④ 读取数据
           
       2、客户端实现步骤

           ① 定义发送信息
           ② 创建DatagramPacket，包含将要发送的信息
           ③ 创建DatagramSocket
           ④ 发送数据
	 * @throws IOException 
	 * 
	 * @throws UnknownHostException
	 */
	
	public static void UDPfuwuDuan() throws IOException{
		//服务器端，实现基于UDP的用户登录
		//1、创建服务器端DatagramSocket，指定端口
		DatagramSocket socket =new DatagramSocket(10010);
		//2、创建数据报，用于接受客户端发送的数据
		byte[] data =new byte[1024];//
		DatagramPacket packet =new DatagramPacket(data,data.length);
		//3、接受客户端发送的数据
		socket.receive(packet);//此方法在接受数据报之前会一致阻塞
		//4、读取数据
		String info =new String(data,0,data.length);
		System.out.println("我是服务器，客户端告诉我"+info);


		//=========================================================
		//向客户端响应数据
		//1、定义客户端的地址、端口号、数据
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		byte[] data2 = "欢迎您！".getBytes();
		//2、创建数据报，包含响应的数据信息
		DatagramPacket packet2 = new DatagramPacket(data2,data2.length,address,port);
		//3、响应客户端
		socket.send(packet2);
		//4、关闭资源
		socket.close();
	}
	
	
	
	
	
	private static void AddressIp() throws UnknownHostException {
		//获取本机的InetAddress实例
		InetAddress address =InetAddress.getLocalHost();
		String hostName = address.getHostName();//获取计算机名
		String hostAddress = address.getHostAddress();//获取IP地址
		byte[] bytes = address.getAddress();//获取字节数组形式的IP地址,以点分隔的四部分

		//获取其他主机的InetAddress实例InetAddress类用于标识网络上的硬件资源，标识互联网协议(IP)地址。 
		InetAddress address2 =InetAddress.getByName("techfujun-PC");
		InetAddress address3 =InetAddress.getByName("192.168.3.5");	
		System.out.println(hostName+"/"+hostAddress); 
		System.out.println(address3.getHostName()+"/"+address2.getHostAddress());
	}
	/**
	 * URL(Uniform Resource Locator)统一资源定位符，表示Internet上某一资源的地址，协议名：资源名称     
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private static void URL() throws MalformedURLException, IOException, UnsupportedEncodingException {
		//URL类:代表一个统一资源定位符，它是指向互联网“资源”的指针
		URL url1 = new URL("http://lib.csdn.net/article/javase/2112");
		//URLConnection类:用于读取和写入此 URL 引用的资源。
		 URLConnection openConnection = url1.openConnection();
		 openConnection.connect();
		 BufferedReader in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "utf-8"));
		 StringBuffer tStringBuffer = new StringBuffer();  
			String sTempOneLine = new String("");  
			while ((sTempOneLine = in.readLine()) != null){  
				tStringBuffer.append(sTempOneLine);  
				System.out.println(sTempOneLine);
			} 
			in.close();
			
			//创建一个URL的实例
			URL baidu =new URL("http://www.baidu.com");
			URL url =new URL(baidu,"/index.html?username=tom#test");//？表示参数，#表示锚点
			String protocol = url.getProtocol();//获取协议
			String host = url.getHost();//获取主机
			int port = url.getPort();//如果没有指定端口号，根据协议不同使用默认端口。此时getPort()方法的返回值为 -1
			String path = url.getPath();//获取文件路径
			String file = url.getFile();//文件名，包括文件路径+参数
			String ref = url.getRef();//相对路径，就是锚点，即#号后面的内容
			String query = url.getQuery();//查询字符串，即参数
	}
}
