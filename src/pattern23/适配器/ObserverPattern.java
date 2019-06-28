package pattern23.适配器;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 观察者定义了一种一对多的依赖关系，当一个主题(Subject)对象状态发生变化时，
 * 所有依赖它的相关对象都会得到通知并且能够自动更新自己的状态，
 * 这些依赖的对象称之为观察者(Observer)对象这
 * 类似于发布/订阅模式。
 * @author Administrator
 *
 */
public class ObserverPattern {
	public static void main(String[] args) {
		Guanchazhe.addDyue("Dyue1", new Dyue1());
		Guanchazhe.addDyue("Dyue2", new Dyue2());
		Guanchazhe.send("大家吃饭");
		Guanchazhe.removeDyue("Dyue1");
	}
	
}

//观察者 -事物处理
class Guanchazhe{
	//发送消息
	static Map<String,Dyue> map=new HashMap<String,Dyue>();
	static void  send(String str){
		Collection<Dyue> values = map.values();
		for(Dyue o:values){
			o.accept(o.getClass().getName()+"----------"+str);
		}
	}
	static void  addDyue(String str,Dyue d){
		send(str+"来啦");
		map.put(str, d);
	}
	static void  removeDyue(String str){
		map.remove(str);
		send(str+"走啦");
	}
}
//订阅者
abstract class Dyue{
	abstract void accept(String str);
}
class Dyue1 extends Dyue{

	@Override
	void accept(String str) {
		// TODO Auto-generated method stub
		System.out.println(str);
	}
	
}
class Dyue2 extends Dyue{

	@Override
	void accept(String str) {
		// TODO Auto-generated method stub
		System.out.println(str);
	}
	
}