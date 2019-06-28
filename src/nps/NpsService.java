package nps;
/**
 * nps服务端模拟
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import net.ServerThread;

public class NpsService {
	Map<String,Socket> socketMap=new HashMap<String,Socket>();
	public static void main(String[] args) {
		//启动服务
		NpsService ns=new NpsService();
		try {
			ns.startService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 一个80端口的服务监听客户端的推送
	 * @throws IOException 
	 */
	public void startService() throws IOException{
		ServerSocket serverSocket =new ServerSocket(80);
		Socket socket =null;
		int count =0;//记录客户端的数量
		while(true){
			socket = serverSocket.accept();
			socketMap.put(socket.getInetAddress().toString(), socket);
			ServerThread serverThread =new ServerThread(socket);
			serverThread.start();
			count++;
			System.err.println("客户端连接的数量："+count);
		}
	}
	/**
	 * 
	 */
	public void toCommon(){
		String nvsIp="192.168.10.2";
		int nvs=2;
		int not=3;
		int node=4;
		int card=5;
		int status=6;
		//先调用指令封装
		byte[] onoff = onoff(nvs,not,node,status);
		//发送数据
		sendDate(nvsIp,onoff);
	}
	/**
	 * 服务端主动发送指令
	 * @throws IOException 
	 */
	public void sendDate(String nvsIp,byte[] dateCommon){
		Socket socket = socketMap.get(nvsIp);
		if(socket!=null){
			try {
				OutputStream os;
				os = socket.getOutputStream();
				os.write(dateCommon);
				os.flush();
				//5、关闭资源
				os.close();
			} catch (IOException e) {
				System.out.println("错误");
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				socketMap.remove(nvsIp);
			}
		}else{
			System.out.println("错误");
		}
		
	}
	/**
	 * 指令封装开关
	 */
	public byte[] onoff(int nvs,int not,int node,int states){
		byte[] arr=new byte[1024];
		//设置数组其它位置的默认值
		
		arr[2]=(byte)nvs;
		arr[3]=(byte)not;
		arr[4]=(byte)node;
		arr[5]=(byte)states;
		
		return arr;
	}
	/**
	 * 指令封装获取状态
	 */
	public byte[] getstates(int nvs,int not,int node){
		byte[] arr=new byte[1024];
		//设置数组其它位置的默认值
		
		arr[2]=(byte)nvs;
		arr[3]=(byte)not;
		arr[4]=(byte)node;
		
		return arr;
	}
	/**
	 * 指令封装获取状态
	 */
	public byte[] setCard(int nvs,int not,int node,int card){
		byte[] arr=new byte[1024];
		//设置数组其它位置的默认值
		
		arr[2]=(byte)nvs;
		arr[3]=(byte)not;
		arr[4]=(byte)node;
		arr[5]=(byte)card;
		return arr;
	}
}
