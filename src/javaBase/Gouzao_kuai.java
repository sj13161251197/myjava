package javaBase;



/**
 * 根据结果得知一个类被创建的执行顺序
 * 1父类的静态块
 * 2类本身的静态块
 * 3父类的初始化块
 * 4父类构造
 * 5类本身的初始化块
 * 6类本身的构造
 * @author Administrator
 * 
 * 对象初始化执行顺序
 * 1分配堆栈空间
 * 2成员变量默认值赋予
 * 3构造初始化赋值
 * 
 * 
 * 
 * 
 *
 */
public class Gouzao_kuai {
	public int Gouzao_kuai(){//这就是一个普通的方法
		
		return 0;
		
	}
	private static String b22="dd";//stat
	public static String c22="dd";//stat
	 static {
		 
	        System.out.println("类本身静态初始化块");
	    }

	    {
	        System.out.println("类本身初始化块");
	    }

	    public Gouzao_kuai() {
	        System.out.println("Constructor Main.");
	    }

	    public static void main(String[] args) {
	        C c = new C();
	        //B b = new B();
	        C c2 = new C();
	        //通过创建2次对象得知静态块只会在类第一次被加载时被执行一次  有且只执行一次
	        System.out.println(c2.a);
	        //初始化块每次类被实例化都要执行一次  类似构造但是执行早于构造
	        //静态变量在类被加载时加载所有类课共享
	        System.out.println(c2.b);
	        System.out.println(c2.c);
	        System.out.println(c2.d);
	        
	        //作用：静态块一般用来初始化静态变量
	        //非静态初始化块可以针对多个重载构造函数进行代码复用。
	    }
}
class A {
    static {System.out.println("Static init A."); }

    {System.out.println("Instance init A.");}

    A() {
        System.out.println("Constructor A.");
    }
}

class B extends A {
    static {System.out.println("Static init B."); }

    { System.out.println("Instance init B.");}

    B() {
        System.out.println("Constructor B.");
    }
}

class C extends B {
	public static int a=0;
	public static int b=0;
	public static int c=0;
	public  int d=0;
    static {
    	a++;
    	
        System.out.println("Static init C.");
    }

    { 	b++;
    	d++;
        System.out.println("Instance init C.");
    }

    C() {
    	c++;
    	d++;
        System.out.println("Constructor C.");
    }
}

