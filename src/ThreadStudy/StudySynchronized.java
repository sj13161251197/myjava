package ThreadStudy;

import java.util.concurrent.atomic.AtomicInteger;

import com.sun.swing.internal.plaf.synth.resources.synth;

/**
 * 对象以及变量的并发访问
 * 关键字Synchronized  监视class  或者Object实例的使用
 * Synchronized 修饰方法时会把方法锁死要想访问其它的同步方法必须等这个方法执行完毕才可以
 * 
 * 同步代码块  Synchronized(this){维护变量}  与同步方法 同步
 * Synchronized(非this对象){}   与同步方法   异步
 * 静态同步方法
 * 关键字volatile  的使用
 * 
 * 方法内的变量为线程安全，非线程安全的问题存在于变量实例二中
 * @author Administrator
 * api：
 * bianlMenth  2个线程同时访问一个类的一个实例的同一个方法（未加锁）
 * methTB      2个线程同时访问一个类的一个实例的同一个方法（加锁）
 * methTB2     2个线程同时访问一个类的2个实例的同一个方法(加锁无影响)这个锁是对象锁
 * MonMeth     一个对象既有同步方法，又有异步方法
 * 
 *Synchronized 是类的实例的锁， Synchronized修饰的方法一定是顺序运行的
 *只有关系资源的运行才需要同步，私有资源没有必要同步
 *
 */
 public class StudySynchronized {
	
	public static void main(String[] args) throws InterruptedException {
		myt[] ma=new myt[100];
		for(int a=0;a<100;a++){
			ma[a]=new myt();
		}
		for(int a=0;a<100;a++){
			ma[a].start();
		}
		
	}
	private static void T() throws InterruptedException {
		rung r=new rung();
		r.start();
		Thread.sleep(1000);
		r.setB(false);
		System.out.println("sdfghj");
	}
	private static void xianch() throws InterruptedException {
		PrintStr p=new PrintStr();
		Thread t=new Thread(p);
		 t.start();
		 Thread.sleep(1000);
		 p.setB(false);
	}
	private static void deal2() throws InterruptedException {
		DealT d=new DealT();
		Thread t=new Thread(d);
		Thread t2=new Thread(d);
		d.setname("a");
		t.start();
		Thread.sleep(100); 
		d.setname("b");
		t2.start();
	}
	private static void lockT() {
		DeadLock lock = new DeadLock() ;
	     new ProxyLeftLock(lock).start() ;
	     new ProxyRightLock(lock).start() ;
	     //jps获得当前Java虚拟机进程的pid
	     //jstack打印堆栈。jstack打印内容的最后其实已经报告发现了一个死锁，但因为我们是分析死锁产生的原因，而不是直接得到这里有一个死锁的结论，所以别管它，就看前面的部分
	}
	/**
	 * 同步方法的死锁的解决办法
	 */
	private static void TbuMent() {
		MetnoedTes12 a=new MetnoedTes12();//同步不具有继承性
		Thread18 c=new Thread18();
		Thread19 c2=new Thread19();
		c.set(a);
		c.setName("A"); 
		c.start();
		c2.set(a);
		c2.setName("B");
		c2.start();
	}
	public static void jisuan(double r,double d){//半径-mm 孔半径  10.5g/cm3   3638/kg
		
		System.out.println((3.142*r*r-3.14*d*r)*10.5*3.638);
	}
	/**
	 * String常量池特性  str相同在这里等价于持有相同的锁，因此，对于String不被作为加锁对象，而new String()
	 */
	private static void TbuStr() {
		MetnoedTes11 a=new MetnoedTes11();//同步不具有继承性
		Thread16 c=new Thread16();
		Thread17 c2=new Thread17();
		c.set(a);
		c.setName("A");
		c.start();
		c2.set(a);
		c2.setName("B");
		
		
		c2.start();
	}
	/**
	 * 静态锁
	 */
	private static void staticSuo() {
		MetnoedTes10 a=new MetnoedTes10();//同步不具有继承性
		Thread13 c=new Thread13();
		Thread14 c2=new Thread14();
		Thread15 c3=new Thread15();
		c.set(a);
		c.setName("A");
		c2.set(a);
		c2.setName("B");
		c3.set(a);
		c3.setName("C");
		c.start();
		c2.start();
		c3.start();
		//AB同步，但是C和AB不同步 
		//原因：AB是static 的同步方法 c不是
		//static 的锁对所有class类的实例起作用
		/*开始A1529409269249
		开始C1529409269249
		结束C1529409269249
		结束A1529409272250
		开始B1529409272250
		结束B1529409272250*/
	}
	/**
	 * 同步代码块-锁（this）{}一半同步  -一半异步 (只需要把实例变量发生变化的代码放在同步代码快里即可)
	 * 同步代码块将会锁住当前对象的所有同步代码块的代码的执行顺序和所有的同步方法
	 */
	private static void TBdaimakuai() {
		MetnoedTes9 a=new MetnoedTes9();//同步不具有继承性
		Thread12 c=new Thread12();
		Thread12 c2=new Thread12();
		c.set(a);
		c2.set(a);
		c.start();
		c2.start();
	}
	/**
	 * 同步不具有继承性
	 */
	private static void WuExt() {
		MetnoedTes8 a=new MetnoedTes8();//同步不具有继承性
		Thread11 c=new Thread11();
		Thread11 c2=new Thread11();
		c.set(a);
		c2.set(a);
		c.start();
		c2.start();
	}
	/**
	 * 抛出异常自动释放锁
	 */
	private static void EXs() {
		Thread10 th=new Thread10();
		Thread11 th2=new Thread11();
		MetnoedTest7 c=new MetnoedTest7();
		th.set(c);
		th2.set(c);
		th.start();
		th2.start();
	}
	private static void suoChru() {
		Thread9 th=new Thread9();
		MetnoedTest6 c=new MetnoedTest6();
		th.set(c);
		th.start();
	}
	/**
	 * 锁重入：即一个线程拿到一个对象的锁之后，可以再次得到该对象的锁   -即在一个同步方法内部可以再次调用其它同步方法，不然就死锁了
	 * 所重入允许子类可以调用父类的同步方法
	 * @throws InterruptedException
	 */
	private static void SuoCHru() throws InterruptedException {
		Thread8 th=new Thread8();
		MetnoedTest4 c=new MetnoedTest4();
		th.set(c);
		th.start();
		th.sleep(1000);
		c.getV();
	}
	/**
	 * 数据脏读  是2个线程操作一个实例的不同的方法(且这些方法至少有一个线程不同步)
	 * 
	 * 
	 * @throws InterruptedException
	 */
	private static void Zdu() throws InterruptedException {
		Thread8 th=new Thread8();
		MetnoedTest4 c=new MetnoedTest4();
		th.set(c);
		th.start();
		th.sleep(1000);
		c.getV();
	}
	/**
	 * 如果一个对象既有同步方法，又有异步方法
	 * 结果交叉-虽然线程一给实例加锁
	 * 他只会妨碍另外一个线程对应这个实例的这个相同方法
	 * 但不会妨碍另外一个线程访问非同步的其他方法，会妨碍其他同步方法
	 * 
	 */
	private static void MonMeth() {
		
		MetnoedTest3 metnoedTestC = new MetnoedTest3();
		Thread5 thread1 = new Thread5(metnoedTestC);
		//Thread6 thread2 = new Thread6(metnoedTestC); 
		Thread7 thread3 = new Thread7(metnoedTestC); 
		thread1.start();
		//thread2.start();
		thread3.start();
		/*异步方法------------
		同步方法------------
		同步方法end------------
		异步方法end------------*/
		
		/*同步方法------------
		同步方法end------------
		同步方法不同名------------
		同步方法不同名end------------*/
	}
	/**
	 *  
	 *  2个线程同时访问一个类的2个实例的同一个方法
	 *  结果又交叉的原因：产生2个业务对象-对应2个锁，
	 *  那个线程先执行同步方法，则那个线程持有改方法所属对象的（实例）的锁
	 *  而这里是2个锁各管各的实例不干扰
	 */
	private static void methTB2() {
		MetnoedTest2 metnoedTest1 = new MetnoedTest2();
		MetnoedTest2 metnoedTest2 = new MetnoedTest2();
		Thread3 thread1 = new Thread3(metnoedTest1); 
		Thread4 thread2 = new Thread4(metnoedTest2);
		thread1.start();
		thread2.start();
		/*bbbbbbbbbbbbbbbbbbbb300/300
		aaaaaaaaaaaaaaaaaa200/200
		B/300/300
		a/200/200*/
	}
	/**
	 * 方法同步：多个线程访问一个对象，结果不交叉
	 */
	private static void methTB() {
		MetnoedTest2 metnoedTestC = new MetnoedTest2();
		Thread3 thread1 = new Thread3(metnoedTestC); 
		Thread4 thread2 = new Thread4(metnoedTestC);
		thread1.start();
		thread2.start();
		//aaaaaaaaaaaaaaaaaa200/200
		//a/200/200
		//bbbbbbbbbbbbbbbbbbbb300/300
		//B/300/300
	}
	/**
	 * 类实例变量测试
	 */
	private static void leibianl() {
		MetnoedTestC metnoedTestC = new MetnoedTestC();
		MetnoedTestC metnoedTestC2 = new MetnoedTestC();
		metnoedTestC.num2=5;
		metnoedTestC2.num2=10;
		System.out.println(metnoedTestC.num2);
	}
	/**
	 * 方法内的变量为线程安全参考变量num
	 * 类的实例变量为非安全的 参考变量num2;
	 * 因为方法内部的变量是私有的
	 * 
	 * 多个线程同时操作业务对象中的实例变量
	 * 而多个线程如果同时操作一个实例，如果实例无锁则实例的变量因为在每个线程获取的时候是共享的都可以操作
	 * ，而多个线程的操作顺序是随机的，这样多个线程随机操作一个实例的不同时期的同一个变量而导致的不同步
	 * 
	 */
	private static void bianlMenth() {
		MetnoedTestC metnoedTestC = new MetnoedTestC();
		Thread1 thread1 = new Thread1(metnoedTestC); 
		Thread2 thread2 = new Thread2(metnoedTestC);
		thread1.start();
		thread2.start();
		/*aaaaaaaaaaaaaaaaaa200/200
		bbbbbbbbbbbbbbbbbbbb300/300
		B/300/200
		a/200/200*/
	}
}
class MetnoedTestC{
	int num2=100;
	void metnoedTest(String userName) throws InterruptedException{
		int num=100;
		if("a".equals(userName)){
			num=200;
			num2=200;
			System.out.println("aaaaaaaaaaaaaaaaaa"+num+"/"+num2);
			Thread.sleep(1000);
		}else{
			num=300;
			num2=300;
			System.out.println("bbbbbbbbbbbbbbbbbbbb"+num+"/"+num2);
		} 
		System.out.println(userName+"/"+num+"/"+num2);
	}
}
class MetnoedTest2{
	int num2=100;
	synchronized void  metnoedTest(String userName) throws InterruptedException{
		int num=100;
		if("a".equals(userName)){
			num=200;
			num2=200;
			System.out.println("aaaaaaaaaaaaaaaaaa"+num+"/"+num2);
			Thread.sleep(1000);
		}else{
			num=300;
			num2=300;
			System.out.println("bbbbbbbbbbbbbbbbbbbb"+num+"/"+num2);
		} 
		System.out.println(userName+"/"+num+"/"+num2);
	}
	
}
class Thread1 extends Thread{
	MetnoedTestC c;
	public void  run(){
		try {
			c.metnoedTest("a");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread1(MetnoedTestC c){
		this.c=c;
	}
}
class Thread2 extends Thread{
	MetnoedTestC c;
	public void  run(){
		try {
			c.metnoedTest("B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread2(MetnoedTestC c){
		this.c=c;
	}
}
class Thread3 extends Thread{
	MetnoedTest2 c;
	public void  run(){
		try {
			c.metnoedTest("a");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread3(MetnoedTest2 c){
		this.c=c;
	}
}
class Thread4 extends Thread{
	MetnoedTest2 c;
	public void  run(){
		try {
			c.metnoedTest("B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread4(MetnoedTest2 c){
		this.c=c;
	}
}
class MetnoedTest3{
	int num2=100;
	synchronized void  metnoedTest(String userName) throws InterruptedException{
		
		System.out.println("同步方法------------");
		Thread.sleep(2000);
		System.out.println("同步方法end------------");
	}
	void metnoedTest2(String userName) throws InterruptedException{
		
		System.out.println("异步方法------------");
		Thread.sleep(2000);
		System.out.println("异步方法end------------");
	}
	synchronized void  metnoedTest3(String userName) throws InterruptedException{
		
		System.out.println("同步方法不同名------------");
		Thread.sleep(2000);
		System.out.println("同步方法不同名end------------");
	}
}
class Thread5 extends Thread{
	MetnoedTest3 c;
	public void  run(){
		try {
			c.metnoedTest("a");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread5(MetnoedTest3 c){
		this.c=c;
	}
}
class Thread6 extends Thread{
	MetnoedTest3 c;
	public void  run(){
		try {
			c.metnoedTest2("B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread6(MetnoedTest3 c){
		this.c=c;
	}
}
class Thread7 extends Thread{
	MetnoedTest3 c;
	public void  run(){
		try {
			c.metnoedTest3("B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Thread7(MetnoedTest3 c){
		this.c=c;
	}
}
class MetnoedTest4{
	String name="aa";
	String pass="123";
	synchronized void setV(String name,String pass) throws InterruptedException{
		this.name=name;
		Thread.sleep(3000);
		this.pass=pass;
		System.out.println(this.name+"/"+this.pass);
	}
	void getV(){
		System.out.println(this.name+"/"+this.pass);
	}
}
class MetnoedTest5{
	String name="aa";
	String pass="123";
	synchronized void setV(String name,String pass) throws InterruptedException{
		this.name=name;
		Thread.sleep(3000);
		this.pass=pass;
		getV();
	}
	synchronized  void getV(){
		System.out.println(this.name+"/"+this.pass);
	}
}
class MetnoedTest6{
	int num=0;
	synchronized void setV() throws InterruptedException{
		while(num<10){
			Thread.sleep(1000);
			getV();
			num++;
			System.out.println(num);
		}
	}
	synchronized  void getV() throws InterruptedException{
		while(num<6){
			Thread.sleep(1000);
			num+=2;
			System.out.println(num); 
		}
	}
}

class Thread8 extends Thread{
	MetnoedTest4 c;
	public void  run(){
		try {
			c.setV("B","BB");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTest4 c){
		this.c=c;
	}
}
class Thread9 extends Thread{
	MetnoedTest6 c;
	public void  run(){
		try {
			c.setV();
			c.getV();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTest6 c){
		this.c=c;
	}
}
class MetnoedTest7{
	int num=0;
	synchronized void setV(String a) throws InterruptedException{
		while(num<10){
			if(num>5&&num<8){
				num = Integer.parseInt(a);
			}else{
				Thread.sleep(1000);
			}
			num++;
			
			System.out.println(num);
		}
	}

}
class MetnoedTes8 extends MetnoedTest7{
	@Override
	void setV(String a) throws InterruptedException{
		while(num<10){
			if(num>5&&num<8){
				num = Integer.parseInt(a);
			}else{
				Thread.sleep(1000);
			}
			num++;
			
			System.out.println(num);
		}
	}
}
class MetnoedTes9{
	int i=0;
	int j=0;
	void setV() throws InterruptedException{
		while(i<10){
			i++;
			System.out.println(i);
		}
		synchronized(this){
			while(j<10){
				j++;
				System.err.println(j);
			}
		}
	}
}
class Thread10 extends Thread{
	MetnoedTest7 c;
	public void  run(){
		try {
			c.setV("s");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTest7 c){
		this.c=c;
	}
}
class Thread11 extends Thread{
	MetnoedTest7 c;
	public void  run(){
		try {
			c.setV("8");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTest7 c){
		this.c=c;
	}
}
class Thread12 extends Thread{
	MetnoedTes9 c;
	public void  run(){ 
		try {
			c.setV();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTes9 c){
		this.c=c;
	}
}
class MetnoedTes10{
	
	synchronized static  void setV() throws InterruptedException{
		System.out.println("开始"+Thread.currentThread().getName()+System.currentTimeMillis());
		Thread.sleep(3000);
		System.out.println("结束"+Thread.currentThread().getName()+System.currentTimeMillis());
	}
	synchronized static  void getV(){
		System.out.println("开始"+Thread.currentThread().getName()+System.currentTimeMillis());
		System.out.println("结束"+Thread.currentThread().getName()+System.currentTimeMillis());
	}
	synchronized   void getC(){
		System.out.println("开始"+Thread.currentThread().getName()+System.currentTimeMillis());
		System.out.println("结束"+Thread.currentThread().getName()+System.currentTimeMillis());
	}
}
class Thread13 extends Thread{
	MetnoedTes10 c;
	public void  run(){ 
		try {
			c.setV();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTes10 c){
		this.c=c;
	}
}
class Thread14 extends Thread{
	MetnoedTes10 c;
	public void  run(){ 
		c.getV();
	}
	public  void set(MetnoedTes10 c){
		this.c=c;
	}
}
class Thread15 extends Thread{
	MetnoedTes10 c;
	public void  run(){ 
		c.getC();
	}
	public  void set(MetnoedTes10 c){
		this.c=c;
	}
}


class MetnoedTes11{
	
	static void  setV(String str) throws InterruptedException{
		
		synchronized(str){
			while(true){
				System.err.println(Thread.currentThread().getName()+"-----------"+str);
				Thread.sleep(1000);
			} 
		}
	}
}
/**
 * 这里注意如果使用new String("AA")则对象不会像直接使用"AA"一样被加锁
 * @author Administrator
 *
 */
class Thread16 extends Thread{
	MetnoedTes11 c;
	public void  run(){ 
		try {
			c.setV("AA");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTes11 c){
		this.c=c;
	}
}
class Thread17 extends Thread{
	MetnoedTes11 c;
	public void  run(){ 
		try {
			c.setV("AA");
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void set(MetnoedTes11 c){
		this.c=c;
	}
}
/**
 * 同步方法锁死的解决办法
 * @author Administrator
 *
 */
class MetnoedTes12{
	
	/*synchronized void  setV() throws InterruptedException{
		System.err.println(Thread.currentThread().getName()+"-----------");
		boolean flag=true;
		while(flag){
			//System.err.println(Thread.currentThread().getName()+"-----------"+str);
			//Thread.sleep(1000);
		}
	}*/
	Object obj=new Object();
	 void  setV(){
		 synchronized(obj){
			 System.err.println("A");
			 boolean flag=true;
				while(flag){
					//System.err.println(Thread.currentThread().getName()+"-----------"+str);
					//Thread.sleep(1000);
				}
		 }
			
	}
	Object obj2=new Object();
	/*synchronized void  setB() throws InterruptedException{
		System.err.println(Thread.currentThread().getName()+"-----------");
		
		
	}*/ 
	void  setB() {
		 synchronized(obj2){
			 System.err.println("B");
		 }
	}
}
/**
 * 这里注意如果使用new String("AA")则对象不会像直接使用"AA"一样被加锁
 * @author Administrator
 *
 */
class Thread18 extends Thread{
	MetnoedTes12 c;
	public void  run(){ 
		c.setV();
	}
	public  void set(MetnoedTes12 c){
		this.c=c;
	}
}

class Thread19 extends Thread{
	MetnoedTes12 c;
	public void  run(){ 
		c.setB();
	}
	public  void set(MetnoedTes12 c){
		this.c=c;
	}
}
/**
 * 线程死锁：
 *  当一个线程永远地持有一个锁，并且其他线程都尝试去获得这个锁时，那么它们将永远被阻塞，这个我们都知道。
 *  如果线程A持有锁L并且想获得锁M，线程B持有锁M并且想获得锁L，那么这两个线程将永远等待下去，这种情况就是最简单的死锁形式。
 */

class DeadLock {
    
    private final Object    left    = new Object() ;
    private final Object    right    = new Object() ;
    
    public void left() throws Exception {
        synchronized (left) {
            Thread.sleep(2000) ;
            synchronized (right) {
                System.out.println("左边") ;
            }
        }
    }
    
    public void right() throws Exception {
        synchronized (right) {
            Thread.sleep(2000) ;
            synchronized (left) {
                System.out.println("右边") ;
            }
        }
    }
}
class ProxyLeftLock extends Thread {
    
    private DeadLock    lock ;
    
    public ProxyLeftLock(DeadLock lock) {
        this.lock = lock ;
    }
    
    @Override
    public void run() {
        try {
            lock.left() ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }
    
}
class ProxyRightLock extends Thread {
    
    private DeadLock    lock ;
    
    public ProxyRightLock(DeadLock lock) {
        this.lock = lock ;
    }
    
    @Override
    public void run() {
        try {
            lock.right() ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }
    
}
class DealT implements Runnable{
	public String username;
	public Object lock1=new Object();
	public Object lock2=new Object();
	public void setname(String name){
		this.username=name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if("a".equals(username)){
			synchronized (lock1) {
				System.out.println(username);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(lock2){
					System.out.println("lock1-lock2");
				}
			}
		}
		if("b".equals(username)){
			synchronized (lock2) {
				System.out.println(username);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(lock1){
					System.out.println("lock2-lock1");
				}
			}
		}
	}
}
class PrintStr implements Runnable{
	private boolean b=true;
	int i=0;
	public void setB(boolean b){
		this.b=b;
	}
	public void pring(){
		while (b) {
			System.out.println(i+++"-----------");
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (b) {
			System.out.println(i+++"-----------");
		}
	}
}
class rung extends Thread{
	volatile private boolean isRunning=true;  //volatile 修饰变量  synchronized修饰方法或者方法块
	
	int i=0;
	public void setB(boolean b){
		this.isRunning=b;
	}
	public void pring(){
		while (isRunning) {
			System.out.println(i+++"-----------");
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning) {
			System.out.println(i+++"-----------");
		}
		System.out.println("线程被停止了");
	}
	
}
class myt extends Thread{
	 volatile public  static int count;
	//volatile关键字的目的是吧数据及时从所有内存中传入共有内存中，而每次取数据也是从共有内存中读取
	/**
	 * 第一步read,load：从主存复制数据到当前线程内存
	 * 第二步use,assign改变共享变量值
	 * 第三部store，write 把值从入内存中
	 * 由于以上操作为分步执行 所以会导致线程不安全
	 * AtomicInteger 表示一个数据进行原子操作
	 */
	synchronized static private void addC(){
		for(int i=0;i<100;i++){ 
			count++;
		}
		System.out.println("conut++++++++++"+count);
	}
	public void run(){
		addC();
	}
}
