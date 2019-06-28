package ThreadStudy;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class Theard1 {
	public  static void main(String[] args) throws InterruptedException {
		XinanChshouhu();
	}
	/**
	 * 守护线程
	 * @throws InterruptedException
	 */
	private static void XinanChshouhu() throws InterruptedException {
		MyThread5 a = new MyThread5();
		//守护线程
		a.setDaemon(true);//使得这个线程守护当前线程
		a.start();
		Thread.sleep(7000);
		System.out.println("我走了，我的守护也要走的");
	}
	/**
	 * 线程的优先级
	 * @return
	 */
	private static MyThread4 YouXianji() {
		MyThread4 a = new MyThread4();
		a.yield();//表示放弃此次线程的占用让给别人，但是不影响下次，下次这个线程得到内存使用正常使用
		a.setPriority(5);//设置线程的优先级   1-10
		return a;
	}
	/**
	 * 线程暂停
	 * @throws InterruptedException
	 */
	private static void ZhanTing() throws InterruptedException {
		//MyThread4 a = new MyThread4();
		//a.start();
		//Thread.sleep(5);
		//a.suspend();//线程暂停(不会释放线程锁，如果线程内对一个对象加锁，造成对象独占锁)
		//Thread.sleep(5000);
		//a.resume();//线程回复运行-------------
		//线程不恢复--下面的语句不会执行的 是因为这个线程内部调用了方法println  而println方法是加锁的  ，所以导致主线程的pringLn也被加锁了
		System.out.println("***************************************");
		
		//导致线程对象数据不同步
		//如果在有一个线程操作这个my对象   会发现对象数据不完整
		MyObje my=new MyObje();
		Thread t=new Thread(){
			public void run(){
				my.setMyObje("b", "b");
			}
		};
		t.setName("a");
		t.start();
		Thread.sleep(5);
		System.out.println(my.name);
		System.out.println(my.pass);
	}
	/**
	 * 线程中断
	 */
	private static void XinahChZhongDuan() {
		try{
			MyThread4 a = new MyThread4();
			a.start();
			Thread.sleep(3);//sleep状态与中断标记的关系：
			//a.interrupt();//给线程打上中断标记，但是不会中断线程，需要在线程的run实现中检测是否有中断标记  有则如何处理，没有则如何处理
			//a.sleep(2000);//sleep状态与中断标记的关系：
			a.stop();//线程暴力停止：过时  可能使得一些清理工作不能完成，另外对一些锁定的对象解锁，导致数据同步问题-数据不完善
		}catch(Exception e){ 
			e.printStackTrace(); 
		}
	}
	private static void sleepTest() {
		MyThread3 a = new MyThread3();
		//MyThread3 a2 = new MyThread3();
		a.start();
		//a2.start();
		//Thread.sleep(1);//使得当前线程暂停，多线程时不影响其他线程，如果有锁，不会释放锁  仍然保持锁拥有权
		System.err.println("******************");
	}
	private static void isAlive() {
		MyThread A = new MyThread("A");
		//System.out.println(A.isAlive());
		A.start();
		/*System.out.println(A.isAlive());
		try {
			Thread.sleep(0);//主线程在睡觉期间，A线程已经可能执行完毕 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(A.isAlive());*/
	}
	/**
	 * 多线程变量安全
	 */
	private static void DuoXianCh() {
		MyThread A = new MyThread("A");
		
		System.out.println(A.currentThread().isAlive());
		MyThread B = new MyThread("B");
		MyThread C = new MyThread("C"); 
		A.start();
		
		B.start();  
		C.start();
	}
	/**
	 * 线程基本类测试1
	 */
	void baseTest(){
		//main 有jvmg管理的名叫main的线程
		System.out.println(Thread.currentThread().getName());
		//等多个线程同时运行时代码的执行顺序和调用顺序无关
		//但一个线程内的代码按顺序执行
		MyThread myThread = new MyThread("test");
		myThread.start();//告诉告诉线程规划器我已经准备就绪可以使用了
		myThread.start();//多长调用会异常 java.lang.IllegalThreadStateException
		System.out.println("我的测试2");
		System.out.println("我的测试");
		MyThread2 myThread2 = new MyThread2();
		Thread th=new Thread(myThread2);//Thrad的构造不仅能够传入Runnable的实例也可以传入  Thread类的对象-使得交给别人调用
		th.start();
		
	}
}

class MyThread extends Thread{

	private  int count=5;//
	private static int count2=6;//数据共享的线程安全   0--变大的原理
	public MyThread(String name){
		this.setName(name);
	}
	/**
	 * 线程非安全指  多个线程对一个类中的共享变量都进行操作，由于线程之间执行顺序的不同步  而可能导致的值变更不同步
	 * 给一个方法加上关键字synchronized  可以使得方法执行时限判断是别人正在执行   执行则等待  尝试   不执行则可以立即执行  加锁，执行完释放锁
	 */
	public synchronized void run(){
		System.out.println("测试");
		/*while (count>0) {
			System.out.println(Thread.currentThread().getName()+"---"+count+"---"+count2);
			count--; 
			count2--;
		}*/
		/*for(;count>0;count--){
			System.out.println(Thread.currentThread().getName()+"---"+count+"---"+count2);
		}*/
		
		//着一部其实是2不操作  先-- ,在pringt  如果方法不安全则这一条语句也是线程非安全的
		System.out.println(Thread.currentThread().getName()+"---"+count2--); 
		System.out.println("this---"+this.isAlive());
		System.out.println("Thread.currentThread()---"+Thread.currentThread().isAlive());
		System.out.println("this---"+this.isAlive());
		
	}
}
/**
 * 类不能多继承  线程的第二章方式
 * 然后通过Thread的构造注入  启动
 */
class MyThread2 implements  Runnable {
	private int count=6;
	
	public void run() {
		System.out.println("测试2");
	}
}
class MyThread3 extends Thread{
	public synchronized void run() {
		 long  i=0;
		 while(i<100000){
			
			if(i>80){
				System.err.println(this.getName()+"/"+i++);
				/*try {
					this.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}else{
				System.out.println(this.getName()+"/"+i++);
			}
		}
	}
}
class MyThread4 extends Thread{
	public synchronized void run() {
		 long  i=0;
		 while(i<1000){
			 if(this.isInterrupted()){//检测线程是否有中断标记
				//try {
					//this.sleep(1000);//sleep状态与中断标记的关系：有中断标记的线程在sleep会抛出异常
				//} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				 // this.interrupted()
			   //isInterrupted  只是测试中断标记
				break; 
			 }
			 System.out.println(this.getName()+"/"+i++);
		}
	}
}
class MyObje{
	public String name="1";
	public String pass="1";
	public void setMyObje(String name,String pass){
		this.name=name;
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("停止线程");
			Thread.currentThread().suspend();
			
		}
		this.pass=pass;
	}
}
class MyThread5 extends Thread{
	public synchronized void run() {
		 long  i=0;
		 while(true){
			 i++;
			System.out.println("i+"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

