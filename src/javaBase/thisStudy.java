package javaBase;
/**
 * this关键字必须放在非静态方法里面
 * this关键字代表自身
 * 		使用this关键字引用成员变量
 * 		使用this关键字在自身构造方法内部引用其它构造方法
 * 		使用this关键字代表自身类的对象
 * 		使用this关键字引用成员方法
 * @author Administrator
 *
 */
public class thisStudy {
	int a=0;
	
	public thisStudy(int a){
		this.a=a;//this 第一用法
		this.test();//this 第2用法,自身构造方法内部引用其普通方法
		
	}
	public thisStudy(){
		
		this(3);//构造内引用其他构造,
		
	}
	public thisStudy(int a,int b){
		
		this();//构造内引用其他构造,
		
	}
	public static void main(String[] args) {
		
	}
	public thisStudy test(){
		this.test2(5);//this第二用法
		return this;//this 第三用法
	}
	private void test2(int i) {
		// TODO Auto-generated method stub
		
	}
	
}
