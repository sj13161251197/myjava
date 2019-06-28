package javaBase;
/**
 * 接口的变量和方法
 * 接口中的变量会被隐式地指定为public static final变量（并且只能是public static final变量
 * 法会被隐式地指定为public abstract方法且只能是public abstract方法
 * 
 * 用其他关键字，比如private、protected、static、 final等修饰会报编译错误
 * 接口中不能含有静态代码块以及静态方法，而抽象类可以有静态代码块和静态方法；
 * 并且一般情况下不在接口中定义变量。
 * 特征：不可被实例化
 * @author Administrator
 *
 */
public class interfaceClass {

}
/**
 * 抽象方法必须为public或者protected（因为如果为private，则不能被子类继承，子类便无法实现该方法）
 * @author Administrator
 *抽象类不能用来创建对象；
 */
abstract class test{
	
}