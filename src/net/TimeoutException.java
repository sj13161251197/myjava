package net;

/**
* 序列化号
*/
public class TimeoutException extends RuntimeException {
	private static final long serialVersionUID = -8078853655388692688L;

	public TimeoutException(String errMessage){
		super(errMessage);
		System.out.println("我是异常");
	}
}