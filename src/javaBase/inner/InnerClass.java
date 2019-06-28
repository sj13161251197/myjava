package javaBase.inner;
/**
 * 为什么要有内部类
 * 		为了了解为什么要内部类，先看看内部类有那些特性
 * 			内部类可以访问外部类的所有成员
 * 			内部类可以有多个实例，每个实例都有自己的状态信息，并且与其外围类对象的信息相互独立。
 * 			在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或继承同一个类。
 * 			只能使用内部类才能实现多重继承。
 * 			内部类是面向对象的闭包
 * 内部类怎么使用
 * 内部类怎么定义:成员内部类、局部内部类、匿名内部类、静态内部类。
 * 常见的那些类属于内部类
 * @author Administrator
 *
 */

/**
 * 使用情景必须在一个类中以某种方式实现两个接口。由于接口的灵活性，可以有2种方式实现：使用单一类，或者使用内部类
 * @author Administrator
 *
 */
public class InnerClass {
    int int1=1;
    static int  int2=2;
    final int  int3=3;
    final static int  int4=4;
    private int int5=5;
    private static int int6=6;
    String str1="1";
    //interface A {}//通过内部类的形式实现接口
    /*推荐使用getxxx()来获取成员内部类，尤其是该内部类的构造函数无参数时 */
	static A a=new A(){//成员内部类 
		
		/**
		 * 类加载的时候，static变量就会被初始化
		 *  内部类是延时加载的，也就是说只会在第一次使用时加载。不使用就不加载，所以可以很好的实现单例模式。
		 *  这样这个成员内部类就脱离了外部类的掌控，不需要外部类的对象就可以生成内部类的对象，这与成员内部类的定义就相驳了
		 * 
		 * 成员内部类的对象必须是现有外部类的对象才能创建，并且是绑定在一起的，所以成员内部类不可以定义静态变量。
		 */
		//static int in=0; 不能有static  变量和方法
		
		
		int innerInt2=int2;
		//int innerInt1=int1;//静态内部类访问外部成员的局限性
		 public void innerTest(){
			 int innerInt6=int6;
		 }
	};
    B b=new B() {
    	
    	static final int i = 50; 
    	//static int i2=3;
    	//static final String str = new String("");  
    	int innerint1=int1;//普通成员内部类可以访问外部类任意成员变量
    };
    public void test(){
    	//局部内部类
    	class inner2{
    		//参数是final的原因是因为内部类无法直接访问方法内的局部变量(即拿着外部类的引用无法访问方法内的变量)
    		//在内部类初始化的时候通过构造方法传值的方式将局部变量复制一份给内部类使用
    		//为了保持两个不同变量的一致性
    		public void ddd(final int ss){
    			System.out.println(ss);
    		}
    		public void ddd(final String ss){
    			System.out.println(ss);
    		}
    	}
    	if(true){//作用域内的内部类
    		class inner3{
    			
    		}
    	}
    }
    
    static void takesA(A a){}
    static void takesB(B b){}

    public static void main(String[] args) {
        
        X x = new X();
        Y y = new Y();
         //1
        takesA(x);
        takesA(y);
        //2
        takesB(x);
        takesB(y.makeB());
      
    }

}

class X implements A, B {
	public static void main(String[] args) {
		
	}
}
class Y implements A {
    B makeB() {
        return new B(){};
    }
}
class D {}
abstract class E {}
class Z extends D {
    E makeE(){return new E(){};}
}
