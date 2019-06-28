package java8_lambda.default_static;
/**
 * 接口A定义了1个接口test，3个默认其中2个同名不同参数2个静态
 * 接口c定义1个接口printA
 * 实现类继承了两个接口，其中接口C的抽象方法printA是另一个接口A的default方法printA
 * @author Administrator
 *
 */
public class F implements A ,C{
/**
 * 首先同一接口中抽象方法、default方法、static方法不能同名，
 * 同一实现类继承自两个不同的接口时，
 * 假如其中一个接口的抽象方法与另一个接口的default方法相同时，
 * 在实现类中default方法不能代替抽象方法的具体实现，
 * 具体而言printA()方法必须被实现，
 * 实现printA()方法的同时继承自A的default方法就被覆写了。

 */
	@Override
	public void printA() {
         System.out.println("默认方法不太代替接口实现");		
	}

	@Override
	public void test() {
		System.out.println("test");
	}

/**
 * 接口a有默认方法printA() 和printA(int a)
 * 接口c有抽象方法printA()
 * 
 * 理论上由于printA(int a)为默认方法不需要实现但是由于接口C有同名不同参的抽象，这里没有这个默认编译不通过
 */
	@Override
	public void printA(int a) {
		// TODO Auto-generated method stub
		A.super.printA(a);
	}



}
