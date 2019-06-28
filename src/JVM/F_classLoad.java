package JVM;

import java.lang.reflect.Method;

public class F_classLoad {
	public static void main(String args[]) {
		//参数 -Xbootclasspath/a:D:/tmp/clz  指定加载的类地址
		HelloLoader loader = new HelloLoader();
		loader.print();
		/*ClassLoader cl=F_classLoad.class.getClassLoader();
		byte[] bHelloLoader=loadClassBytes("geym.jvm.ch6.findorder.HelloLoader");
		Method md_defineClass=ClassLoader.class.getDeclaredMethod("defineClass", byte[].class,int.class,int.class);
		md_defineClass.setAccessible(true);
		md_defineClass.invoke(cl, bHelloLoader,0,bHelloLoader.length);
		md_defineClass.setAccessible(false);
		
		HelloLoader loader = new HelloLoader();
		System.out.println(loader.getClass().getClassLoader());
		loader.print();
*/
	}
}
class HelloLoader {
	public void print() {
		
		
		System.out.println(HelloLoader.class.getClassLoader().toString());
	}

}
