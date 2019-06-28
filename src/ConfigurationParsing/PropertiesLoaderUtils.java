package ConfigurationParsing;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * java Properties配置文件的加载解析---springframework包提供
 * @author Administrator
 *
 */
public class PropertiesLoaderUtils {
	private static Properties props;
	
	private PropertiesLoaderUtils() {
		getProperties();
	}
	public static Properties getProperties() {
		if(props==null) {
			props=new Properties();
		}
		return props;
	}
	public static void main(String[] args) {
		PropertiesLoaderUtils pu = new PropertiesLoaderUtils();
		pu.loadFile();
		pu.writeProperties();
		
	}
	/**
	 * 这里默认加载classpath配置文件
	 * 关于路径的写法：（可以相对路径也可以是绝对路径）
	 * Class.getResourceAsStream(String path) 
	 * path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath（src文件）根下获取
	 * 
	 * 加载配置文件  配置文件至于classpath中
	  */
	public void loadFile() {
		/*InputStream resourceAsStream2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("filename");
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("filename");
		props.load(resourceAsStream);*/
		InputStream inStream=getClass().getResourceAsStream("config.properties");//获取配置文件输入流
        try {
        	props.load(inStream);//载入输入流
            Enumeration enumeration=props.propertyNames();//取得配置文件里所有的key值
            while(enumeration.hasMoreElements()){
                String key=(String) enumeration.nextElement();
                //在配置文件中汉字或解析的数字编码，在这里正常打印
                System.out.println("配置文件里的key值："+key+"=====>配置文件里的value值："+props.getProperty(key));//输出key值
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * @throws IOException 
	 * 以项目中路径加载
	 */
	public void loadFile(String fileUrl) throws IOException {
		/*方式一
		InputStream in = new BufferedInputStream(new FileInputStream("文件路径名"));
		Properties p = new Properties();
		p.load(in);
		System.out.println(p.getProperty("version"));
		//方式二
		InputStream ins = PropertiesUtil.class.getResourceAsStream("文件路径名");
		Properties ps = new Properties();
		ps.load(ins);
		System.out.println(ps.getProperty("version"));
		//方式三
		InputStream inss = PropertiesUtil.class.getClassLoader().getResourceAsStream("文件名");
		Properties pss = new Properties();
		pss.load(inss);
		System.out.println(pss.getProperty("version"));
		//方式四
		InputStream insss = ClassLoader.getSystemResourceAsStream("文件名");
		Properties psss = new Properties();
		psss.load(insss);
		System.out.println(pss.getProperty("version"));*/

		
        InputStream in = new BufferedInputStream(new FileInputStream(fileUrl));
        props.load(in);
        Enumeration enumeration=props.propertyNames();//取得配置文件里所有的key值
        while(enumeration.hasMoreElements()){
            String key=(String) enumeration.nextElement();
            System.out.println("配置文件里的key值："+key+"=====>配置文件里的value值："+props.getProperty(key));//输出key值
        }
	}
	/**
	 * 
	 * @throws IOException 
	 * 以盘符路径加载
	 */
	public void loadPanFile(String fileUrl) throws IOException {
		InputStream in=getClass().getResourceAsStream(fileUrl);//获取配置文件输入流
        props.load(in);
	}
	/**
	 * 使用PropertiesLoaderUtils工具类（springframework包提供）
	 */
	/*private static void springUtil(){  
	    Properties props = new Properties();  
	    while(true){  
	        try {  
	            props=PropertiesLoaderUtils.loadAllProperties("param.properties");  
	            for(Object key:props.keySet()){  
	                System.out.print(key+":");  
	                System.out.println(props.get(key));  
	            }  
	        } catch (IOException e) {  
	            System.out.println(e.getMessage());  
	        }  
	          
	        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}  
	    }  
	}*/ 
	/**
	 * 修改配置文件  这里的写需要先读取在写，直接写会吧没有写的给覆盖
	 * 另外写的时候如果写入元文件，编译文件是不会发生变化的
	 */
	public void writeProperties(){ 
       // Properties properties=new Properties();
        try { 
        	File file = new File("src/ConfigurationParsing/config.properties");
        	System.err.println(file.getAbsolutePath());
           //这个路径是项目根路径
        	//OutputStream outputStream=new FileOutputStream("config.properties");
        	
        	OutputStream outputStream=new FileOutputStream("src/ConfigurationParsing/config.properties");
            props.setProperty("number", "2015");
            props.setProperty("song", "手写的从前"); 
           
            props.store(outputStream, "comments");
          
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
