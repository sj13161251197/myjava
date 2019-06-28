package bat;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Mian extends JFrame implements ActionListener{
	JPanel jp1,jp2,jp3,jp4,jp5=null;   
	JButton jb1,jb2,jb3,jb4,jb5=null; 
	JLabel jlb1,jlb2,jlb3,jlb4,jlb5=null;
    JTextField url1,url2,url3,url4,url5=null;
    String zkpUrl,kafkaUrl,tcpUrl,tswUrl=null;
    JTextField dos1,dos2,dos3,dos4=null;
    String zkpDos,kafkaDos,tcpDos,tswDos=null;
     
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//根据配置设置默认值
		//四个目录输入框
		//四个命令输入框
		//四个按钮
		//一个桌面
		Mian man=new Mian();
	}
	public  Mian() {
		
		
		jp1=new JPanel();  
		 	jlb1=new JLabel("启动zookeep："); 
		 	url1=new JTextField(30);
		 	//url1.setText(zkpUrl);//设置默认值
		 	
		 	dos1=new JTextField(30);
		 	//dos1.setText(zkpDos);//设置默认值
		 	
		 	jp1.add(jlb1);  
		    jp1.add(url1);  
		    jp1.add(dos1); 
		    jb1 = new JButton("启动zkp");
		 	jb1.addActionListener(this); 
		    jp1.add(jb1);
		jp2=new JPanel();  
		 	jlb2=new JLabel("启动kafka："); 
		 	url2=new JTextField(30);
		 	//url2.setText(kafkaUrl);//设置默认值
		 	dos2=new JTextField(30);
		 	//dos2.setText(kafkaDos);//设置默认值
		 	
		 	jp2.add(jlb2);  
		 	jp2.add(url2);  
		 	jp2.add(dos2); 
		 	jb2 = new JButton("启动kafka");
		 	jb2.addActionListener(this);  
		 	jp2.add(jb2);
		 	
		jp3=new JPanel();  
		 	jlb3=new JLabel("启动TCP"); 
		 	url3=new JTextField(30);
		 	//url3.setText(tcpUrl);//设置默认值
		 	dos3=new JTextField(30);
		 	//dos3.setText(tcpDos);//设置默认值
		 	
		 	jp3.add(jlb3);  
		 	jp3.add(url3);  
		 	jp3.add(dos3); 
		 	jb3 = new JButton("启动TCP");
		 	jb3.addActionListener(this); 
		    jp3.add(jb3);
		 jp4=new JPanel();  
		 	jlb4=new JLabel("启动管控"); 
		 	url4=new JTextField(30);
		 	//url4.setText(tswUrl);//设置默认值
		 	dos4=new JTextField(30);
		 	//dos4.setText(tswDos);//设置默认值
		 	
		 	jp4.add(jlb4);  
		 	jp4.add(url4);  
		 	jp4.add(dos4); 
		 	jb4 = new JButton("启动TSW");
		 	jb4.addActionListener(this); 
		    jp4.add(jb4);
		 jp5=new JPanel();  
		 jlb5=new JLabel("选择properties格式的文件"); 
		 url5=new JTextField(30);
		 jb5 = new JButton("选择配置文件");
		 jp5.add(jlb5);
		 jp5.add(jb5);
		 jp5.add(jb5); 
		 jb5.addActionListener(this); 
	    this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        this.add(jp4); 
        this.add(jp5); 
        this.setLayout(new GridLayout(4,1));            //选择GridLayout布局管理器        
        this.setTitle("配置文件启动");          
        this.setSize(800,600);          
        this.setLocation(100, 100);           
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
     /*   this.setResizable(true);  
       try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
        initDate();
    }
	public void initDate(){
		try {	
			//File file = new File("D:\\eclWock\\ZhuoMian\\url_cmd.properties");
			//System.out.println(file.getAbsolutePath());
			//InputStream inStream=new FileInputStream(file);
			//InputStream inStream=getClass().getResourceAsStream("D:\\eclWock\\ZhuoMian\\url_cmd.properties");//获取配置文件输入流
			
			InputStream inStream=getClass().getResourceAsStream("url_cmd.properties");//获取配置文件输入流
			Properties props=new Properties();
			props.load(inStream);//载入输入流
			
			/* zkpUrl = props.getProperty("zkpUrl");
			 kafkaUrl = props.getProperty("kafkaUrl");
			 tcpUrl = props.getProperty("tcpUrl");
			 tswUrl = props.getProperty("tswUrl");
			 zkpDos = props.getProperty("zkpDos");
			 kafkaDos = props.getProperty("kafkaDos");
			 tcpDos = props.getProperty("tcpDos");
			 tswDos = props.getProperty("tswDos");*/
			dos1.setText(props.getProperty("zkpDos"));//设置默认值
			dos2.setText(props.getProperty("kafkaDos"));//设置默认值
			dos3.setText(props.getProperty("tcpDos"));//设置默认值
			dos4.setText(props.getProperty("tswDos"));//设置默认值
			
			url1.setText(props.getProperty("zkpUrl"));//设置默认值
			url2.setText(props.getProperty("kafkaUrl"));//设置默认值
			url3.setText(props.getProperty("tcpUrl"));//设置默认值
			url4.setText(props.getProperty("tswUrl"));//设置默认值
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void initDateByUrl(){
		try {
			JFileChooser fileChooser = new JFileChooser("D:\\");
		 	
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = fileChooser.showOpenDialog(fileChooser);
		 	if(returnVal == JFileChooser.APPROVE_OPTION) {
		 		String filePath= fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径
		 		System.out.println(filePath);
		 		if(filePath.endsWith(".properties")) {
		 			File file = new File(filePath);
			 		InputStream inStream=new FileInputStream(file);
			 		Properties props=new Properties();
					props.load(inStream);//载入输入流
					
					url5.setText(filePath);//设置默认值
					dos1.setText(props.getProperty("zkpDos"));//设置默认值
					dos2.setText(props.getProperty("kafkaDos"));//设置默认值
					dos3.setText(props.getProperty("tcpDos"));//设置默认值
					dos4.setText(props.getProperty("tswDos"));//设置默认值
					
					url1.setText(props.getProperty("zkpUrl"));//设置默认值
					url2.setText(props.getProperty("kafkaUrl"));//设置默认值
					url3.setText(props.getProperty("tcpUrl"));//设置默认值
					url4.setText(props.getProperty("tswUrl"));//设置默认值
		 		}else {
		 			JOptionPane.showMessageDialog(null,"配置文件格式错误！","配置文件错误",JOptionPane.WARNING_MESSAGE);  
		 		}
		 		
			
		 	}
			
			 
		}catch (Exception e) {
			// TODO: handle exception
            JOptionPane.showMessageDialog(null,"缺少关键值！","配置文件错误",JOptionPane.WARNING_MESSAGE);  
			System.err.println("****************************");
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {            //事件判断
		
        if(e.getActionCommand().equals("启动zkp"))   {
        	String url=url1.getText();
        	String dos=dos1.getText();
        	startCmd(url,dos);
        }else if(e.getActionCommand().equals("启动kafka")) {  
        	String url=url2.getText();
        	String dos=dos2.getText();
        	startCmd(url,dos);
        }else if(e.getActionCommand().equals("启动TCP")) {   
        	String url=url3.getText();
        	String dos=dos3.getText();
        	startCmd(url,dos);
    	}else if(e.getActionCommand().equals("启动TSW")) {    
    		String url=url4.getText();
        	String dos=dos4.getText();
        	startCmd(url,dos);
    	}else if(e.getActionCommand().equals("选择配置文件")) {
    		initDateByUrl();
    		
    	}

    }  

	public void startCmd(String url,String cmd) {
		//获取输入框的值
		//获取命令框的值
		//启动cmd 执行命令
		//url="D:/zookeep_kafka/zookeeper-3.4.8/bin";
		System.out.println(url);  
		File dir = new File(url);
		
        // String command="netstat -an";
        String command = cmd; 
        Runtime r = Runtime.getRuntime();
        System.out.println(url+"\\"+command+"bat"); 
        try {   
        	r.exec("C:/Windows/System32/cmd.exe /k start"+url+"\\"+command+"bat");
        	//test2();
        	//Process p = r.exec(command,null,dir);
			//Process p = r.exec("1_1_1.bat", null, new File("G:/bat文件编写学习"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		
	}
	public void test1() throws IOException {
        // 直接打开应用程序
        Runtime.getRuntime().exec("C:/Users/liqiang/Desktop/开机后点它.bat"); // 打开一个批处理文件
        Runtime.getRuntime().exec("E:/酷狗/KGMusic/KuGou.exe"); // 打开酷狗

        /******** 可以通过cmd命令打开软件或者是做其他 *****/
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start E:/酷狗/KGMusic/KuGou.exe"); // 通过cmd窗口执行命令
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k start E:/php/Test/第一个html/界面.html"); // 通过cmd命令打开一个网页
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k mkdir C:\\Users\\liqiang\\Desktop\\java键的1"); // 通过cmd创建目录用两个反斜杠
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k mkdir C:\\Users\\liqiang\\Desktop\\java键的2"); // 通过cmd创建目录用两个反斜杠
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c calc ");// 通过cmd打开计算器
    }

    public void test2() throws IOException {
        /******** 可以通过cmd命令打开软件或者是做其他 *****/
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c osk");// 通过屏幕软键盘
    }
}
