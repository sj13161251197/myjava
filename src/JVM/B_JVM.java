package JVM;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * runStatic：演示方法栈溢出
 * add：演示jvm怎么进行堆栈运算的
 * 内存的泄露：在栈上时分配一个堆对象出栈时没有回收这个堆对象   多次运行到一定阶段就会导致内存的泄露
 * 			如果把这个对象声明在栈上  则函数调用完成自动清
 * 
 * test3堆溢出(无结果)
 * test4堆溢出
 * @author Administrator
 *
 */
public class B_JVM {
	public static void main(String[] args) {
		//runStatic(5);
		//test4();
		test5();
	}

	/**
	 * 演示方法栈溢出 原理递归调用 每一次调用都产生一个帧，并压栈，递归不退出则一直压栈直到溢出
	 * 
	 * @param i
	 * @return
	 */
	public static int runStatic(int i) {
		return runStatic(i);
	}
	/**
	 * Java没有寄存器，所有参数传递使用操作数栈
	0:   iconst_0 // 0压栈
 	1:   istore_2 // 弹出int，存放于局部变量2
 	2:   iload_0  // 把局部变量0压栈
 	3:   iload_1 // 局部变量1压栈
 	4:   iadd      //弹出2个变量，求和，结果压栈
 	5:   istore_2 //弹出结果，放于局部变量2
 	6:   iload_2  //局部变量2压栈
 	7:   ireturn   //返回

	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int add(int a, int b) {
		int c = 0;
		c = a + b;
		return c;
	}
	public static void alloc(){
        byte[] b=new byte[1024];
        b[0]=1;
    }
	/**
	 * 演示内存泄露。堆空间不足
	 * 
	在栈上分配
	-server -Xmx10m -Xms10m
	-XX:+DoEscapeAnalysis -XX:+PrintGC
	在栈上分配的要求与好处:
	
	小对象（一般几十个bytes），在没有逃逸的情况下，可以直接分配在栈上
	直接分配在栈上，可以自动回收，减轻GC压力
	大对象或者逃逸对象无法栈上分配


	在堆上分配
	-server -Xmx10m -Xms10m  
	-XX:-DoEscapeAnalysis -XX:+PrintGC

	 * @param args
	 */
	public static void test3(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc(); 
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
		
    }
	/**
	 * 堆溢出
	 */
	public static void test4() {
		 ArrayList list=new ArrayList();

	        while(true){

	            list.add(new B_JVM());

	        }
	}
	/**
	 * 永久区内存溢出
	 */
	public static void test5() {
		//for(int i=0;i<100000;i++){
		   // CglibBean bean = new CglibBean("geym.jvm.ch3.perm.bean"+i,new HashMap());
		//}
		C c = new C();

	}


}
class C{
	long[] a=new long[200*1024*1024];
	long[] b=new long[2*1024*1024];
	long[] c=new long[2*1024*1024];
	long[] d=new long[2*1024*1024];
	long[] e=new long[2*1024*1024];
	long[] f=new long[2*1024*1024];
}
