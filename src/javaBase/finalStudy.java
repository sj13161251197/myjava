package javaBase;
/**
 * final关键字可以用来修饰类、方法和变量（包括成员变量和局部变量）
 * final修饰一个类时，表明这个类不能被继承,final类中的所有成员方法都会被隐式地指定为final方法。如Stirng
 * 修饰方法：
 * 			第一个原因是把方法锁定，以防任何继承类修改它的含义
 * 修饰变量：如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，
 * 			则在对其初始化之后便不能再让其指向另一个对象。
 * 			必须在定义时或者构造器中进行初始化赋值
 * 		匿名内部类中使用的外部局部变量为什么只能是final变量？
 * @author Administrator
 *
 */
public class finalStudy {
	public final int a;//必须在定义时或者构造器中进行初始化赋值
	public  int c1=0;
	public final String b="";
	public finalStudy(int a,String b){
		this.a=a;
		this.c1=6;
	}
	public static void main(String[] args) {
		finalStudy ss=new finalStudy(5, "");
		int c1=ss.a;
		//ss.a=4;
		finalStudy.test1();
	}
	/**
	 * 运行结果  true，false,false
	 * 原因：由于变量b被final修饰，因此会被当做编译器常量，所以在使用到b的地方会直接将变量b 替换为它的  值。
	 * 		而对于变量d的访问却需要在运行时通过链接来进行。java就不会进行优化
	 */
	public static void test1(){
		String a = "hello2"; 
        final String b = "hello";
        String d = "hello";
        String c = b + 2; 
        String e = d + 2;
        final String b1;
        b1="hello";
        String c1 = b1 + 2;
        System.out.println(a.hashCode());
        System.out.println(c.hashCode());
        System.out.println(c1.hashCode());
        System.out.println(e.hashCode());
        System.out.println((a == c));
        System.out.println((a == e));
        System.out.println((a == c1));
	}
}
