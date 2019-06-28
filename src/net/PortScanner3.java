package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
 
/**
 * 扫描主机上1到1024的端口，判断这些端口是否被服务器监听
 *Socket构造
 *public Socket() 通过系统默认类型的 SocketImpl 创建未连接套接字 
 *public Socket(String host,int port) throws UnknownHostException,IOException  创建一个流套接字并将其连接到指定主机上的指定端口号。
 *public Socket(String host,int port,InetAddress localAddr,int localPort) throws IOException 创建一个套接字并将其连接到指定远程主机上的指定远程端口。socket 会通过调用 bind() 函数来绑定提供的本地地址及端口。
 *public Socket(InetAddress address,int port)throws IOException创建一个流套接字并将其连接到指定 IP 地址的指定端口号。
 *public Socket(InetAddress address,int port,InetAddress localAddr,int localPort)throws IOException创建一个套接字并将其连接到指定远程地址上的指定远程端口。
 *							socket 会通过调用 bind() 函数来绑定提供的本地地址及端口。
 *如何连接成功则返回Socket对象，如果连接失败就会抛出
 * @author hanfeng
 * @data 2012-8-24 上午10:25:57
 */
public class PortScanner3 {
 
    public static void main(String[] args) throws Exception {
    	communicate(); 
        //new PortScanner().scan(host);
    }
    //在本机（112.5.4.3）构建区域网内112.5.4.45远程的服务端
    public void test() throws IOException{
    	InetAddress remoteAddr = InetAddress.getByName("112.5.4.45");
        InetAddress localAddr = InetAddress.getByName("112.5.4.3");
        Socket socket = new Socket(remoteAddr,8000,localAddr,2345);
    }
	private static void KuhuDuan() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
        System.out.println("本机IP地址："+address);
        InetAddress address2 = InetAddress.getByName("222.34.5.7");
        System.out.println("返回远程IP地址："+address2);
        InetAddress address3 = InetAddress.getByName("www.google.com");
        System.out.println("返回域名IP地址："+address3);
	}
 
    public void scan(String host) {
        Socket socket = null;
 
        for (int port = 8080; port < 8089; port++) {
            try {
                socket = new Socket(host, port);
                System.out.println("连接到端口：" + port);
            } catch (IOException e) {
                System.out.println("无法连接到端口：" + port);
            } finally {
                try {
                    if (socket != null) {//39.3  866480303910001
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //设置等待连接超时时间
    public void scan2(String host) {
        Socket socket = null;      
        socket = new Socket();
        SocketAddress address = new InetSocketAddress(host, 1158);
        try {
            socket.connect(address, 60000);
            System.out.println("连接成功！");
        } catch (IOException e) {
            System.out.println("连接超时！");
            e.printStackTrace();
        }      
    }
    /**
     * 各种异常
     * @param host
     * @param port
     */
    public void connect(String host,int port){
        SocketAddress address = new InetSocketAddress(host, port);
        Socket socket = null;
        
        String result = "";
         
        try {
        	long begin = System.currentTimeMillis();//计算开始连接的时间
        	socket = new Socket();//开始建立连接     
        	socket.connect(address, 6000);//设置连接超时时间
        	long end = System.currentTimeMillis();// 计算机连接结束的时间
        	result = (end-begin)+"ms";
        } catch (BindException e) {
            result = "IP地址或端口绑定异常！";
        } catch (UnknownHostException e) {
            result = "未识别主机地址！";
        }catch (SocketTimeoutException e) {
            result = "连接超时！";
        }catch (ConnectException e) {
            result = "拒绝连接！";
        }catch (Exception e) {
            result =  "失败啦！";
        }finally{
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("远程地址信息==>"+address+":"+result);
    }
    @SuppressWarnings("resource")
	public static void communicate() throws Exception{
        StringBuffer sb=new StringBuffer("GET "+" HTTP/1.1\r\n");
      sb.append("Host: www.javathinker.org\r\n");
      sb.append("Accept: */*\r\n");
      sb.append("Accept-Language: zh-cn\r\n");
      sb.append("Accept-Encoding: gzip, deflate\r\n");
      sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\r\n");
      sb.append("Connection: Keep-Alive\r\n\r\n");
        String host ="www.google.com";
        int port = 80;
        Socket socket=null;
       try{
    	   //socket = new Socket(host, port);
    	   socket = new Socket();
    	   SocketAddress address = new InetSocketAddress(host, port);
    	   socket.connect(address, 2000);//设置连接超时时间
    	   //发出HTTP请求
           OutputStream socketOut = socket.getOutputStream();
           socketOut.write(sb.toString().getBytes());
           socket.shutdownOutput();
            
           //接收响应结果
           InputStream socketInput = socket.getInputStream();
           BufferedReader buffer = new BufferedReader(new InputStreamReader(socketInput));
           String data = null;
           while ((data=buffer.readLine())!=null) {
               System.out.println(data);
           }
       }catch (SocketTimeoutException e) {
           System.out.println("超时");
        }catch (BindException e) {
        	System.out.println( "IP地址或端口绑定异常！");
        } catch (UnknownHostException e) {
        	System.out.println( "未识别主机地址！");
        }catch (ConnectException e) { 
        	System.out.println("拒绝连接！");
        }catch (Exception e) {
        	System.out.println("失败啦！");
        }finally {
            try {
                if(socket!=null)socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
    }
}
/**
 * Socket类提供了3个状态测试方法
 * isClosed()：如果Socket已经连接到远程主机，并且还没有关闭，则返回true

isConnected()：如果Socket曾经连接到远程主机，则返回true

isBound()：如果Socket已经与一个本地端口绑定，则返回true

如果要判断一个Socket对象当前是否处于连接状态，可以采用以下方式：

boolean isConnected = socket.isConnected()&&!socket.isClosed();

4.1 有的时候，可能仅仅希望关闭输出流或输入流之一。此时可以采用Socket类提供的半关闭方法：

shutdownInput()：关闭输入流。

shutdownOutput(): 关闭输出流。

4.2 先后调用Socket的shutdownInput()和shutdownOutput()方法，仅仅关闭了输入流和输出流，并不等价于调用Socket的close()方法。在通信结束后，仍然要调用Socket的close()方法，因为只有该方法才会释放Socket占用的资源，比如占用的本地端口等。

4.3 Socket类还提供了两个状态测试方法，用来判断输入流和输出流是否关闭：

public boolean isInputShutdown()

public boolean isOutputShutdown()

Socket有以下几个选项：

n TCP_NODELAY：表示立即发送数据。

n SO_RESUSEADDR：表示是否允许重用Socket所绑定的本地地址。

n SO_TIMEOUT：表示接收数据时的等待超时时间。

n SO_LINGER：表示当执行Socket的close()方法时，是否立即关闭底层的Socket。

n SO_SNFBUF：表示发送数据的缓冲区的大小。

n SO_RCVBUF：表示接收数据的缓冲区的大小。

n SO_KEEPALIVE：表示对于长时间处于空闲状态的Socket，是否要自动把它关闭。

n OOBINLINE：表示是否支持发送一个字节的TCP紧急数据。


1. TCP_NODELAY选项 

1) 设置该选项：public void setTcpNoDelay(boolean on) throws SocketException

2) 读取该选项：public boolean getTcpNoDelay() throws SocketException

3) TCP_NODEALY的默认值为false，表示采用Negale算法。如果调用setTcpNoDelay(true)方法，就会关闭Socket的缓冲，确保数据及时发送：

if(!socket.getTcpNoDelay()) socket.setTcpNoDelay(true);

4) 如果Socket的底层实现不支持TCP_NODELAY选项，那么getTcpNoDelay()和setTcpNoDelay()方法会抛出SocketException。


 */

