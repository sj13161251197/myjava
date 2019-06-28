package ThreadStudy;

import com.sun.beans.decoder.ValueObject;

/**
 * 线程通讯经典案例   生产者与消费者
 * @author Administrator
 *生产者
 */
public class MoneyThread {
	public static void main(String[] args) {
		String lock=new String("");
		P p=new P(lock);
		C c=new C(lock);
		ThreadP tp=new ThreadP(p);
		ThreadC tc=new ThreadC(c);
		tp.start();
		tc.start();
	}
}
class P {
	public String lock;
	public P(String lock){
		this.lock=lock;
	}
	public void setVlaue(){
		try{
			synchronized(lock){
				if(!"".equals(lock)){
					lock.wait();
				}
				String value=System.currentTimeMillis()+"-"+System.nanoTime();
				System.err.println("set"+value);
				lock.notify();
			}
		}catch(Exception e){
			
		}
	}
}
class C {
	public String lock;
	public C(String lock){
		this.lock=lock;
	}
	public void getVlaue(){
		try{
			synchronized(lock){
				if(!"".equals(lock)){
					lock.wait();
				}
				
				String value=System.currentTimeMillis()+"-"+System.nanoTime();
				System.err.println("get"+value);
				lock="";
				lock.notify();
			}
		}catch(Exception e){
			
		}
	}
}
class ThreadP extends Thread{
	private P p;
	public ThreadP(P p){
		this.p=p;
	}
	public void run(){
		p.setVlaue();
	}
}
class ThreadC extends Thread{
	private C p;
	public ThreadC(C p){
		this.p=p;
	}
	public void run(){
		p.getVlaue();
	}
}