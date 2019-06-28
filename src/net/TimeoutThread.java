package net;

public class TimeoutThread extends Thread{
/**
* 计时器超时时间
*/
private long timeout;
  
/**
* 计时是否被取消
*/
private boolean isCanceled = false;
  
/**
* 当计时器超时时抛出的异常
*/
private TimeoutException timeoutException;
  
	/**
	* 构造器
	* @param timeout 指定超时的时间
	*/
	public TimeoutThread(long timeout, TimeoutException timeoutErr) {
		super();
		this.timeout = timeout;
		this.timeoutException = timeoutErr;
		// 设置本线程为守护线程
		this.setDaemon(true);
	}

	/**
	 * 取消计时
	 */
	public synchronized void cancel() {
		isCanceled = true;
	}

	/**
	 * 启动超时计时器
	 */
	public void run() {
		try {
			System.out.println("run()");
			Thread.sleep(timeout);
			System.out.println("sleep");
			if (!isCanceled)
				throw timeoutException;
		} catch (InterruptedException e) {
			System.out.println("eeeeeeeeee");
			e.printStackTrace();
			throw timeoutException;
		}
	}

}

