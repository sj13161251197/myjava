package pattern23.factory.method;
/**
 * 	工厂方法模式_工厂接口 
 * @author Administrator
 *
 */
public interface IMyMessageFactory {  
	  
    public IMyMessage createMessage(String messageType);  
}