package java8_lambda.default_static;
/**
 * 实现类继承两个独立的接口，两个接口具有相同的default方法
 * @author Administrator
 *
 */
public class DefaultTest implements A,B{

	@Override
	public void test() {
	   A.super.printA();
       System.out.println("test");		
	}
	/**
	 *
	 * 当实现类继承的多个独立的接口中具有相同的default方法时，
	 * 由于编译器不知道实现类具体将要调用哪种方法会报编译错误，
	 * 必须覆写同名的default方法，
	 * 可以通过A.super.printA()指定具体调用哪个接口的默认方法，
	 * 也可以完全抛开这些default方法另外实现。
	 * 值得注意的是实现类可以有同名的static方法，
	 * 但是不能加@Override注解，原因也好理解，
	 * 静态方法从语义上讲与类的实例无关，子类覆写父类中的静态方法没有实际意义，
	 * 实际调用时直接通过类名调用该类下的静态方法，不同于普通方法会存在多态问题。

	 */
	@Override
	public void printA(){
		//子类调用接口中的非静态方法
		A.super.printA();
	}
//	@Override,接口中同名的静态方法不存在冲突，子类也不能覆写
	static void printB(){
		//通过接口名直接调用接口中静态方法
		A.printB();
		B.printB();
	}
}
