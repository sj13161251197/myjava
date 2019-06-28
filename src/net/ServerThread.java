package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
 
public class ServerThread extends Thread{
	//服务器线程处理
	//和本线程相关的socket
	Socket socket =null;
	InputStream is;
	
	
	OutputStream os;
	String infoStr;
	//
	public ServerThread(Socket socket){
		this.socket = socket;
	}

	public void run(){
		//服务器处理代码
		//3、获取输入流，并读取客户端信息
		
		try {
			is = socket.getInputStream();
			boolean ioToDate = ioToDate(is);//处理输入流
			if(ioToDate) {
				toClient();//给客户端响应
			}
			//socket.shutdownInput();//关闭输入流 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	/**
	 * 服务端本身对数据的处理
	 * @param ios
	 * @throws IOException
	 */
	public boolean ioToDate(InputStream ios) throws IOException{
		boolean flag=false;
		try {
			if(ios!=null){
				/*byte[] flush=new byte[ios.available()];
				int read = ios.read(flush);
				System.out.println(read+"/"+flush.toString());*/
				
				InputStreamReader isr =new InputStreamReader(ios);
				BufferedReader br =new BufferedReader(isr);
				String info =null;
				while((info=br.readLine())!=null){
					infoStr=info;
					System.out.println("我是服务器，客户端说："+info);
				}
				socket.shutdownInput();//关闭输入流 
				flag=true;
				//br.close();
				//isr.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			//socket.shutdownInput();//关闭输入流 
		}
		
		return flag;
	}
	/**
	 * 服务端对客户端的响应
	 * @return 
	 * @throws IOException 
	 */
	public void toClient() throws IOException {
		os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("欢迎您！"+infoStr);
		pw.flush();
		pw.close();
		for(int i=0;i<3;i++) { 
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(socket.isClosed());
			socket.setKeepAlive(true);
			System.out.println(socket.isClosed());
			//userClient.serviceToClient(i);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("欢迎您！"+i);
			pw.flush();
			pw.close();
			
			//pw.close(); 
			
		}
		
	}
	public void closeIo() {
		try {
			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}