package JVM;

import java.util.HashMap;
/**
 * 可复活CanReliveObj
 * PrintThread
 *MyThread全区停顿 案例
 * @author Administrator
 *
 */
public class D_JVM {
	public static void main(String[] args) throws InterruptedException {
		meth();
	}
	/**
	 * 全区停顿案例
	 */
	private static void meth() {
		/*-Xmx512M -Xms512M 
		-XX:+UseSerialGC 
		-Xloggc:gc.log 
		-XX:+PrintGCDetails  
		-Xmn1m 
		-XX:PretenureSizeThreshold=50 
		-XX:MaxTenuringThreshold=1*/
		MyThread mm=new MyThread();
		PrintThread mt=new PrintThread();
		mt.start();
		mm.start();
	}
	/**
	 * 可复活测试
	 * @throws InterruptedException
	 */
	private static void finals() throws InterruptedException {
		CanReliveObj obj = new CanReliveObj();
		obj = null; // 可复活
		System.gc();
		Thread.sleep(1000);
		if (obj == null) {
			System.out.println("obj 是 null");
		} else {
			System.out.println("obj 可用");
		}
		System.out.println("第二次gc");
		obj = null; // 不可复活
		System.gc();//子类可以覆盖finalize以实现资源清理工作，GC在回收对象之前调用该方法。
		//在finalize方法中显式调用其他资源释放方法
		//finalize方法至多由GC执行一次(用户当然可以手动调用对象的finalize方法，但并不影响GC对finalize的行为)
		Thread.sleep(1000);
		if (obj == null) {
			System.out.println("obj 是 null");
		} else {
			System.out.println("obj 可用");
		}
	}

}
/**
 * finalize解析参考
 * https://www.cnblogs.com/Smina/p/7189427.html
 * @author Administrator
 *
 */
class CanReliveObj {
	public static CanReliveObj obj;

	/**
	 * 这个方法只会被掉用
	 * 建议用于：
	 * ① 清理本地对象(通过JNI创建的对象)；
	 * ② 作为确保某些非内存资源(如Socket、文件等)释放的一个补充：在finalize方法中显式调用其他资源释放方法。
	 * Java语言规范并不保证finalize方法会被及时地执行、而且根本不会保证它们会被执行
	 * finalize方法可能会带来性能问题。因为JVM通常在单独的低优先级线程中完成finalize的执行
对象再生问题：finalize方法中，可将待回收对象赋值给GC Roots可达的对象引用，从而达到对象再生的目的
finalize方法至多由GC执行一次(用户当然可以手动调用对象的finalize方法，但并不影响GC对finalize的行为)


流程：
当对象变成(GC Roots)不可达时，GC会判断该对象是否覆盖了finalize方法，
若未覆盖，则直接将其回收。否则，若对象未执行过finalize方法，
将其放入F-Queue队列，由一低优先级线程执行该队列中对象的finalize方法。执行finalize方法完毕后，
GC会再次判断该对象是否可达，若不可达，则进行回收，否则，对象“复活”。


新建对象首先处于
[reachable, unfinalized]状态(A)（表示GC Roots引用可达）
	随着程序的运行，一些引用关系会消失，导致状态变迁，
从reachable状态变迁到f-reachable(B, C, D)或unreachable(E, F)状态    （对象不可通过上面两种途径可达）
	若JVM检测到处于unfinalized状态的对象变成f-reachable或unreachable，
JVM会将其标记为finalizable状态(G,H)。（表示GC可对该对象执行finalize方法，GC已检测到该对象不可达。正如前面所述，GC通过F-Queue队列和一专用线程完成finalize的执行）
	若对象原处于[unreachable, unfinalized]状态，
则同时将其标记为f-reachable(H)。
在某个时刻，JVM取出某个finalizable对象，
	将其标记为finalized并在某个线程中执行其finalize方法。
由于是在活动线程中引用了该对象，该对象将变迁到(reachable, finalized)状态(K或J)。（finalized表示GC已经对该对象执行过finalize方法，reachable: 表示GC Roots引用可达）
该动作将影响某些其他对象从f-reachable状态重新回到reachable状态(L, M, N)
处于finalizable状态的对象不能同时是unreahable的，由第4点可知，将对象finalizable对象标记为finalized时会由某个线程执行该对象的finalize方法，致使其变成reachable。这也是图中只有八个状态点的原因
程序员手动调用finalize方法并不会影响到上述内部标记的变化，因此JVM只会至多调用finalize一次，即使该对象“复活”也是如此。程序员手动调用多少次不影响JVM的行为
若JVM检测到finalized状态的对象变成unreachable，回收其内存(I)
若对象并未覆盖finalize方法，JVM会进行优化，直接回收对象（O）
注：System.runFinalizersOnExit()等方法可以使对象即使处于reachable状态，JVM仍对其执行finalize方法

	 * 其原因可见下文[finalize的问题]
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("CanReliveObj finalize called");
		obj = this;
	}

	@Override
	public String toString() {
		return "I am CanReliveObj";
	}
}
class PrintThread extends Thread{
	public static final long starttime=System.currentTimeMillis();
	@Override
	public void run(){
		try{
			while(true){
				long t=System.currentTimeMillis()-starttime;
				System.out.println("time:"+t);
				Thread.sleep(100);
			}
		}catch(Exception e){
			
		}
	}
}

/**
 * 这是一个不断消耗内存  达到一定标准有释放内存   使得gc一段时间清理一次大量的数据
 * 理论上100ms打印一次但是  gc处理是会到这这个主线程停顿
 * @author Administrator
 *
 */
class MyThread extends Thread{
	HashMap<Long,byte[]> map=new HashMap<Long,byte[]>();
	@Override
	public void run(){
		try{
			while(true){
				if(map.size()*512/1024/1024>=450){
					System.out.println("=====准备清理=====:"+map.size());
					map.clear();
				}
				
				for(int i=0;i<1024;i++){
					map.put(System.nanoTime(), new byte[512]);
				}
				Thread.sleep(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
