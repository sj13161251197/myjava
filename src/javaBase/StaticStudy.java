package javaBase;
/**
 * static  不能修饰普通类
 * 内部类等价于成员变量  可以修饰内部类
 * @author Administrator
 *
 */
public class StaticStudy {
	public  String a="";
	
	public static void main(String[] args) {
		//static  修饰成员变量，方法，静态区域块
		//他们和方法一样都存放在静态模块中
		
		//而静态方法和静态变量之所以能够直接调用时因为  类在编译  即第一次加载时就别加载
		
		//System.out.println(Gouzao_kuai.b22);static不会影响访问修饰符修饰的范围
		System.out.println(Gouzao_kuai.c22);
	}
	public static void test(){
		//this.a=2;//是没有this的，因为它不依附于任何对象
		//static 不能修饰区部变量
		
	}
	//static 不能修饰区部变量
	//public  void test(static String a){}
	
}
