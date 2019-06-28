import java.io.IOException;

public class Look {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String commandStr="ffmpeg -i rtsp://test:tech1234@192.168.3.182:554/Streaming/Channels/101";
		commandStr+= "?transportmode=unicast";
		commandStr+= "-vcodec copy -acodec copy -f flv "; 
		//commandStr+= "rtmp://192.168.1.117:1935 live/camera182";
;
		Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec(commandStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
