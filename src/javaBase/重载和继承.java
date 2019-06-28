package javaBase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 多态就是指重写，指方法运行时直接自动选择用哪个方法  父类对象调用方法是根据不同的实例对象调用不同的实例所属类的对应实现
 * 而重载只是根据传的参数不同调用不同的方法不属于多态属于一对一
 * 重载：方法名字和参数类型或者个数,类型排序不相同,关于返回值可以不相同但是  不能以返回值区分方法的重载
 * 		类以统一的方式处理不同类型数据的一种手段
 * 重写：类继承  父类的方法除了方法的实现和访问修饰符，其他都必须相同
 * @author Administrator
 *
 */
public class 重载和继承 {
	
	public static void main(String[] args) {
		//方法是否继承的验证
		//通过是否能添加注解@Override,
		//通过子类实例化父类，父类调用对应的方法，看看到底用的那个方法的实现
		//某些方法不能被实例化的原因
		//方法的调用规则是  先到本类中调用没有则到父类中查找
		//static、final、private方法本身都是编译期绑定的（也叫前期绑定）这些方法不存在多态，他们是在还没有运行的时候，程序在编译器里面就知道该调用哪个类的哪个方法了，
		//而其他可观察的普通方法的绑定是在运行的时候根据具体的对象决定的，因为从语义上看这些方法是可被继承的，有了多态而造成了不确定性。。
	
		//重载的特殊情况
		 List<List<String>> lists = new ArrayList<List<String>>();
		    lists.add(new ArrayList<String>());
		    lists.add(new LinkedList<String>());
		   
		    A1 obj = new A1();
		    for (List<String> list : lists) {
		        obj.testOverLoad(list);//这里调用的是方法，testOverLoad(List<String> list)
		    }
	}
}
/**
 * 1）、必须具有不同的参数列表；
 * 2）、可以有不同的返回类型，只要参数列表不同就可以了；
 * 3）、可以有不同的访问修饰符；
 * 4）、可以抛出不同的异常；
 */
class A1{
	
	public void test(String a){
		
	}
	protected void test() {
		
	}
	public static  void test1() {
		
	}
	private void test(String a,String b){
		
	}
	public int test(String a,String b,String c){
		return 0;
		
	}
	
	//理解这几个重载
	public void testOverLoad(ArrayList<String> arrayList){
	    System.out.println("ArrayList");
	}
	public void testOverLoad(LinkedList<String> linkedList){
	    System.out.println("LinkedList");
	}
	public void testOverLoad(List<String> list){
	    System.out.println("list");
	}
}
/**
 * 方法的重写：
 * 1参数列表必须完全与被重写的方法相同
 * 2回的类型必须一直与被重写的方法的返回类型相同
 * 3访问修饰符的限制一定要大于被重写方法的访问修饰符
 * 4重写方法一定不能抛出新的检查异常或者比被重写方法申明更加宽泛的检查型异常。
 * 5方法被定义为final，static不能被重写。
 * 
 * 6验证方法  通过是否能添加注解@Override,
 * 				通过子类实例化父类，父类调用对应的方法，看看到底用的那个方法的实现
 * 
 * @author Administrator
 *
 */
class B1 extends A1{
	private void test(String a,String b){//这个不属于继承来的方法不属于重写
		
	}
	@Override
	protected void test() {
		
	}
}
class  C1 extends B1{
	
}