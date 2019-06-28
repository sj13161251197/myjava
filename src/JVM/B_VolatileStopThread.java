package JVM;

public class B_VolatileStopThread extends Thread {
	private volatile boolean stop = false;

	public void stopMe() {
		stop = true;
	}

	public void run() {
		int i = 0;
		while (!stop) {
			i++;
		}
		System.out.println("Stop thread"+i);
	}

	public static void main(String args[]) throws InterruptedException {
		B_VolatileStopThread t = new B_VolatileStopThread();
		t.start();
		
		Thread.sleep(1000);
		t.stopMe();
		Thread.sleep(1000);
	}

}
