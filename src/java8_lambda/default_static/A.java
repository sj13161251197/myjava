package java8_lambda.default_static;
/**
 * 
 * @author Administrator
 *
 */
interface A{
	//接口中可以定义变量，默认是public static final，必须初始化，可以加final，static，public修饰
	final int i=1;
	void test();
	default void printA(){
		System.out.println("A"+i);
	}
	//默认方法不可以重名
	default void printA(int a) {
	}
   default void printC(){
	   System.out.println("C");
	   //默认方法中可以访问静态方法或者默认方法
	   printB();
	   printA();
   }
   //default，static，abstract三个关键字中不能同时有两个关键字修饰一个方法
//   default static void printD(){
//	   System.out.println("C");
//   }
/**
 * 接口中static方法只能访问static方法，不能访问default方法，
 * 而default方法可以访问static方法和default方法，
 * 调用方式跟普通类中调用方法一样。
 * 另外一个方法只能被static、default、abstract关键字修饰，
 * 因为从语义上讲，static方法就是一种静态的default方法，
 * 是一种已经实现的方法，与abstract定义的抽象方法冲突。

 */
	static void printB(){
		//static方法中不能访问default方法，只能访问静态方法
//		printC()
		System.out.println("静态方法A");
	}
	static void printD(){
	    printB();
	}
}
