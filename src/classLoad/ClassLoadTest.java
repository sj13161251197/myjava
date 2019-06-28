package classLoad;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类加载器
 * @author Administrator
 *BootStrap 		加载jre   lib   里边的rt.jar包的类
 *ExtClassLoader   	加载jre lib/ext/*.jar   主要为扩展包里边的类
 *AppClassLoader	加载classPath下边的类
 *
 *
 *加载机制：当前线程的类加载器加载线程中的第一个类
 *如果类a引用了类b  则默认用a的加载器加载类b
 *还可以直接指定加载器：ClassLoader.loadClass(类);
 *用一个加载器加载一个类的时候是先交给父加载器加载  找不到在自己找  在找不到抛出异常
 *
 */
public class ClassLoadTest { 
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(ClassLoadTest.class.getClassLoader().getClass().getName());
		System.out.println(System.class.getClassLoader());//有BootStrap  系统加载器加载
		//java提供了三个加载器  负责加载不同位置的类  加载类也是java类  Boot是虚拟机内置的类不是java类
		List<ClassLoader> a=new ArrayList<ClassLoader>();
		//当前类的加载类是sun.misc.Launcher$AppClassLoader
		 ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
		 if(classLoader!=null){
			 a.add(classLoader);
			 ClassLoader parent = classLoader.getParent();
			 if(parent!=null){
				 a.add(parent);
				 ClassLoader zparent = parent.getParent();
				 if(zparent!=null){
					 a.add(zparent);
				}
			 }
		 }
		 for(ClassLoader l:a){
			 System.out.println(l.getClass().getName()); 
		 }
		//Web类加载器顺序
		//WebAppClassLoader-StandardClassLoader-AppClassLoader-ExtClassLoader
		
		
		ClassLoader classLoader1 = ClassLoadTest.class.getClassLoader();
		while(classLoader1!=null){
			System.out.println(classLoader1.getClass().getName());
			classLoader1=classLoader1.getParent();
		}
		//假设类路径为srcLei 
		//Class class1 = new MyClassLoadTest("D:\\eclWock\\java\\bin\\classLoad").loadClass("ClassLoadTest");
		//Object newInstance = class1.newInstance();
		//newInstance.toString();
		classTest classTest1 = new classTest("a","c");
		classTest classTest2 = new classTest("b","d");
		System.out.println(classTest1.a);
		System.out.println(classTest2.a);
		System.out.println(classTest1.b);
		System.out.println(classTest2.b);
	}
	/**
	 * 类加载的委托机制  loadClass默认先找上级要，要不到在找findClass()要  然后由defindClass吧class文件字节文件转换为字节码对应的class类
	 */
}
/**
 * 自定义类加载器  
 * 必须继承ClassLoader
 * 覆盖findClass
 * 如果重写loadClass  则会是委托机制改写
 * 模板方法设计模式:类加载机制的findClass 和 ClassLoader 自己的流程findClass   统一的流程ClassLoader
 * 对class转换为二进制字节码
 */
class MyClassLoadTest extends ClassLoader{
	private String classDir="";//编译目录
	
	/**
	 * 设置编译目录
	 */
	public MyClassLoadTest() {
		// TODO Auto-generated constructor stub
	}
	public MyClassLoadTest(String classDir) {
		// TODO Auto-generated constructor stub
		this.classDir=classDir;
	}
	public Class<?> findClass(String name) throws ClassNotFoundException{
		//对class转换为二进制字节码
		//
		//byte[] b=loadClassDate(name);
		//return defineClass(name, b, 0, b.length);
		
		//String[] args={"D:\\eclWock\\java\\bin\\classLoad","D:\\eclWock\\java\\bin\\classLoad\\lib"};
		try {
			String fileUrl=classDir+"\\"+name+".class";
			FileInputStream ins = new FileInputStream(fileUrl);//相对路径
			FileOutputStream outs = new FileOutputStream(fileUrl);//相对路径
			MyClassLoadTest.cypher(ins,outs);
			ins.close();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			outs.close();
			byte[] byteArray = byteArrayOutputStream.toByteArray();
			return defineClass(byteArray,0,byteArray.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	public void test(String[] args) throws IOException{
		FileInputStream ins = new FileInputStream(args[0]);//相对路径
		FileOutputStream outs = new FileOutputStream(args[1]);//相对路径
		MyClassLoadTest.cypher(ins,outs);
		ins.close();
		outs.close();
	}
	private byte[] loadClassDate(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 加密方法
	 * @return 
	 */
	public static void cypher(InputStream ins,OutputStream outs){
		int b=-1;
		try {
			while((b=ins.read())!=-1){//类加密  异或方式加密
				outs.write(b^ 0xff);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
/**
 * 作为一个测试类  用自定义的类加载器MyClassLoadTest  先加密  在加载
 * @author Administrator
 *设置runconfig  的参数 和目标地址
 *类的普通成员变量的生命周期和类的实例生命周期一样
 *类的静态变量又叫类变量不会随着实例的销毁而销毁
 */
class classTest extends Date{
	public static String a;
	public  String b;
	public classTest(String a,String b){
		this.a=a;
		this.b=b;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println("测试");
		return super.toString();
	}
	
	
}

