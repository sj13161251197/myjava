package pattern23.适配器;
/**
 * 适配器模式
 * 列子有：中国的插头，插座
 * 外国的插头，插座
 * 做一个能让中国人的插头插座外国插座上的适配器
 * 
 * 
 * 常见的适配器有手机充电线   圆头和梯形的头
 * @author Administrator
 *
 */
public class AdaptorPattern {
	public static void main(String[] args) {
		//中国人拿着中国插头要往外国插座上用
		
	}
}
class zhC{
	zhC(){
		System.out.println("中国的插头");
	}
}
class yC{
	yC(){
		System.out.println("外的插头");
	}
}
interface yCHZ{
	public void yCHZ1(yC c);
}
class yCHZImpl implements yCHZ{

	@Override
	public void yCHZ1(yC c) {
		// TODO Auto-generated method stub
		System.out.println("挺合适的");
	}	
}
interface zCHZ{
	public void zCHZ1(zhC c);
}
class zCHZImpl implements zCHZ{

	@Override
	public void zCHZ1(zhC c) {
		// TODO Auto-generated method stub
		System.out.println("挺合适的");
	}	
}
//适配器
class yCHZ2 implements yCHZ{
	zCHZ zc;
	@Override
	public void yCHZ1(yC c) {//还能用在外国的插座上
		// TODO Auto-generated method stub
		System.out.println("还算合适");
	}
	public void adapt(zCHZImpl z){//用之前先要先适配一下
		zc=z;
	}
	
	
}
