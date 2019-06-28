package enumTest;
/**
 * 静态导入
 */
import static java.lang.Math.max;

public class EnumTest {
	public int a=0;
	//java 工作原理：java虚拟机和垃圾回收机制
	//通过虚拟机  .java文件编译为.class文件-类装载器-字节校验器--解释器-操作系统
	//jdk:java的开放根据  包括jre和开发工具编译器调试分析jconsole，jvisualvm
	//jre:java编译器和java程序类库 java jvm虚拟机（jre/bin/client/jvm.dll）
	//JDK所提供的运行环境和工具度需要进行环境变量的配置以后，才能使用，最主要的配置就是把<JDK安装目录>/bin目录设置为Path环境变量值的一部分。
	//安装JRE的时候安装程序会自动把JRE的java.exe添加到了系统变量中。系统变量Path的最前面有%SystemRoot%system32;%SystemRoot%;这样的配置，那样到Windows/system32目录下main去看看，会发现一个java.exe文件。这样就无需配置环境变量，也可以运行Java程序了。
	public static void main(String[] args) {
		EnumTest java1 = new EnumTest();
		//java1.autoBox();
		java1.EnumTest();
	}
	/**
	 * 可变参数  重写与继承
	 * overLoad 与overWriter的区别
	 * overLoad  是一个类  除了参数不同之外不同的方法
	 * overWriter 是父子类  子类继承分类的方法
	 */
	public int addInt(int a ,int b, int... args){
		//args属于一个可变参数  即一个数组
		int sum=0;
		for(int i=0;i<args.length;i++){
			sum+=args[i];
		}
		return sum+a+b;
		
	}
	/**
	 * 增强for   增强for会锁定list不能对list增加和移除
	 */
	public int addInt2(int a ,int b, int... args){
		//args属于一个可变参数  即一个数组
		int sum=0;
		for(int arg:args){
			sum+=arg;
		}
		return sum+a+b;
		
	}
	/**
	 * 自动装箱对象与普通对象的区别
	 */
	public void autoBox(){
		/**
		 * new String 和String 直接赋值的区别：直接赋值  是首先在站中创建引用然后  把引用指向 String 池中  （如果池中没有则先在池中创建一个新的String对象）
		 * new String 是直接在堆中创建对象
		 */
		//args属于一个可变参数  即一个数组
		System.out.println("aaa"=="aaa");
		System.out.println(new String("aaa")==new String("aaa"));
		System.out.println(new String("aaa")=="aaa");
		/**
		 * 享元模式：一个模式大部分一样部分不一样通过方法的参数称之为外部属相
		 * 
		 */
		Integer a=5;//Integer.valueOf(int)这个值大于等于-128并且小于等于127时使用了常量池  类似于上边的new String 和String
		Integer b=5;
		Integer c=212;
		Integer d=212;
		System.out.println(a==b);
		System.out.println(c==d);
		System.out.println(new Integer(5)==new Integer(5));
		System.out.println(new Integer(211)==new Integer(211));
	}
	/**
	 * 枚举:把一个类的数据范围订死
	 */
	public void EnumTest(){
		WeedDay1 week=WeedDay1.w1;
		WeekDay w1 = WeekDay.w1;
		System.out.println(w1.name());
		System.out.println(w1.toString());
		System.out.println(w1.ordinal());
		
	}
	public enum WeekDay{
		w1,w2(2),w3;//所有的其他信息必须放在元素列表的后面,枚举元素的后面有卡号并且有参数表示需要使用含参数的构造
		/**
		 * 
		 */
		private WeekDay(){//构造必须私有
			System.out.println("fist");
		}
		private WeekDay(int a){//构造必须私有
			System.out.println("2");
		}
	}
	public enum WeekDay2{
		//如果枚举只有一个对象  可以按案列模式的方式使用
		w1(30){//每个元素通过内部类时限分类的抽象方法  new 自类的对象 默认调用分类的午餐构造  
			public  WeekDay2 newDay(){
				return w2;
				
			};
		},
		w2(40){
			public  WeekDay2 newDay(){
				return w3;
				
			};
		},
		w3(50){
			public  WeekDay2 newDay(){
				return w1;
				
			};
		};
		/**
		 * 
		 */
		public abstract WeekDay2 newDay();
		private WeekDay2(){//构造必须私有
			System.out.println("fist");
		}
		private WeekDay2(int a){//构造必须私有
			System.out.println("2");
		}
	}
}
/**
 * java模拟定义枚举
 * @author Administrator
 *
 */
class WeedDay1{
	
	private WeedDay1(){
		
	}
	public final static WeedDay1 w1=new WeedDay1(){//通过匿名内部类  替代父类的nextWeek 方法
		public WeedDay1 nextWeek(){
			return w2;
		}
	};
	public final static WeedDay1 w2=new WeedDay1();
	public final static WeedDay1 w3=new WeedDay1();
	
	public WeedDay1 nextWeek(){
		if(this==w1){
			return w2;
		}else if(this==w2){
			return w3;
		}else{
			return w1;
		}
	}
	public String toString(){
		return this==w1?"w1":this==w2?"w2":"w3";
	}
}

