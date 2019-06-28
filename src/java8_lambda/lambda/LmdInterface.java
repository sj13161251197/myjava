package java8_lambda.lambda;
/**
 * 函数式接口-属于特殊类型的接口：特殊点：否只有一个非Object的方法的抽象方法的抽象方法
 * 					（java8规定接口中含有的Object对象的方法的抽象方法时该抽象方法忽略不计。）
 * @author Administrator
 * 
 *函数式接口----方便使用lambda表达式
 */
@FunctionalInterface//该注解吧鉴别该接口是否是函数式接口
public interface LmdInterface {
	void run();//由于是函数式接口只能定义一个非object抽象方法的抽象方法
	//以下两个方法都是Object对象中的方法的抽象方法
	String toString();
	boolean equals(Object object);
    default void say(){//属于接口所以可以定义static方法和default方法
    	System.out.println("hello");
    }
}
