package JVM;

import java.util.ArrayList;
import java.util.List;
/**
 * 要想看jc消息需要先运行时配置vm参数：-XX:+PrintHeapAtGC
 * 由于gc的回收需要一定条件 所有：可以手动回收或者创造添加回收
 * @author Administrator
 *
 */
public class C_JVM {
	static class OOMObject{}
    private static final int _1M = 1024*1024;  
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	byte[] b=new byte[20*1024*1024];
    	System.out.print("Xmx=");//最大
    	System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

    	System.out.print("free mem=");//空闲  可用空间
    	System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

    	//jvm会尽量使得总堆空间缩到最小 不够时自动扩容
    	System.out.print("total mem=");//已经使用的总空间  
    	System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");

    	test();
    	

    }
    /**
     * 	手动回收gc
     */
    static void test() {
    	 List<OOMObject> l=new ArrayList<OOMObject>();

         l.add(new OOMObject());
         System.gc();
    }
    /**
     * 	创造条件回收
     * 	堆空间大小的参数无非就是Xms和Xmx。使得空间参数小一些
     * 	-Xmx5m
		-verbose:gc
     */
    static void test1(){
    	for (long i = 0;i < 1000000; i++) {
            User user = new User(i);
         }
        
    }

}

class User {

	private Long userId;

	public User() {
	}

	public User(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}