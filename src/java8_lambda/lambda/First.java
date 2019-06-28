package java8_lambda.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class First {
	public static void main(String[] args) {
		First first = new First();
		//使用1
		/**
		 * 匿名内部类方式-函数表达式，注意省略使得表达式的可读性发生较大的变化
		 * 如参数类型的省略
		 * {}的省略
		 * return 关键字的省略
		 */
		test1();//
		/**
		 * 方法引用，有普通方法的引用和静态方法的引用
		 */
		test2();
		/**
		 * 表达式内部的变量与方法的区部变量，参数,类变量的关系
		 */
		first.test3(5, 6);
		//this的使用
		first.test4();
		
	}
	
	/**
	 * lambda表达式使用一；
	 */
	public static void test1(){
	   Runnable run1=new Runnable() {//有接口名称为Runnable
			@Override
			public void run() {
	            System.out.println("匿名内部类");
			}
	   };
	  //只有类似于Runnable这样只有一个方法的接口的匿名内部类可以被改写成lambda表达式
	  Runnable run2=()->{//这里是无参无返回
		  System.out.println("lambda表达式");
	  };
	  run1.run();//run接口run的实现
	  run2.run();//这里的疑问run 那里来的
	  
	  //解释：
	  //lambda表达式把接口和抽象方法的声明都省略了，
	  //具体编译时编译器会根据lambda表达式返回的类型
	  //（如Runnable）推断lambda表达式实际对应的接口类型和方法
	  
	  //只有类似于Runnable这样只有一个方法的接口的匿名内部类可以被改写成lambda表达式
	  //java8规定接口中含有的Object对象的方法的抽象方法时该抽象方法忽略不计。
	  
	  
	  //ambda表达式所返回的对象可以理解为针对某个接口的匿名内部类的实例对象，可以像使用普通对象一样调用其实现的接口方法
	  //类似于Runnable这样的接口就被称为函数式接口，
	  //一个接口是否是函数式接口可以通过@FunctionalInterface注解判断，若不符合条件编译器会报错
	  
	  
	  //理解lambda表达式的返回值
	  	Comparator<Integer> com=(Integer x,Integer y)->{
			return x-y;
		};
		//关于参数类型：
		//在指定具体返回类型时可以省略参数类型，由编译器根据返回类型对应的接口类型去推断，如果类型错误会报异常
		
		
		//大括号中的方法体跟普通的方法体完全一样，可以有返回值，也可以没有返回值。没有参数时直接写一对空的小括号，
		//有返回值且计算返回值的表达式较简单时可以省略大括号和return语句。具体如下
		Comparator<Integer> com2=(x,y)->{
			return x-y;
		};
		System.out.println(com2.compare(1, 0));//输出1-0的结果
		Comparator<Integer> com3=(x,y)->x-y;
		
		Supplier<Integer> obj=()->5;
		System.out.println(obj.get());//输出结果5
		ToIntFunction<Integer> obj2=x->x*2;
		System.out.println(obj2.applyAsInt(9));//输出结果2*9
		
		
		//Comparator<Integer> com=(x,y)->Integer.compare(x, y);
		System.out.println(com.compare(1, 2));
		System.out.println(com2.compare(1, 2));
		System.out.println(com3.compare(1, 2));
		int i=obj.get();
		int j=obj2.applyAsInt(5);
		System.out.println(i+":"+j);


	}
	/**
	 * 可以用lambda表达式来表示对方法的调用，称为方法引用，
	 * 方法引用包括静态方法引用，实例方法引用，
	 * 父类方法引用，构造方法引用和数组构造方法引用
	 * @param args
	 */
	public static void test2(){
		//静态方法引用
		//com.compare()的实现是该静态方法的返回  这种形式就是函数式接口的简写
		Comparator<Integer> com=(x,y)->Integer.compare(x, y);
		//上一句的特殊写法
		Comparator<Integer> com2=Integer::compare;
		
		
		List<Person> persons=Arrays.asList(new Person(),new Person(),new Person());
		//forEach方法接受一个lambda表达式作为参数，对于list集合中每个元素都调用该表达式
		//实例方法引用
		
		for(Person p:persons){//常见写法
			p.getFood();
		}
		//js的写法
		//$().each(function(){
			
		//})
		
		//这里不要int的原因   forEach是引用对象才有的
		//Arrays.asList的参数数组元素是基本类型有点问题
		Integer[] array = {1,2,3,4,5,6,7}; 
		List<Integer> asList = Arrays.asList(array);
		asList.forEach(per->{
			 per.intValue();
			}
		);
		//简写为省略掉{} 和return
		asList.forEach(per->per.intValue());
		
		
		persons.forEach(p-> {
			p.getFood();
		});
		persons.forEach(person->person.getFood());
		persons.forEach(Person::getFood); 
		
		//直接作为循环内部的实现其中声明表达式为列表中的元素
		//父类方法引用 
		persons.forEach(person->person.eat());
		persons.forEach(Human::eat);
        List<Integer> ages=Arrays.asList(1,2,3,4,5);
        //构造方法引用
        ages.forEach(x->{
        	new Integer(x);
        });
        //简写为
        ages.forEach(x->new Integer(x));
        ages.forEach(Integer::new);
        //数组构造方法引用
        IntFunction<Integer[]> a=x1->new Integer[x1];
        IntFunction<Integer[]> a2=Integer[]::new;
        a.apply(10);
        a.apply(20);
        a.apply(50);
        System.out.println(a.apply(10));
        a2.apply(10);
        a2.apply(70);
        System.out.println(a2.toString());
	}

	/**
	 * 跟外部方法中的局部变量及外部类成员变量的交互
	 * lambda表达式中可以访问类成员变量，
	 * 可以在lambda表达式的方法体中定义与类成员变量同名的变量，可以修改该类成员变量。
	 * 与内部类访问其外部方法的局部变量要求其必须是final不同。
	 * “effectively final”要求lambda表达式所引用的外部方法变量不能在lambda表达式之外被修改，
	 * 也不能在lambda表达式内部被修改，也不能在lambda表达式中定义与外部方法变量同名的变量名。
	 * @param a
	 */
	private int a=1;
	private static int b=2; 
	private int d=2;
	
	void test3(int c,final int ff){
		int c2=1;
		final int c3=1;
		IntFunction<Integer> f=x->x+a;
		IntFunction<Integer> f2=x->x+b;
		IntFunction<Integer> f3=x->{
			int a=2;//方法体中定义与类成员变量同名的变量，可以修改该类成员变量。
			a++;//在这里如果表达式内部的变量和类变量命名相同，则默认使用表达式内部的变量
			b++;
    	//lambda表达式中不能修改其引用的外部方法的局部变量，一旦被lambda表达式引用，该变量在整个方法体中
    	//同样不能被修改，否则报错
    	b++;
    	//c2++;
    	//但是在lambda表达式中访问其外部方法的局部变量时要求其必须是“effectively final”
    	//c++;
    	int d=2;
    	//不能与表达式外部的方法的局部变量和方法参数命名相同,也不可以使用
    	//不能其被定义的外部方法中的局部变量相同的变量，但可以与类中成员变量同名
    	//int c2=3;
    	//int c=2;
    	//c2++;
    	//c++;
    	//int ff=0;
    	//ff++;
    	 return b+x+c2;
      };
      IntFunction<Integer> f4=x->{
    	  a++;
    	  return a+x;
      };
//	      c2=3;		
	}
	/**
	 * 在lambda表达式内部，this指代的就是外部类，而内部类中this指代的内部类本身
	 */
	public  void test4(){
		Runnable run=()->{
			System.out.println(this);
		};
		Runnable run2=()->{
			System.out.println(toString1()+getName1());
		};
		run.run();
		run2.run();
	}
	
	public  String toString1() {
		return "helloworld";
	}
	private  String getName1(){
		return "shl";
	}
	
	
	
}
