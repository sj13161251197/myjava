package ffmpeg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 测试
 * @author eguid
 * @since jdk1.7
 * @version 2017年10月13日
 */
public class Test {
	/**
	 * 命令组装器测试
	 * @throws InterruptedException
	 */
	public static void test1() throws InterruptedException{
		FFmpegManagerImpl manager = new FFmpegManagerImpl();
		Map<String,String> map = new HashMap<String,String>();
		map.put("appName", "test123");
		map.put("input", "rtsp://admin:admin@192.168.2.236:37779/cam/realmonitor?channel=1&subtype=0");
		map.put("output", "rtmp://192.168.30.21/live/");
		map.put("codec", "h264");
		map.put("fmt", "flv");
		map.put("fps", "25");
		map.put("rs", "640x360");
		map.put("twoPart", "2");
		// 执行任务，id就是appName，如果执行失败返回为null
		String id = manager.start(map);
		System.out.println(id);
		// 通过id查询
		TaskEntity info = manager.query(id);
		System.out.println(info);
		// 查询全部
		Collection<TaskEntity> infoList = manager.queryAll();
		System.out.println(infoList);
		Thread.sleep(30000);
		// 停止id对应的任务
		 manager.stop(id);
	}
	/**
	 * 默认方式，rtsp->rtmp转流单个命令测试
	 * @throws InterruptedException
	 */
	public static void test2() throws InterruptedException{
		FFmpegManagerImpl manager = new FFmpegManagerImpl();
		// -rtsp_transport tcp 
		//测试多个任何同时执行和停止情况
		//默认方式发布任务
		//manager.start("tomcat", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat");
		//manager.start("tomcat", "ffmpeg -i rtsp://admin:tech123456@192.168.1.65:554/h264/ch01/main/av_av_stream -f flv -r 25 –s 640*640 –an rtmp://127.0.0.1:1935/live/test");
		String start = manager.start("tomcat","ffmpeg -i rtsp://test:tech1234@192.168.3.182:554/Streaming/Channels/101?transportmode=unicast -vcodec copy -acodec copy -f flv rtmp://127.0.0.1:1935/live/camera182");
		//String start = manager.start("tomcat","ffmpeg -i rtsp://admin:12345678ab@192.168.10.217:554/Streaming/Channels/101?transportmode=unicast -vcodec copy -acodec copy -f flv rtmp://127.0.0.1:1935/live/test");
		//String start1 = manager.start("tomcat","ffmpeg -i rtsp://admin:tech123456@192.168.1.99:554/Streaming/Channels/101?transportmode=unicast -vcodec copy -acodec copy -f flv rtmp://127.0.0.1:1935/live/test");
		//System.out.println(start);
		//System.out.println(start1);
		//manager.start("tomcat","ffmpeg -i rtsp://admin:tech123456@192.168.1.99:554/Streaming/Channels/101?transportmode=unicast -vcodec copy -acodec copy -f flv rtmp://127.0.0.1:1935/live/test");
		Thread.sleep(30000);
		// 停止全部任务 
		manager.stopAll();
	}
	/**
	 * 完整ffmpeg路径测试
	 * @throws InterruptedException
	 */
	public static void test4() throws InterruptedException{
		FFmpegManagerImpl manager = new FFmpegManagerImpl();
		// -rtsp_transport tcp 
		//测试多个任何同时执行和停止情况
		//默认方式发布任务
		manager.start("tomcat", "D:/TestWorkspaces/FFmpegCommandHandler/src/cc/eguid/FFmpegCommandManager/ffmpeg/ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat",true);
		
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	
	/**
	 * rtsp-rtmp转流多任务测试
	 * @throws InterruptedException
	 */
	public static void test3() throws InterruptedException{
		FFmpegManagerImpl manager = new FFmpegManagerImpl();
		// -rtsp_transport tcp 
		//测试多个任何同时执行和停止情况
		//false表示使用配置文件中的ffmpeg路径，true表示本条命令已经包含ffmpeg所在的完整路径
		manager.start("tomcat", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat",false);
		manager.start("tomcat1", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat1",false);
		manager.start("tomcat2", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat2",false);
		manager.start("tomcat3", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat3",false);
		manager.start("tomcat4", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat4",false);
		manager.start("tomcat5", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat5",false);
		manager.start("tomcat6", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat6",false);
		manager.start("tomcat7", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat7",false);
		manager.start("tomcat8", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat8",false);
		manager.start("tomcat9", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat9",false);
		
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	
	public static boolean pingTest(String ipAddress) {  
       
            try {
				return  InetAddress.getByName(ipAddress).isReachable(3000);
			} catch (java.net.UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;  
            
       
    } 
	
	public static boolean ping(String ipAddress, int pingTimes, int timeOut) {    
        BufferedReader in = null;    
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令    
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;    
        try {   // 执行命令并获取输出    
            System.out.println(pingCommand);     
            Process p = r.exec(pingCommand);     
            if (p == null) {      
                return false;     
            }  
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数    
            int connectedCount = 0;     
            String line = null;     
            while ((line = in.readLine()) != null) {      
                connectedCount += getCheckResult(line);     
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真    
            return connectedCount == pingTimes;    
        } catch (Exception ex) {     
            ex.printStackTrace();   // 出现异常则返回假    
            return false;    
        } finally {     
            try {      
                in.close();     
            } catch (IOException e) {      
                e.printStackTrace();     
            }    
        }  
    } 
	//若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.  
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);    
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);    
        Matcher matcher = pattern.matcher(line);    
        while (matcher.find()) {  
            return 1;  
        }  
        return 0;   
    } 
	public static void main(String[] args) throws InterruptedException {
	//	test1();
		test2();
		//test3();
		//test4();
		//pingTest("192.168.1.65");
		//System.out.println("19820805".substring(4,6));
	}
}
